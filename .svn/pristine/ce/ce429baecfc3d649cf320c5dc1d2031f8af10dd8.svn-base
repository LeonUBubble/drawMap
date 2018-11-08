package cn.edu.zju.gis.service;

import java.io.BufferedReader;
import java.util.List;

import cn.edu.zju.gis.po.Features;
import cn.edu.zju.gis.po.FeaturesVo;
import cn.edu.zju.gis.po.Layers;
import cn.edu.zju.gis.po.LayersVo;
import cn.edu.zju.gis.po.Maps;

public interface FeaturesService {
	public int insertFeatures(Integer layerid,BufferedReader bufferedReader,int type,boolean hasXY,String[] titles) throws Exception;
	public int deleteFeatures(Integer layerid) throws Exception;
	public List<Features> getFeaturesByLayer(Layers layer) throws Exception;
	public Features getFeaturesDetail(FeaturesVo queryfeature) throws Exception; 
}
