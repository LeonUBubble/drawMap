package cn.edu.zju.gis.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.gis.mapper.FeaturesMapper;
import cn.edu.zju.gis.mapper.LayersMapper;
import cn.edu.zju.gis.po.Features;
import cn.edu.zju.gis.po.FeaturesVo;
import cn.edu.zju.gis.po.Layers;
import cn.edu.zju.gis.po.LayersVo;
import cn.edu.zju.gis.service.FeaturesService;
import cn.edu.zju.gis.util.BgeoCoder;

public class FeaturesServiceImpl implements FeaturesService
{
	@Autowired
	private FeaturesMapper featuresMapper;
	
	@Override
	public int deleteFeatures(Integer layerid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertFeatures(Integer layerid, BufferedReader bufferedReader, int type, boolean hasXY, String[] titles)
			throws Exception 
			{
	Integer count = null;
	switch (type) {//根据图层选择处理方法
		case 0://
			count = AnalyseCSV0(layerid,bufferedReader, hasXY, titles);
			break;
		case 1://�ȼ�����ͼ
			count = AnalyseCSV0(layerid,bufferedReader, hasXY, titles);
			break;
		case 2://��ͼ
			count = AnalyseCSV0(layerid,bufferedReader, hasXY , titles);
			break;
		//�켣ͼ�Ļ���������
		case 3://�켣ͼ
			count = AnalyseCSV3(layerid,bufferedReader, titles);
			break;
		default:
			break;
	}		
	bufferedReader.close();
	return count;
}

public Integer AnalyseCSV0(Integer layerid,BufferedReader bufferedReader,boolean hasXY, String[] titles) throws NumberFormatException, FileNotFoundException, IOException 
{
	Integer count = 0;
	if (null==layerid) return 0;
	String line = null;//
	int i;	
	while((line=bufferedReader.readLine())!=null) {
		String content = "";
		i = 0;
		line = line.trim();
		if(line.length() > 0) {
			count++;
			Features feature = new Features();
			feature.setLayerid(layerid);
			feature.setFeatureid(count);
			String items[] = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			content += "{";
			if(hasXY) {//判断是否有XY列
				for(String item : items) {//直接开始解析
	            	if(i != 0) {
	            		content += ",";
	            	}
	            	switch(titles[i]){
	            	case "name":
	            		feature.setName(item);
	            		break;
	            	case "X":
	            		feature.setX(Double.parseDouble(item));
	            		break;
	            	case "Y":
	            		feature.setY(Double.parseDouble(item));
	            		break;
	            	case "value":
	            		feature.setValue(item);
	            		break;
	            	}
	            	content += "\"" + titles[i] + "\":" + "\"" +item +"\"";
	            	i++;
	            }
	            content += "}";
	            feature.setJsondata(content);
			}
			else {//没有XY坐标 则需要进行地理匹配
				for(String item : items) {//直接开始解析
	            	if(i != 0) {
	            		content += ",";
	            	}
	            	switch(titles[i]){
	            	case "name":
	            		feature.setName(item);
	            		break;

	            	case "value":
	            		feature.setValue(item);
	            		break;
	            	}
	            	content += "\"" + titles[i] + "\":" + "\"" +item +"\"";
	            	i++;
	            }
	            content += "}";
	            feature.setJsondata(content);
		        BgeoCoder bg = new BgeoCoder();  
	            		double[] latlng = null;
	            		latlng = bg.getLatlng(feature.getName());
				       				
	            		if(latlng!=null) {//ƥ�䵽�� ����뾭γ��
	            			feature.setX(latlng[1]);
	            			feature.setY(latlng[0]);
	            		}else {//δƥ�䵽�� �����""
	            			feature.setX(999.0);
	            			feature.setY(999.0);
	            		}
	            				       
	            }
			featuresMapper.insertFeature(feature);
		}
		
			
		}
	return count;
	
}

public Integer AnalyseCSV3(Integer layerid,BufferedReader bufferedReader,String titles[]) throws IOException {
	Integer count = 0;
	String line = null;//读取bufferedReader的每一行		
	int i;	
	//判断是直接解析the_geom还是地理位置匹配
	boolean isGeom = true;
	int geom_index = -1;
	for(int k=0;k<titles.length;k++) {
		if(titles[k].equals("地名")) {
			isGeom = false;
			geom_index = k;
			break;
		}
		if(titles[k].equals("the_geom")) {
			geom_index = k;
			break;
		}
	}

	BgeoCoder bg = new BgeoCoder(); //为解析做准备
	while((line = bufferedReader.readLine())!=null) {
		i = 0;
		line = line.trim();
		if(line.length() > 0) {
			String content = "";
			String items[] = line.split(",");
			content += "{";
			count++;
			
			Features feature = new Features();
			feature.setLayerid(layerid);
			feature.setFeatureid(count);
			for(String item : items) {
            	if(i != 0) {
            		content += ",";
            	}
            	if(isGeom && (i == geom_index)) {//直接解析the_geom
            		String[] geomItems = item.split(":");
            		String linegeom = "";
            		content += "\"coords\":";
            		linegeom += "[[";
            		int p = 0;
            		for(String geomItem:geomItems) {
            			if(p!=0) {
            				linegeom +=  ",[";
            			}
            			geomItem = geomItem.trim();
            			geomItem = geomItem.replace(" ", ",");
            			System.out.println("坐标："+geomItem);
            			
            			linegeom += geomItem;
            				   
            			linegeom += "]";
            			p++;
            		}
            		linegeom += "]";
            		content += linegeom;
            		feature.setLinegeom(linegeom);
            	}
            	else if(!isGeom&&(i == geom_index)) {//地名解析
            		String[] geomItems = item.split(":");
            		content += "\"coordinates\":";
            		String linegeom = "[[";
            		int p = 0;
            		for(String geomItem:geomItems) {
            			if(p!=0) {
            				linegeom += ",[";
            			}
            			geomItem = geomItem.trim();
            			double[] latlng = null;
	            		latlng = bg.getLatlng(geomItem);
				        //获取地址经纬度信息  	
	            		if(latlng!=null) {//匹配到点 则加入经纬度
	            			linegeom +=  latlng[1]+ 
		            				"," + latlng[0];;
	            		}else {//未匹配到点 则加入""
	            			linegeom +=  "" + 
		            				"," + "";
	            		}
	            		linegeom += "]";
	            		p++;
            		}
            		linegeom += "]";
            		content += linegeom;
            		feature.setLinegeom(linegeom);
            	}
            	else {
            		switch(titles[i]){
	            	case "name":
	            		feature.setName(item);
	            		break;

	            	case "value":
	            		feature.setValue(item);
	            		break;
	            	}
            		content +="\""+ titles[i] + "\":\"" +item + "\"";
            	}  	
            	i++;
            }
            content += "}";
            feature.setJsondata(content);
            featuresMapper.insertFeature(feature);
		}
	}
	return count;
}

@Override
public List<Features> getFeaturesByLayer(Layers layer) throws Exception {
	
	if(layer.getType()==3) return  featuresMapper.findLineInfoByLayer(layer.getId() );
	else
	return featuresMapper.findInfoByLayer(layer.getId()); 
	
}

@Override
public Features getFeaturesDetail(FeaturesVo queryfeature) throws Exception {
	
	return featuresMapper.findFeaturesDetail(queryfeature); 
}

}

