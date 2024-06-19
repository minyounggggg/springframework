package com.spring.javaclassS.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javaclassS.vo.UserVO;

public interface DbtestDAO {

	public ArrayList<UserVO> getDbtestList();

	public ArrayList<UserVO> getDbtestSearch(@Param("mid") String mid);

	public int setDbtestDelete(@Param("idx")int idx);  //여기서사용하는변수, xml에서 쓰는 매개변수가 똑같을때 Param을 생략 가능 (하지만 헷갈리니까 쓰는걸 추천)

	public int setDbtestInputOk(@Param("vo") UserVO vo);

	public int setDbtestUpdateOk(@Param("vo") UserVO vo);

	public UserVO setDbtestWindow(@Param("mid") String mid);

}
