package com.spring.javaclassS.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.javaclassS.common.JavaclassProvide;
import com.spring.javaclassS.dao.StudyDAO;
import com.spring.javaclassS.vo.CrimeVO;
import com.spring.javaclassS.vo.UserVO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDAO studyDAO;
	
	@Autowired
	JavaclassProvide javaclassProvide;
	
	@Override
	public String[] getCityStringArray(String dodo) {
		String[] strArray = new String[100];
		
		if(dodo.equals("서울")) {
			strArray[0] = "강남구";
			strArray[1] = "강북구";
			strArray[2] = "강서구";
			strArray[3] = "강동구";
			strArray[4] = "서초구";
			strArray[5] = "관악구";
			strArray[6] = "종로구";
			strArray[7] = "영등포구";
			strArray[8] = "마포구";
			strArray[9] = "동대문구";
		}
		else if(dodo.equals("경기")) {
			strArray[0] = "수원시";
			strArray[1] = "안양시";
			strArray[2] = "안성시";
			strArray[3] = "평택시";
			strArray[4] = "시흥시";
			strArray[5] = "용인시";
			strArray[6] = "성남시";
			strArray[7] = "광명시";
			strArray[8] = "김포시";
			strArray[9] = "안산시";
		}
		else if(dodo.equals("충북")) {
			strArray[0] = "청주시";
			strArray[1] = "충주시";
			strArray[2] = "제천시";
			strArray[3] = "단양군";
			strArray[4] = "음성군";
			strArray[5] = "진천군";
			strArray[6] = "괴산군";
			strArray[7] = "증평군";
			strArray[8] = "옥천군";
			strArray[9] = "영동군";
		}
		else if(dodo.equals("충남")) {
			strArray[0] = "천안시";
			strArray[1] = "아산시";
			strArray[2] = "논산시";
			strArray[3] = "공주시";
			strArray[4] = "당진시";
			strArray[5] = "서산시";
			strArray[6] = "홍성군";
			strArray[7] = "청양군";
			strArray[8] = "계룡시";
			strArray[9] = "예산군";
		}
		
//		for(String s : strArray) {
//			System.out.println("s : " + s);
//			if(s == null) break;
//		}
		
		return strArray;
	}

	@Override
	public ArrayList<String> getCityArrayList(String dodo) {
		ArrayList<String> vos = new ArrayList<String>();
		
		if(dodo.equals("서울")) {
			vos.add("강남구");
			vos.add("강북구");
			vos.add("강서구");
			vos.add("강동구");
			vos.add("서초구");
			vos.add("관악구");
			vos.add("종로구");
			vos.add("영등포구");
			vos.add("마포구");
			vos.add("동대문구");
		}
		else if(dodo.equals("경기")) {
			vos.add("수원시");
			vos.add("안양시");
			vos.add("안성시");
			vos.add("평택시");
			vos.add("시흥시");
			vos.add("용인시");
			vos.add("성남시");
			vos.add("광명시");
			vos.add("김포시");
			vos.add("안산시");
		}
		else if(dodo.equals("충북")) {
			vos.add("청주시");
			vos.add("충주시");
			vos.add("제천시");
			vos.add("단양군");
			vos.add("음성군");
			vos.add("진천군");
			vos.add("괴산군");
			vos.add("증평군");
			vos.add("옥천군");
			vos.add("영동군");
		}
		else if(dodo.equals("충남")) {
			vos.add("천안시");
			vos.add("아산시");
			vos.add("논산시");
			vos.add("공주시");
			vos.add("당진시");
			vos.add("서산시");
			vos.add("홍성군");
			vos.add("청양군");
			vos.add("계룡시");
			vos.add("예산군");
		}
		
		return vos;
	}

	@Override
	public HashMap<Object, Object> getUserData(String user) {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		
		if(user.equals("최민영")) {
			map.put("mid", "cmy1234");
			map.put("age", "20");
			map.put("address", "청주");
		}
		else if(user.equals("김민영")) {
			map.put("mid", "kmy5234");
			map.put("age", "30");
			map.put("address", "대전");
		}
		else if(user.equals("최영민")) {
			map.put("mid", "cym6842");
			map.put("age", "40");
			map.put("address", "마산");
		}
		else if(user.equals("영민최")) {
			map.put("mid", "ymc4321");
			map.put("age", "50");
			map.put("address", "울산");
		}
		
		return map;
	}

	@Override
	public UserVO getUserMidSearch(String mid) {
		return studyDAO.getUserMidSearch(mid);
	}

	@Override
	public ArrayList<UserVO> getUserMidList(String mid) {
		return studyDAO.getUserMidList(mid);
	}

	@Override
	public void setSaveCrimeData(CrimeVO vo) {
		studyDAO.setSaveCrimeData(vo);
	}

	@Override
	public int fileUpload(MultipartFile fName, String mid) {
		int res = 0;
		
		// 파일이름 중복처리를 위해 uuid객체 활용
		UUID uid = UUID.randomUUID();
		String oFileName = fName.getOriginalFilename();
		String sFileName = mid + "_" + uid.toString().substring(0,8) + "_" + oFileName;		
		
		// 서버에 파일 올리기
		try {
			writeFile(fName, sFileName);
			res = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	private void writeFile(MultipartFile fName, String sFileName) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/fileUpload/");
		
		FileOutputStream fos = new FileOutputStream(realPath + sFileName);
		
		//fos.write(fName.getBytes());
		if(fName.getBytes().length != -1) {		// 파일이 존재할때, 없지 않을때 
			fos.write(fName.getBytes());
		}
		fos.flush();
		fos.close();
	}

	@Override
	public int multiFileUpload(MultipartHttpServletRequest mFile) {
		int res = 0;
		
		try {
			List<MultipartFile> fileList = mFile.getFiles("fName");
			String oFileNames = "";
			String sFileNames = "";
			int fileSizes = 0;
			
			for(MultipartFile file : fileList) {
				//System.out.println("원본파일 : " + file.getOriginalFilename());
				String oFileName = file.getOriginalFilename();
				String sFileName = javaclassProvide.saveFileName(oFileName);
				
				javaclassProvide.writeFile(file, sFileName, "fileUpload");	//오버라이드니까 예외더처리 throw말고  try catch
				
				oFileNames += oFileName + "/";
				sFileNames += sFileName + "/";
				fileSizes += file.getSize();
			}
			oFileNames = oFileNames.substring(0, oFileNames.length()-1);
			sFileNames = sFileNames.substring(0, sFileNames.length()-1);
			
			System.out.println("원본파일 : " + oFileNames);
			System.out.println("저장파일 : " + sFileNames);
			System.out.println("총사이즈 : " + fileSizes);
			
			res = 1;
			} catch (IOException e) {e.printStackTrace();}
			return res;
	}

	@Override
	public Map<String, Integer> analyzer(String content) {
		int wordFrequenciesToReturn = 10;	// 빈도 수 반환되는거 10개
		int minWordLength = 2;			// 글자수 2개
		
		Map<String, Integer> frequencyMap = new HashMap<>();
		
		String[] words = content.split("\\s+");	// 공백으로 자르기(정규식으로쓰기)
		
		for(String word : words) {
			if(word.length() >= minWordLength) {
				word = word.toLowerCase();
				frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
			}
		}
		
		return frequencyMap.entrySet().stream()
	          .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
	          .limit(wordFrequenciesToReturn)
	          .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
	}

	
	
	
}

	


