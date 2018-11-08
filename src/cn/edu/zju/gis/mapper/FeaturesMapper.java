package cn.edu.zju.gis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.edu.zju.gis.po.Features;
import cn.edu.zju.gis.po.FeaturesVo;

public interface FeaturesMapper {

	List<Features> findInfoByLayer(Integer id );
	List<Features> findLineInfoByLayer(Integer id); 
	
	int insertFeature(Features feature);

	Features findFeaturesDetail(FeaturesVo queryfeature);
}