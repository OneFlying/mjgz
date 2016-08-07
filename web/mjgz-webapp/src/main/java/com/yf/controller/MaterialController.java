package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.dao.MaterialDao;
import com.yf.dao.SearchEntity;
import com.yf.model.Material;
import com.yf.utils.StringUtils;

@Controller
@RequestMapping("/material")
public class MaterialController {

	@Resource(name="materialDao")
	private MaterialDao materialDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addMaterial(Material material){
		
		ModelMap modelMap = new ModelMap();
		
		material.setId(StringUtils.generateUuid());
		
		int res = materialDao.insertMaterial(material);
		
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
	public ModelMap deleteMaterial(@RequestParam(value="ids[]") String []ids){
		
		ModelMap modelMap = new ModelMap();
		
		int res = materialDao.deleteMaterial(ids);
		
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
	public ModelMap updateMaterial(Material material){
		
		ModelMap modelMap = new ModelMap();
		
		int res = materialDao.updateMaterial(material);
		
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
	public ModelMap getMaterialById(String id){
		
		ModelMap modelMap = new ModelMap();
		
		Material material = materialDao.getMaterialById(id);
		
		if(material != null){
			
			modelMap.put("success", true);
			modelMap.put("result", material);
			
		}else{
			
			modelMap.put("success", false);
		
		}
		
		return modelMap;
		
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	@ResponseBody
	public ModelMap getAllMaterial(int page,int rows){
		
		ModelMap modelMap = new ModelMap();
		
		SearchEntity searchEntity = new SearchEntity(Material.class);
		searchEntity.addResultColumn("*");
		searchEntity.setPage(page, rows);
		
		List<Material> users = materialDao.getAllMaterial(searchEntity);
		
		if(users != null){
			modelMap.put("success", true);
			modelMap.put("rows", users);
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
}
