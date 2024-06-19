package com.spring.javaclassS.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.spring.javaclassS.vo.UserVO;

@Service
public interface DbtestService {

	public ArrayList<UserVO> getDbtestList();

	public ArrayList<UserVO> getDbtestSearch(String mid);

	public int setDbtestDelete(int idx);

	public int setDbtestInputOk(UserVO vo);

	public int setDbtestUpdateOk(UserVO vo);

	public UserVO setDbtestWindow(String mid);

}
