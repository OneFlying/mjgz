package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.dao.OrderDao;
import com.yf.dao.SearchEntity;
import com.yf.model.Order;
import com.yf.utils.StringUtils;

public class OrderController {

	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addOrder(Order order){
		
		ModelMap modelMap = new ModelMap();
		
		order.setId(StringUtils.generateUuid());
		
		int res = orderDao.insertOrder(order);
		
		if(res != 0){
			modelMap.put("success", true);
			modelMap.put("msg", "添加成功");
		}else {
			modelMap.put("success", false);
			modelMap.put("msg", "添加失败");
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
