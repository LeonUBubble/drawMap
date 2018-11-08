package cn.edu.zju.gis.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import cn.edu.zju.gis.po.Maps;
import cn.edu.zju.gis.po.MapsVo;
import cn.edu.zju.gis.po.Users;
import cn.edu.zju.gis.po.UsersVo;
import cn.edu.zju.gis.service.MapsService;
import cn.edu.zju.gis.service.UsersService;

@Controller
public class AdminController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private MapsService mapsService;
	/*
	 * 这里的action都要经过checkAdmin的权限验证
	 * 以下的请求是配合bootstrap的table插件写的
	 * getUserList 获取用户列表 并且配合ban和passUser来实现用户的封停和解封
	 * getMapList2(其实应该改名叫getMapListForAdmin的) 配合ban和passMap来由管理员设定地图的可见性(也就是地图审核)
	 * 管理员并没有删除数据的权限 如果要删除权限 需要联络数据库管理员
	*/
	
	@RequestMapping(value = "/getUserList",method = RequestMethod.GET,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getUserList(UsersVo user,HttpSession session,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		try {
			String newMapName = new String(user.getUsername().getBytes("ISO8859-1"), "UTF-8");
			user.setUsername(newMapName);
			if(usersService.checkAdmin(session))
			{
				String res="";
				Gson gson = new Gson();
				List<Users> users = usersService.findUsers(user);
				res = gson.toJson(users);
				int total = usersService.countUsers();
				
				return "{\"total\":"+total+",\"rows\":"+res+"}";
			}
			else return "fail";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@RequestMapping(value = "/getMapList2", method = RequestMethod.GET,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getMapList2(MapsVo querymap,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		try {
			String newMapName = new String(querymap.getMapname().getBytes("ISO8859-1"), "UTF-8");
			querymap.setMapname(newMapName);
			if(usersService.checkAdmin(session)){
				List<MapsVo> maps = mapsService.getMapList2(querymap);
				Gson gson = new Gson();
				String rows = gson.toJson(maps);
				int count = mapsService.countMaps(querymap);
				return "{\"total\":"+count+",\"rows\":"+rows+"}";
			}
			else return "fail";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	@RequestMapping(value = "/passMap", method = RequestMethod.POST,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String passMap(String mapList,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(usersService.checkAdmin(session)){
			Gson gson = new Gson();
			ArrayList<Integer> idList= gson.fromJson(mapList,new TypeToken<ArrayList<Integer>>(){}.getType());
			for(Integer id : idList)
			{
				mapsService.passMap(id);
			}
			return "success";
		}
		else return "fail";
	}
	@RequestMapping(value = "/banMap", method = RequestMethod.POST,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String banMap(String mapList,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(usersService.checkAdmin(session)){
			Gson gson = new Gson();
			ArrayList<Integer> idList= gson.fromJson(mapList,new TypeToken<ArrayList<Integer>>(){}.getType());
			for(Integer id : idList)
			{
				mapsService.banMap(id);
			}
			return "success";
		}
		else return "fail";
	}
	
	@RequestMapping(value = "/passUser", method = RequestMethod.POST,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String passUser(String userList,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(usersService.checkAdmin(session)){
			Gson gson = new Gson();
			ArrayList<Integer> idList= gson.fromJson(userList,new TypeToken<ArrayList<Integer>>(){}.getType());
			for(Integer id : idList)
			{
				usersService.passUser(id);
			}
			return "success";
		}
		else return "fail";
	}
	
	@RequestMapping(value = "/banUser", method = RequestMethod.POST,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String banUser(String userList,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		if(usersService.checkAdmin(session)){
			Gson gson = new Gson();
			ArrayList<Integer> idList= gson.fromJson(userList,new TypeToken<ArrayList<Integer>>(){}.getType());
			for(Integer id : idList)
			{
				usersService.banUser(id);
			}
			return "success";
		}
		else return "fail";
	}
}	