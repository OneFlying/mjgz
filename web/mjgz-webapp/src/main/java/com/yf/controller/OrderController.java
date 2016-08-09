package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.constant.OrderNodeConstant;
import com.yf.dao.OrderDao;
import com.yf.dao.OrderNodeDao;
import com.yf.dao.SearchEntity;
import com.yf.model.Order;
import com.yf.model.OrderNode;
import com.yf.utils.BarCodeUtil;
import com.yf.utils.StringUtils;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Resource(name="orderNodeDao")
	private OrderNodeDao orderNodeDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 * 前端需要序列化节点字符串JSON.stringfy()
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addOrder(HttpServletRequest request,@RequestBody Order order){
		
		ModelMap modelMap = new ModelMap();
		List<OrderNode> orderNodes = order.getOrderNodes();
		//使用系统的当前时间作为id
		String id = System.currentTimeMillis()+"";
		order.setId(id);
		
		//测试生成条形码
		String code = BarCodeUtil.creatTestBarCode(request, id);
		order.setCode(id);
		
		//添加订单节点 每个订单有多个流程节点
		int total=0;
		for(OrderNode orderNode : orderNodes){		
			orderNode.setOrderId(order.getId());
			orderNode.setStatus(OrderNodeConstant.NODE_UNEXCUTE);
			total += orderNodeDao.addOrderNode(orderNode);
			
		}
		
		if(total == orderNodes.size()){
			//添加订单
			int res = orderDao.insertOrder(order);
			
			if(res != 0){
				modelMap.put("success", true);
				modelMap.put("msg", "添加成功");
			}else {
				modelMap.put("success", false);
				modelMap.put("msg", "添加失败");
			}
		}
			
		return modelMap;
	}
	
	@RequestMapping(value="/del",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap deleteOrder(@RequestParam(value="ids[]") String []ids){
		
		ModelMap modelMap = new ModelMap();
		
		int res = orderDao.deleteOrder(ids);
		
		if(res == ids.length){
			modelMap.put("success", true);
			modelMap.put("msg", "删除成功");
		}else{
			modelMap.put("success", false);
			modelMap.put("msg", "删除失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap updateOrder(Order order){
		
		ModelMap modelMap = new ModelMap();
		
		int res = orderDao.updateOrder(order);
		
		if(res != 0){
			modelMap.put("success", true);
			modelMap.put("msg", "修改成功");
		}else{
			modelMap.put("success", true);
			modelMap.put("msg", "修改失败");
		}
		return modelMap;
		
	}
	
	@RequestMapping(value="/byid",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getOrderById(String id){
		
		ModelMap modelMap = new ModelMap();
		
		Order order = orderDao.getOrderById(id);
		
		if(order != null){
			
			modelMap.put("success", true);
			modelMap.put("result", order);
			
		}else{
			
			modelMap.put("success", false);
		
		}
		
		return modelMap;
		
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getAllUser(int page,int rows){
		
		ModelMap modelMap = new ModelMap();
		
		SearchEntity searchEntity = new SearchEntity(Order.class);
		searchEntity.addResultColumn("*");
		searchEntity.setPage(page, rows);
		
		List<Order> users = orderDao.getAllOrder(searchEntity);
		
		if(users != null){
			modelMap.put("success", true);
			modelMap.put("rows", users);
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
}
