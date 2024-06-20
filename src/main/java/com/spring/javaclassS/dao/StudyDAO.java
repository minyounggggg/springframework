package com.spring.javaclassS.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javaclassS.vo.CrimeVO;
import com.spring.javaclassS.vo.UserVO;

public interface StudyDAO {

	public UserVO getUserMidSearch(@Param("mid")String mid);

	public ArrayList<UserVO> getUserMidList(@Param("mid")String mid);

	public void setSaveCrimeData(@Param("vo")CrimeVO vo);

}
