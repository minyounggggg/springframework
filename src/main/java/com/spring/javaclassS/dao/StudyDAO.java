package com.spring.javaclassS.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.javaclassS.vo.UserVO;

public interface StudyDAO {

	public UserVO getUserMidSearch(@Param("mid")String mid);

}
