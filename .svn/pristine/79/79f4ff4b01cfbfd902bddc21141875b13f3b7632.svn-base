package cn.edu.zju.gis.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.edu.zju.gis.po.MapLayer;
import cn.edu.zju.gis.po.Maps;
import cn.edu.zju.gis.po.MapsCustom;
import cn.edu.zju.gis.po.Users;
import cn.edu.zju.gis.service.MapsService;
import cn.edu.zju.gis.service.UsersService;

@Controller
public class PagesController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private MapsService mapsService;
	
	@RequestMapping("/admin")
	public ModelAndView openAdminPanel(HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		ModelAndView modelAndView =  new ModelAndView();
		if(usersService.checkAdmin(session))
		{
			modelAndView.setViewName("adminPanel");
			return modelAndView;
		}
		else
		{
			modelAndView.setViewName("index");
			return modelAndView;
		}
	}
	
	@RequestMapping("/user")
	public ModelAndView openUserCenter(HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		ModelAndView modelAndView =  new ModelAndView();
		Integer userid = (Integer)session.getAttribute("userid");
		modelAndView.addObject(userid);
		if(userid!=0)
		modelAndView.setViewName("userCenter");
		else modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping("/openUpLayerPage")
	public ModelAndView openUpLayerPage(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.setViewName("uplayer");
		return modelAndView;
	}
	@RequestMapping("/openSearchMapPage")
	public ModelAndView openSearchMapPage(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.setViewName("searchMapPage");
		return modelAndView;
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
	
		ModelAndView modelAndView =  new ModelAndView();
		
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/main")
	public ModelAndView openmain(Integer mapid,HttpSession session,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		boolean userflag = false;//用户是否登陆
		boolean examinflag = false;//用户是否可以可以查看该地图
		String username =(String) session.getAttribute("username");
		Users nowuser = null;
		if(username==null) 
			{nowuser = new Users();
			nowuser.setId(0);}
		else
			nowuser = usersService.findUserByName(username);
		MapsCustom map=null;//地图初始化为空
		if(mapid==null)
			map= new MapsCustom(0,//默认id 0
					"new map",//默认地图名
					nowuser.getId(),//当前用户id
					1,//用户自定的地图可见性
					0,//暂时还没有basemap机制 有也打算整合到图层里面 basemap作为底图服务存在即可
					"{\"centerx\":110,\"centery\":40,\"zoomlevel\":5,\"mapmode\":0}",//初始化地图显示状态（bmap接口）
					0,//addable 审核属性（相当于是管理员认定的地图可见性） 只有通过审核之后才有（总觉得这个变量一开始不是用来干这个的
					"[{\"id\": 0,\"text\": \"new map\",\"type\":\"map\"}]",//初始化的地图图层树
					0);//初始化的地图类型 综合
		else
		{	
			Maps mapa = mapsService.findMapById(mapid);
			//开始进行权限验证
			//1.管理员
			if(usersService.checkAdmin(session)&&mapa.getAccessibility()==1) {
				userflag = true;
				examinflag = true;
			}
			//2.地图作者
			else if(nowuser.getId()==mapa.getUserid()) {
				userflag = true;
				examinflag = true;
			}
			//3.普通用户
			else if(mapa.getAddable()==1&&mapa.getAccessibility()==1&&usersService.checkUserAuthority(mapa.getUserid())) {
				userflag = true;
				examinflag = true;
			}//3.普通用户 查看未审核的公开地图
			else if(mapa.getAddable()==0&&mapa.getAccessibility()==1&&usersService.checkUserAuthority(mapa.getUserid())) {
				userflag = true;
				examinflag = false;
			}
			//4.游客 只能查看公开的通过审核的地图
			else if(mapa.getAddable()==1&&mapa.getAccessibility()==1&&nowuser.getId()==0) {
				userflag = true;
				examinflag = true;
			}
			//4.游客查看其他情况的地图——针对查看地图的用户中途注销的情况
			else if(nowuser.getId()==0) {
				userflag = false;
				examinflag = true;
			}
			if(examinflag==false)
			{
				ModelAndView modelAndView =  new ModelAndView();//构造model
				modelAndView.setViewName("noAuthority");//用户没有查看权限，切换到提示该地图未通过审核的页面
				return modelAndView;
			}
			if(userflag==false)
			{
				ModelAndView modelAndView =  new ModelAndView();//构造model
				modelAndView.setViewName("pleaseLogin");//用户未登录，切换到提示请登录的页面
				return modelAndView;
			}
			map = new MapsCustom(mapa);
			List<MapLayer> maplayerlist = mapsService.findMapLayerByMapId(mapid);
			map.setMaplayer(maplayerlist);
		}
		Gson gson = new Gson();
		String mapjson = gson.toJson(map);
		ModelAndView modelAndView =  new ModelAndView();//构造model
		modelAndView.addObject("map", mapjson);		
		modelAndView.setViewName("main");
		return modelAndView;
	}
	@RequestMapping("/about")
	public ModelAndView about(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		int loginflag = 0;
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.addObject("loginflag", loginflag);
		modelAndView.setViewName("about");
		return modelAndView;
	}
	@RequestMapping("/pleaseLogin")
	public ModelAndView pleaseLogin(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		int loginflag = 0;
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.addObject("loginflag", loginflag);
		modelAndView.setViewName("pleaseLogin");
		return modelAndView;
	}
}