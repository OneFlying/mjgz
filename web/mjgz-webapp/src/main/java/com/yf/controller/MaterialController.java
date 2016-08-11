package com.yf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
			modelMap.put("total",searchEntity.getTotal());
		}else{
			modelMap.put("success", false);
		}
		return modelMap;
	}
	
	/**
	 * 图纸上传
	 * @return
	 */
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadImg(HttpServletRequest request) throws Exception{
		
		//ModelAndView modelAndView = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		//第一步转换request
		MultipartHttpServletRequest mtr = (MultipartHttpServletRequest)request;
		//接受文件
		//这里的pic是表单中的name="pic"
		CommonsMultipartFile cmf = (CommonsMultipartFile) mtr.getFile("pic");
		//获得字节数组
		byte[] bfile = cmf.getBytes();
		
		//设置文件名
		String fileName = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		fileName = format.format(new Date());
		//获得3为随机数
		Random random = new Random();
		
		for(int i = 0 ; i < 3 ; i++)
		{
			fileName = fileName + random.nextInt(9);
		}
		
		//获取原始文件名
		String original = cmf.getOriginalFilename();
		//获取后缀
		String suffix = original.substring(original.lastIndexOf("."));
		
		//拿到项目的部署路径
		String path = request.getSession().getServletContext().getRealPath("/");
		
		String realPath = path+"/upload/"+fileName+suffix;
		
		//url访问的路径
		String urlPath = fileName+suffix;
		
		OutputStream out = new FileOutputStream(new File(realPath));
		
		//开始写出
		out.write(bfile);
		out.flush();
		out.close();
		
		modelMap.put("success", true);
		modelMap.put("logourl", urlPath);
		
		return modelMap;
		
	}
}
