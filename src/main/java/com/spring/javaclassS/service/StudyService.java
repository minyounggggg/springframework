package com.spring.javaclassS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.javaclassS.vo.CrimeVO;
import com.spring.javaclassS.vo.KakaoAddressVO;
import com.spring.javaclassS.vo.UserVO;

public interface StudyService {

	public String[] getCityStringArray(String dodo);

	public ArrayList<String> getCityArrayList(String dodo);

	public HashMap<Object, Object> getUserData(String user);

	public UserVO getUserMidSearch(String mid);

	public ArrayList<UserVO> getUserMidList(String mid);

	public void setSaveCrimeData(CrimeVO vo);

	public int fileUpload(MultipartFile fName, String mid);

	public int multiFileUpload(MultipartHttpServletRequest mFile);

	public Map<String, Integer> analyzer(String content);

	public KakaoAddressVO getKakaoAdderssSearch(String address);

	public void setKakaoAddressInput(KakaoAddressVO vo);

	public List<KakaoAddressVO> getKakaoAddressList();

	public int setKakaoAddressDelete(String address);

	public String fileCsvToMysql(MultipartFile fName);

	

}
