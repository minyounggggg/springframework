package com.spring.javaclassS.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.javaclassS.dao.BoardDAO;
import com.spring.javaclassS.vo.BoardReply2VO;
import com.spring.javaclassS.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;

	@Override
	public ArrayList<BoardVO> getBoardList() {
		return boardDAO.getBoardList();
	}

	@Override
	public int setBoardInput(BoardVO vo) {
		return boardDAO.setBoardInput(vo);
	}

	@Override
	public BoardVO getBoardContent(int idx) {
		return boardDAO.getBoardContent(idx);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(int startIndexNo, int pageSize) {
		return boardDAO.getBoardList(startIndexNo, pageSize);
	}

	@Override
	public void setReadNumPlus(int idx) {
		boardDAO.setReadNumPlus(idx);
	}

	@Override
	public BoardVO getPreNexSearch(int idx, String str) {
		return boardDAO.getPreNexSearch(idx, str);
	}

	// contentㅔ 이미지가 있다면 이미지를 ckeditor폴더에서 board폴더로 복사한다.
	@Override
	public void imgCheck(String content) {
		//				  0   		1  		  2			3		  4			5
		// 				  01234567890123456789012345678901234567890123456789012345
		// <p><img alt="" src="/javaclassS/data/ckeditor/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>
		// <p><img alt="" src="/javaclassS/data/board/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();	  //ServletRequestAttributes 로 형변환 후 사용
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/");
		
		int position = 31;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));
			
			String origFilePath = realPath + "ckeditor/" + imgFile;  //원본파일 경로명
			String copyFilePath = realPath + "board/" + imgFile; 
			
			fileCopyCheck(origFilePath, copyFilePath);	//ckeditor폴더의 그림파일을 board폴더 위치로 복사처리하는 메소드
			
			if(nextImg.indexOf("src=\"/") == -1) sw = false;
			else nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
		}
	}

	// 파일 복사처리
	private void fileCopyCheck(String origFilePath, String copyFilePath) {
		
		try {
			FileInputStream fis = new FileInputStream(new File(origFilePath));
			FileOutputStream fos = new FileOutputStream(new File(copyFilePath));
			
			byte[] b = new byte[2048]; 	//2k
			int cnt = 0;
			while((cnt = fis.read(b)) != -1) {	// 있을때 2024바이트만큼 없을때까지 빙글빙글 반복 
				fos.write(b, 0, cnt);
			}
			fos.flush(); 	//찌꺼기 남은거 다 저장
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void imgBackup(String content) {
		//	 			  0   		1  		  2			3		  4			5
		// 				  01234567890123456789012345678901234567890123456789012345
		// <p><img alt="" src="/javaclassS/data/board/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>
		// <p><img alt="" src="/javaclassS/data/ckeditor/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();	  //ServletRequestAttributes 로 형변환 후 사용
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/");
		
		int position = 28;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));
			
			String origFilePath = realPath + "board/" + imgFile;  //원본파일 경로명
			String copyFilePath = realPath + "ckeditor/" + imgFile; 
			
			fileCopyCheck(origFilePath, copyFilePath);	//ckeditor폴더의 그림파일을 board폴더 위치로 복사처리하는 메소드
			
			if(nextImg.indexOf("src=\"/") == -1) sw = false;
			else nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
		}
	}

	
	@Override
	public void imgDelete(String content) {
		//		  0   		1  		  2			3		  4			5
		// 				  01234567890123456789012345678901234567890123456789012345
		// <p><img alt="" src="/javaclassS/data/board/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>
		// <p><img alt="" src="/javaclassS/data/ckeditor/240626093408_panchu1.jpg" style="height:200px; width:200px" /></p>
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();	  //ServletRequestAttributes 로 형변환 후 사용
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/");
		
		int position = 28;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));
			
			String origFilePath = realPath + "board/" + imgFile;  //원본파일 경로명
			
			fileDelete(origFilePath);	//board폴더의 그림파일을 삭제힌다.
			
			if(nextImg.indexOf("src=\"/") == -1) sw = false;
			else nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
		}
	}

	// 서버에 존재하는 파일 삭ㅈ제처리
	private void fileDelete(String origFilePath) {
		File delFile = new File(origFilePath);
		if(delFile.exists()) delFile.delete();
	}

	@Override
	public int setBoardUpdate(BoardVO vo) {
		return boardDAO.setBoardUpdate(vo);
	}

	@Override
	public int setBoardDelete(int idx) {
		return boardDAO.setBoardDelete(idx);
	}

	@Override
	public BoardReply2VO getBoardParentReplyCheck(int boardIdx) {
		return boardDAO.getBoardParentReplyCheck(boardIdx);
	}

	@Override
	public int setBoardReplyInput(BoardReply2VO replyVO) {
		return boardDAO.setBoardReplyInput(replyVO);
	}

	@Override
	public List<BoardReply2VO> getBoardReply(int idx) {
		return boardDAO.getBoardReply(idx);
	}

	@Override
	public void setReplyOrderUpdate(int boardIdx, int re_order) {
		boardDAO.setReplyOrderUpdate(boardIdx, re_order);
	}

	@Override
	public List<BoardVO> getBoardSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return boardDAO.getBoardSearchList(startIndexNo, pageSize, search, searchString);
	}
	
	
}
