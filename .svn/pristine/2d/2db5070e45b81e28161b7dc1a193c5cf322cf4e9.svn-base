package cn.edu.zju.gis.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.edu.zju.gis.po.Features;
import cn.edu.zju.gis.po.FeaturesVo;
import cn.edu.zju.gis.service.FeaturesService;

@Controller
public class FeaturesController {
	@Autowired
	private FeaturesService featuresService;
	
	
	@RequestMapping(value = "/getFeaturesDetail", method = RequestMethod.POST,   
	        produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getFeaturesDetail(String index,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*"); Gson gson = new Gson();
		
        List<Integer> indexL = gson.fromJson(index, new TypeToken<List<Integer>>()
        {}.getType());
		
		FeaturesVo queryfeature = new FeaturesVo();
		queryfeature.setLayerid(indexL.get(0));
		
		queryfeature.setFeatureid(indexL.get(1));
		
		Features resf = featuresService.getFeaturesDetail(queryfeature);
		
		return gson.toJson(resf);
	}
}	