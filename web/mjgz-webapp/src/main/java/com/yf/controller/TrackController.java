package com.yf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yf.dao.SearchEntity;
import com.yf.dao.TrackDao;
import com.yf.model.Track;
import com.yf.utils.StringUtils;

public class TrackController {

	@Resource(name="trackDao")
	private TrackDao trackDao;
	
	/**
	 * tianjia
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap addTrack(Track track){
		
		ModelMap modelMap = new ModelMap();
		
		track.setId(StringUtils.generateUuid());
		
		int res = trackDao.insertTrack(track);
		
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
