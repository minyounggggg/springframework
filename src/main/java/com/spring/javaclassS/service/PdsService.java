package com.spring.javaclassS.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.javaclassS.vo.PdsVO;

public interface PdsService {

	public List<PdsVO> getPdsList(int startIndexNo, int pageSize, String part);

	public int setPdsUpload(MultipartHttpServletRequest mFile, PdsVO vo);

	public int setpdsDownNumPlus(int idx);

	public int setpdsDelete(int idx, String fSName, HttpServletRequest request);

	public PdsVO getPdsContent(int idx);

}
