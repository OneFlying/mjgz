package com.yf.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.*;

import com.yf.dao.UserDao;
import com.yf.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private Logger logger = Logger.getLogger(getClass());
    
    @Resource(name="userDao")
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        logger.info("welcome");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request) {
        logger.info("welcome");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }
    
    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ModelMap loginUser(User loginuser){
    	
    	ModelMap modelMap = new ModelMap();
    	
    	User user = userDao.loginUser(loginuser);
    	
    	if(user != null){
    		
    		modelMap.put("success", true);
    		
    	}else{
    		
    		modelMap.put("success", false);
    		modelMap.put("msg", "用户名或密码错误");
    	}
    	return modelMap;
    }

}
