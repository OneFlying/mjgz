package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.constant.OrderConstant;
import com.yf.constant.OrderNodeConstant;
import com.yf.dao.OrderDao;
import com.yf.dao.OrderNodeDao;
import com.yf.dao.SearchEntity;
import com.yf.dao.TrackDao;
import com.yf.model.Order;
import com.yf.model.OrderNode;
import com.yf.model.Track;
import com.yf.utils.StringUtils;

@Controller
@RequestMapping("/track")
public class TrackController {

	@Resource(name="trackDao")
	private TrackDao trackDao;
	
	@Resource(name="orderNodeDao")
	private OrderNodeDao orderNodeDao;
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/node/start",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addTrack(Track track){
		
		ModelMap modelMap = new ModelMap();
		
		//获取当前的日期
		String starttime = StringUtils.getCurTimeFormat();
		String endtime = starttime;
		
		//判断扫描的是否是结束标志节点		
		Order order = orderDao.getOrderById(track.getCode());
		
		if(order != null){
			//判断该订单是否已经 结束
			if(order.getStatus() == OrderConstant.ORDER_END){
				modelMap.put("success", false);
				modelMap.put("msg", "该订单已经结束");		
				return modelMap;
			}
		}
		
		
		if(track.getNode().equals("结束")){			
						
			//更新最后一个节点的状态
			OrderNode orderNode = orderNodeDao.getOrderNode(order.getNode(), order.getId());
			orderNode.setStatus(OrderNodeConstant.NODE_END);
			orderNode.setEndtime(endtime);
			orderNodeDao.updateOrderNode(orderNode);
			
			//更新订单状态  为结束
			order.setStatus(OrderConstant.ORDER_END);
			orderDao.updateOrder(order);
			
			modelMap.put("success", true);
			modelMap.put("msg", "订单结束成功");
			return modelMap;
		}
		
		//1.如果操作的节点不存在则返回失败 
		OrderNode orderNode_ = orderNodeDao.getOrderNode(track.getNode(), track.getCode());	
		if(orderNode_ == null){
			modelMap.put("success", false);
			modelMap.put("msg", "该订单不存在该流程节点");
			return modelMap;
		}
			
		//判断该节点是否已经扫描过
		Track exsit_track = trackDao.workNameIsExist(track.getNode(), track.getCode());
		if(exsit_track != null){
			modelMap.put("success", false);
			modelMap.put("msg", "该节点已经扫描");
			return modelMap;
		}
		
			
		/**
		 * 如果是正常的状态下不是结束标志节点
		 * 则进行下面的操作
		 */
		//Order order = orderDao.getOrderById(track.getCode());				
		//2.自动结束上一次的流程
		if(StringUtils.isNotBlank(order.getNode())){
			//找到订单上次执行的流程节点
			OrderNode orderNode = orderNodeDao.getOrderNode(order.getNode(), order.getId());
			orderNode.setStatus(OrderNodeConstant.NODE_END);
			orderNode.setEndtime(endtime);
			orderNodeDao.updateOrderNode(orderNode);
		}else{
			//如果为空说明订单开始要执行 修改订单状态
			order.setStatus(OrderConstant.ORDER_EXCUTING);
			orderDao.updateOrder(order);
		}
		 
		//3.设置订单表中新的要进行的节点		 
		order.setNode(track.getNode());
		orderDao.updateOrder(order);
		
		//4.更新流程节点的开始时间 
		OrderNode orderNode = orderNodeDao.getOrderNode(track.getNode(), track.getCode());		
		if(orderNode != null){
			//修改节点的开始时间
			orderNode.setStarttime(starttime);
			//修改节点的状态
			orderNode.setStatus(OrderNodeConstant.NODE_EXCUTING);
			orderNodeDao.updateOrderNode(orderNode);
		}
		
		track.setId(StringUtils.generateUuid());
		track.setTime(starttime);
		
		int res = trackDao.insertTrack(track);
		
		if(res != 0){
			modelMap.put("success", true);
			modelMap.put("msg", "扫描成功");
		}else {
			modelMap.put("success", false);
			modelMap.put("msg", "扫描失败");
		}
		
		return modelMap;
	}
	
	
	@RequestMapping(value="/del",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap deleteTrack(@RequestParam(value="ids[]") String []ids){
		
		ModelMap modelMap = new ModelMap();
		
		int res = trackDao.deleteTrack(ids);
		
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
	public ModelMap updateTrack(Track track){
		
		ModelMap modelMap = new ModelMap();
		
		int res = trackDao.updateTrack(track);
		
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
	public ModelMap getUserById(String id){
		
		ModelMap modelMap = new ModelMap();
		
		Track track = trackDao.getTrackById(id);
		
		if(track != null){
			
			modelMap.put("success", true);
			modelMap.put("result", track);
			
		}else{
			
			modelMap.put("success", false);
		
		}
		
		return modelMap;
		
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getAllUser(int page,int rows){
		
		ModelMap modelMap = new ModelMap();
		
		SearchEntity searchEntity = new SearchEntity(Track.class);
		searchEntity.addResultColumn("*");
		searchEntity.setPage(page, rows);
		
		List<Track> users = trackDao.getAllTracks(searchEntity);
		
		if(users != null){
			modelMap.put("success", true);
			modelMap.put("rows", users);
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
}
