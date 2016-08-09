package com.yf.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.*;

import com.yf.constant.OrderNodeConstant;
import com.yf.dao.OrderDao;
import com.yf.dao.OrderNodeDao;
import com.yf.model.Order;
import com.yf.model.OrderNode;

@Component
public class OrderCheckTask {

	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Resource(name="orderNodeDao")
	private OrderNodeDao orderNodeDao;
	

	/**
	 * 定时检查订单流程是否超时
	 */
	@Scheduled(cron="0/5 * * * * ?") 
	public void checkOrderNodeIsOverTime(){
		
		//获取所有正在被执行的订单
		List<Order> orders = orderDao.getOrderExcute();
		
		if((orders != null)&&(orders.size() != 0)){
			
			for(Order order:orders){
				
				List<OrderNode> orderNodes = orderNodeDao.getOrderNodes(order.getId());
				
				if(orderNodes != null){
					this.checkOrderNode(orderNodes);
				}
				
			}
		}
	}
	
	/**
	 * 检测节点是否超时
	 * @param orderNodes
	 */
	public void checkOrderNode(List<OrderNode> orderNodes){
		
		for(OrderNode orderNode : orderNodes){
			
			boolean res = this.checkOverTime(orderNode.getStarttime(), orderNode.getOvertime());
			
			/**
			 * 如果超时了
			 */
			if(res){
				
				orderNode.setStatus(OrderNodeConstant.NODE_OVERTIME);
				
				orderNodeDao.updateOrderNode(orderNode);
			}
			
		}
		
	}
	
	/**
	 * 检测时间是否超时
	 * @param starttime
	 * @param overtime
	 * @return
	 */
	public boolean checkOverTime(String starttime,String overtime){
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean res = false;
		try {
			
			Date date = sf.parse(starttime);
			
			Date currentTime = new Date();
			
			//如果相减大于超时时间则说明超时了
			res = ((currentTime.getTime()-date.getTime())>Long.parseLong(overtime))?true:false;
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}
}
