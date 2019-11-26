package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.HospitalIntroduction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HospitalIntroductionMapper extends tk.mybatis.mapper.common.Mapper<HospitalIntroduction> {

	@Select({
			"SELECT * " +
					"FROM hospital_introduction " +
					"ORDER BY sort ASC"
	})
	List<HospitalIntroduction> listHospitalIntroductionBySort();

	@Delete({
			"<script>" +
					"DELETE FROM hospital_introduction " +
					"WHERE id IN " +
					"<foreach collection=\"idList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
					"#{item}" +
					"</foreach>" +
			"</script>"
	})
	Integer deleteByIds(@Param("idList") List<String> idList);


	@Select({
			"SELECT content " +
					"FROM hospital_introduction " +
					"WHERE type = 1"
	})
	HospitalIntroduction getHospitalIntroduction();

	@Select({
			"SELECT content " +
					"FROM hospital_introduction " +
					"WHERE type = 2"
	})
	List<HospitalIntroduction> getHospitalCarousel();

}
