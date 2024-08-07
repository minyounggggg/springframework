package com.spring.javaclassS.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.spring.javaclassS.common.JavaclassProvide;
import com.spring.javaclassS.dao.StudyDAO;
import com.spring.javaclassS.vo.ChartVO;
import com.spring.javaclassS.vo.CrimeVO;
import com.spring.javaclassS.vo.KakaoAddressVO;
import com.spring.javaclassS.vo.PetCafeVO;
import com.spring.javaclassS.vo.QrCodeVO;
import com.spring.javaclassS.vo.TransactionVO;
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

	@Override
	public KakaoAddressVO getKakaoAdderssSearch(String address) {
		return studyDAO.getKakaoAdderssSearch(address);
	}

	@Override
	public void setKakaoAddressInput(KakaoAddressVO vo) {
		studyDAO.setKakaoAddressInput(vo);
	}

	@Override
	public List<KakaoAddressVO> getKakaoAddressList() {
		return studyDAO.getKakaoAddressList();
	}

	@Override
	public int setKakaoAddressDelete(String address) {
		return studyDAO.setKakaoAddressDelete(address);
	}

	@Override
	public String fileCsvToMysql(MultipartFile fName) {
		String str = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/study/");
		
		// 작업할 원본 csv파일 업로드(원본파일이 utf-8파일이 아니라면 메모장에서 utf-8로 다시 저장해서 업로드시켜준다.
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(realPath + fName.getOriginalFilename());
			if(fName.getBytes().length != -1) {
				fos.write(fName.getBytes());
			}
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 업로드된 파일을 Line단위로 읽어와서 ','로 분리후 DB에 저장하기
		realPath = request.getSession().getServletContext().getRealPath("/resources/data/study/"+fName.getOriginalFilename());
		try {
			BufferedReader br = new BufferedReader(new FileReader(realPath));
			String line;
			int cnt = 0;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				cnt++;
				str += cnt + " : " + line + "<br>";
				String[] pet_cafe = line.split(",");
				PetCafeVO vo = new PetCafeVO();
				int k = 0;
				vo.setPlaceName(pet_cafe[k]); k++;
				vo.setCategory(pet_cafe[k]); k++;
				vo.setSido(pet_cafe[k]); k++;
				vo.setSigungu(pet_cafe[k]); k++;
				vo.setDong(pet_cafe[k]); k++;
				vo.setLatitude(Double.parseDouble(pet_cafe[k])); k++;
				vo.setLongitude(Double.parseDouble(pet_cafe[k])); k++;
				vo.setZipNum(Integer.parseInt(pet_cafe[k])); k++;
				vo.setRdnmAddress(pet_cafe[k]); k++;
				vo.setLnmAddress(pet_cafe[k]); k++;
				vo.setHomePage(pet_cafe[k]); k++;
				vo.setClosedDay(pet_cafe[k]); k++;
				vo.setOpenTime(pet_cafe[k]); k++;
				vo.setParking(pet_cafe[k]); k++;
				vo.setPlayPrice(pet_cafe[k]); k++;
				vo.setPetOK(pet_cafe[k]); k++;
				vo.setPetSize(pet_cafe[k]); k++;
				vo.setPetLimit(pet_cafe[k]); k++;
				vo.setInPlaceOK(pet_cafe[k]); k++;
				vo.setOutPlaceOK(pet_cafe[k]); k++;
				vo.setPlaceInfo(pet_cafe[k]); k++;
				vo.setPetExtraFee(pet_cafe[k]); k=0;
				
				// DB에 저장하기
				studyDAO.setPetCafe(vo);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String setQrCodeCreate(String realPath) {
		String qrCodeName = javaclassProvide.newNameCreate(2);
		String qrCodeImage = "";
		try {
			// QR 코드 안의 한글인코딩
			qrCodeImage = "생성된 QR코드명 : " + qrCodeName;	//인코딩 시켜줘야함
			qrCodeImage = new String(qrCodeImage.getBytes("UTF-8"), "ISO-8859-1");
			
			// qr코드 만들기
			QRCodeWriter qrCodeWriter = new QRCodeWriter();		// 라이브러리에의해 올라옴
			BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeImage, BarcodeFormat.QR_CODE, 200, 200); 		//BarcodeFormat.QR_CODE(구글에서 제공해줌), 폭 200 높이 200
			
			//MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();		//점의 밀도형식 , 기본컬러 (글자색:검정, 배경색:흰색)
			int qrCodeColor = 0xFF0000FF;	//int는 큰따옴표 x
			int qrCodeBackColor = 0xFFFFFFFF;
			
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrCodeColor, qrCodeBackColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig); 	//점의 밀도 형식으로 큐알코드를 만들겠다.
			
			// 렌더링된 큐알코드 이미지를 실제 그림파일로 만들어낸다.
			ImageIO.write(bufferedImage, "png", new File(realPath + qrCodeName + ".png"));
		} catch (IOException e) {		//Exception이거 쓰면 모든 에러가 여기서 나기 때문에 비추 그래서 IOException를 써서 에러를 따로따로 표시해준다.
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return qrCodeName;
	}

	@Override
	public String setQrCodeCreate1(String realPath, QrCodeVO vo) {
		String qrCodeName = javaclassProvide.newNameCreate(2);
		String qrCodeImage = "";
		try {
			// QR 코드 안의 한글인코딩
			qrCodeName += vo.getMid() + "_" + vo.getName() + "_" + vo.getEmail();
			qrCodeImage = "생성날짜슨 : " + qrCodeName.substring(0,4) + "년 " + qrCodeName.substring(4,6) + "월 " + qrCodeName.substring(6,8) + "일\n";	//인코딩 시켜줘야함
			qrCodeImage += "아이디 : " + vo.getMid() + "\n";
			qrCodeImage += "성명슨 : " + vo.getName() + "\n";
			qrCodeImage += "이메일 : " + vo.getEmail();
			qrCodeImage = new String(qrCodeImage.getBytes("UTF-8"), "ISO-8859-1");
			
			// qr코드 만들기
			QRCodeWriter qrCodeWriter = new QRCodeWriter();		// 라이브러리에의해 올라옴
			BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeImage, BarcodeFormat.QR_CODE, 200, 200); 		//BarcodeFormat.QR_CODE(구글에서 제공해줌), 폭 200 높이 200
			
			//MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();		//점의 밀도형식 , 기본컬러 (글자색:검정, 배경색:흰색)
			int qrCodeColor = 0xFF0000FF;	//int는 큰따옴표 x
			int qrCodeBackColor = 0xFFFFFFFF;
			
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrCodeColor, qrCodeBackColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig); 	//점의 밀도 형식으로 큐알코드를 만들겠다.
			
			// 렌더링된 큐알코드 이미지를 실제 그림파일로 만들어낸다.
			ImageIO.write(bufferedImage, "png", new File(realPath + qrCodeName + ".png"));
		} catch (IOException e) {		//Exception이거 쓰면 모든 에러가 여기서 나기 때문에 비추 그래서 IOException를 써서 에러를 따로따로 표시해준다.
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return qrCodeName;
	}

	@Override
	public String setQrCodeCreate2(String realPath, QrCodeVO vo) {
		String qrCodeName = javaclassProvide.newNameCreate(2);
		String qrCodeImage = "";
		try {
			// QR 코드 안의 한글인코딩
			qrCodeName += vo.getMoveUrl(); 	// 파일명+날짜
			qrCodeImage = vo.getMoveUrl();	// ulr만잇슴
			qrCodeImage = new String(qrCodeImage.getBytes("UTF-8"), "ISO-8859-1");
			
			// qr코드 만들기
			QRCodeWriter qrCodeWriter = new QRCodeWriter();		// 라이브러리에의해 올라옴
			BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeImage, BarcodeFormat.QR_CODE, 200, 200); 		//BarcodeFormat.QR_CODE(구글에서 제공해줌), 폭 200 높이 200
			
			//MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();		//점의 밀도형식 , 기본컬러 (글자색:검정, 배경색:흰색)
			int qrCodeColor = 0xFF0000FF;	//int는 큰따옴표 x
			int qrCodeBackColor = 0xFFFFFFFF;
			
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrCodeColor, qrCodeBackColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig); 	//점의 밀도 형식으로 큐알코드를 만들겠다.
			
			// 렌더링된 큐알코드 이미지를 실제 그림파일로 만들어낸다.
			ImageIO.write(bufferedImage, "png", new File(realPath + qrCodeName + ".png"));
		} catch (IOException e) {		//Exception이거 쓰면 모든 에러가 여기서 나기 때문에 비추 그래서 IOException를 써서 에러를 따로따로 표시해준다.
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return qrCodeName;
	}

	@Override
	public String setQrCodeCreate3(String realPath, QrCodeVO vo) {
		String qrCodeName = javaclassProvide.newNameCreate(2);
		String qrCodeImage = "";
		try {
			// QR 코드 안의 한글인코딩
			qrCodeName += vo.getMid() + "_" + vo.getMovieName() + "_" + vo.getMovieDate() + "_" + vo.getMovieTime() + "_" + vo.getMovieAdult() + "_" + vo.getMovieChild(); 	
			qrCodeImage = "구매자 ID : " + vo.getMid() + "\n";
			qrCodeImage += "영화제목 : " + vo.getMovieName() + "\n";
			qrCodeImage += "상영일자 : " + vo.getMovieDate() + "\n";
			qrCodeImage += "상영시간 : " + vo.getMovieTime() + "\n";
			qrCodeImage += "성인구매인원수 : " + vo.getMovieAdult() + "\n";
			qrCodeImage += "소인구매인원수 : " + vo.getMovieChild();
			qrCodeImage = new String(qrCodeImage.getBytes("UTF-8"), "ISO-8859-1");
			
			// qr코드 만들기
			QRCodeWriter qrCodeWriter = new QRCodeWriter();		// 라이브러리에의해 올라옴
			BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeImage, BarcodeFormat.QR_CODE, 200, 200); 		//BarcodeFormat.QR_CODE(구글에서 제공해줌), 폭 200 높이 200
			
			//MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();		//점의 밀도형식 , 기본컬러 (글자색:검정, 배경색:흰색)
			int qrCodeColor = 0xFF0000FF;	//int는 큰따옴표 x
			int qrCodeBackColor = 0xFFFFFFFF;
			
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrCodeColor, qrCodeBackColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig); 	//점의 밀도 형식으로 큐알코드를 만들겠다.
			
			// 렌더링된 큐알코드 이미지를 실제 그림파일로 만들어낸다.
			ImageIO.write(bufferedImage, "png", new File(realPath + qrCodeName + ".png"));
			//System.out.println("QR Code : " + qrCodeName);
		} catch (IOException e) {		//Exception이거 쓰면 모든 에러가 여기서 나기 때문에 비추 그래서 IOException를 써서 에러를 따로따로 표시해준다.
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return qrCodeName;
	}

	@Override
	public String setQrCodeCreate4(String realPath, QrCodeVO vo) {
		String qrCodeName = javaclassProvide.newNameCreate(2);
		String qrCodeImage = "";
		try {
			String strToday = qrCodeName.substring(0, qrCodeName.length()-3);
			
			// QR 코드 안의 한글인코딩
			qrCodeName += vo.getMid() + "_" + vo.getMovieName() + "_" + vo.getMovieDate() + "_" + vo.getMovieTime() + "_" + vo.getMovieAdult() + "_" + vo.getMovieChild(); 	
			qrCodeImage = "구매자 ID : " + vo.getMid() + "\n";
			qrCodeImage += "영화제목 : " + vo.getMovieName() + "\n";
			qrCodeImage += "상영일자 : " + vo.getMovieDate() + "\n";
			qrCodeImage += "상영시간 : " + vo.getMovieTime() + "\n";
			qrCodeImage += "성인구매인원수 : " + vo.getMovieAdult() + "\n";
			qrCodeImage += "소인구매인원수 : " + vo.getMovieChild();
			qrCodeImage = new String(qrCodeImage.getBytes("UTF-8"), "ISO-8859-1");
			
			// qr코드 만들기
			QRCodeWriter qrCodeWriter = new QRCodeWriter();		// 라이브러리에의해 올라옴
			BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeImage, BarcodeFormat.QR_CODE, 200, 200); 		//BarcodeFormat.QR_CODE(구글에서 제공해줌), 폭 200 높이 200
			
			//MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();		//점의 밀도형식 , 기본컬러 (글자색:검정, 배경색:흰색)
			int qrCodeColor = 0xFF000022;	//int는 큰따옴표 x
			int qrCodeBackColor = 0xFFFFFFFF;
			
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrCodeColor, qrCodeBackColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig); 	//점의 밀도 형식으로 큐알코드를 만들겠다.
			
			// 렌더링된 큐알코드 이미지를 실제 그림파일로 만들어낸다.
			ImageIO.write(bufferedImage, "png", new File(realPath + qrCodeName + ".png"));
			
			// QR코드 생성 후 , 생성된 정보를 DB에 저장시켜준다.
			vo.setPublishDate(strToday);
			vo.setQrCodeName(qrCodeName);
			studyDAO.setQrCodeCreate(vo);
		} catch (IOException e) {		//Exception이거 쓰면 모든 에러가 여기서 나기 때문에 비추 그래서 IOException를 써서 에러를 따로따로 표시해준다.
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return qrCodeName;
	}

	@Override
	public String getQrCodeSearch(String qrCode) {
		return studyDAO.getQrCodeSearch(qrCode);
	}

	@Override
	public List<ChartVO> getRecentlyVisitCount(int i) {
		return studyDAO.getRecentlyVisitCount(i);
	}

	@Override
	public List<TransactionVO> getTransactionList() {
		return studyDAO.getTransactionList();
	}

	@Override
	public int setTransactionUesrInput(TransactionVO vo) {
		return studyDAO.setTransactionUesrInput(vo);
	}

	@Override
	public List<TransactionVO> getTransactionList2() {
		return studyDAO.getTransactionList2();
	}

	@Override
	public void setTransactionUser1Input(TransactionVO vo) {
		studyDAO.setTransactionUser1Input(vo);
	}

	@Override
	public void setTransactionUser2Input(TransactionVO vo) {
		studyDAO.setTransactionUser2Input(vo);
	}

	@Override
	public void setTransactionUserTotalInput(TransactionVO vo) {
		studyDAO.setTransactionUserTotalInput(vo);
	}

	
	
	
}

