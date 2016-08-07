package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.dao.ProductionDao;
import com.yf.dao.SearchEntity;
import com.yf.model.ProductionNode;
import com.yf.utils.StringUtils;

@Controller
@RequestMapping("/productionnode")
public class ProductNodeController {

	@Resource(name="productionDao")
	private ProductionDao productionDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addProductionNode(ProductionNode productionNode){
		
		ModelMap modelMap = new ModelMap();
		
		productionNode.setId(StringUtils.generateUuid());
		
		int res = productionDao.insertProduction(productionNode);
		
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
	public ModelMap deleteProductionNode(@RequestParam(value="ids[]") String []ids){
		
		ModelMap modelMap = new ModelMap();
		
		int res = productionDao.deleteProduction(ids);
		
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
	public ModelMap updateProductionNode(ProductionNode productionNode){
		
		ModelMap modelMap = new ModelMap();
		
		int res = productionDao.updateProduction(productionNode);
		
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
	public ModelMap getProductionNodeById(String id){
		
		ModelMap modelMap = new ModelMap();
		
		ProductionNode productionNode = productionDao.getProductionNodeById(id);
		
		if(productionNode != null){
			
			modelMap.put("success", true);
			modelMap.put("result", productionNode);
			
		}else{
			
			modelMap.put("success", false);
		
		}
		
		return modelMap;
		
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getAllProductionNode(int page,int rows){
		
		ModelMap modelMap = new ModelMap();
		
		SearchEntity searchEntity = new SearchEntity(ProductionNode.class);
		searchEntity.addResultColumn("*");
		searchEntity.setPage(page, rows);
		
		List<ProductionNode> users = productionDao.getAllProduction(searchEntity);
		
		if(users != null){
			modelMap.put("success", true);
			modelMap.put("rows", users);
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 获取所有的节点，移动端使用
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getnode")
	@ResponseBody
	public ModelMap getAllProduction(HttpServletRequest request,HttpServletResponse response){
		
		ModelMap modelMap = new ModelMap();
		List<ProductionNode> nodes = productionDao.getAll();
		
		if(nodes != null){
			
			modelMap.put("success", true);
			modelMap.put("list", nodes);
			
		}else{
			
			modelMap.put("success", false);
			
		}
		return modelMap;
	}
}
