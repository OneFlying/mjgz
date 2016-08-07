package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		//1.如果操作的节点不存在则返回失败 
		OrderNode orderNode_ = orderNodeDao.getOrderNode(track.getNode(), track.getCode());		
		if(orderNode_ == null){
			modelMap.put("success", false);
			return modelMap;
		}
		
		//获取当前的日期
		String starttime = StringUtils.getCurTimeFormat();
		
		Order order = orderDao.getOrderById(track.getCode());		
		
		//2.自动结束上一次的流程
		if(StringUtils.isNotBlank(order.getNode())){
			//找到订单上次执行的流程节点
			OrderNode orderNode = orderNodeDao.getOrderNode(order.getNode(), order.getId());
			orderNode.setStatus(OrderNodeConstant.NODE_END);
			orderNodeDao.updateOrderNode(orderNode);
		}
		
		//3.设置订单表中新的要进行的节点		 
		order.setNode(track.getNode());
		
		//4.更新流程节点的开始时间 
		OrderNode orderNode = orderNodeDao.getOrderNode(track.getNode(), track.getCode());		
		if(orderNode != null){		
			orderNode.setStarttime(starttime);
			orderNodeDao.updateOrderNode(orderNode);
		}
		
		track.setId(StringUtils.generateUuid());
		track.setTime(starttime);
		
		int res = trackDao.insertTrack(track);
		
		if(res != 0){
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
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
