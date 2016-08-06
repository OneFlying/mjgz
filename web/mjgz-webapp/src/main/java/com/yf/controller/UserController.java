package com.yf.controller;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.*;

import com.yf.dao.SearchEntity;
import com.yf.dao.UserDao;
import com.yf.model.User;
import com.yf.utils.StringUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name="userDao")
	private UserDao userDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addUser(User user){
		
		ModelMap modelMap = new ModelMap();
		
		user.setId(StringUtils.generateUuid());
		
		int res = userDao.insertUser(user);
		
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
	public ModelMap deleteUser(@RequestParam(value="ids[]") String []ids){
		
		ModelMap modelMap = new ModelMap();
		
		int res = userDao.deleteUser(ids);
		
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
	public ModelMap updateUser(User user){
		
		ModelMap modelMap = new ModelMap();
		
		int res = userDao.updateUser(user);
		
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
		
		User user = userDao.getUserById(id);
		
		if(user != null){
			
			modelMap.put("success", true);
			modelMap.put("result", user);
			
		}else{
			
			modelMap.put("success", false);
		
		}
		
		return modelMap;
		
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getAllUser(int page,int rows){
		
		ModelMap modelMap = new ModelMap();
		
		SearchEntity searchEntity = new SearchEntity(User.class);
		searchEntity.addResultColumn("*");
		searchEntity.setPage(page, rows);
		
		List<User> users = userDao.getUsers(searchEntity);
		
		if(users != null){
			modelMap.put("success", true);
			modelMap.put("rows", users);
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
}
