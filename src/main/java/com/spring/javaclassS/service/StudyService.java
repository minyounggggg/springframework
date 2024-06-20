package com.spring.javaclassS.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.javaclassS.vo.UserVO;

public interface StudyService {

	public String[] getCityStringArray(String dodo);

	public ArrayList<String> getCityArrayList(String dodo);

	public HashMap<Object, Object> getUserData(String user);

	public UserVO getUserMidSearch(String mid);


	

}
