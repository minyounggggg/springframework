package com.spring.javaclassS.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JavaclassProvide {
	
	@Autowired
	JavaMailSender mailSender;
	
	// urlPath에 파일 저장하는 메소드 : (업로드 파일명, 저장할 파일명, 저장할 경로)
	public void writeFile(MultipartFile fName, String sFileName, String urlPath) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/"+urlPath+"/");
		
		FileOutputStream fos = new FileOutputStream(realPath + sFileName);
		
		if(fName.getBytes().length != -1) {		// 파일이 존재할때, 없지 않을때 
			fos.write(fName.getBytes());
		}
		fos.flush();
		fos.close();
	}
	
	public void deleteFile(String photo, String urlPath) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/"+urlPath+"/");
		
		File file = new File(realPath + photo);
		if(file.exists()) file.delete();
	}

	// 파일 이름변경하기 (중복방지)
	public String saveFileName(String oFileName) {
		String fileName = "";
		
		Calendar cal = Calendar.getInstance();
		fileName += cal.get(Calendar.YEAR);
		fileName += cal.get(Calendar.MONTH)+1;
		fileName += cal.get(Calendar.DATE);
		fileName += cal.get(Calendar.HOUR_OF_DAY);
		fileName += cal.get(Calendar.MINUTE);
		fileName += cal.get(Calendar.SECOND);
		fileName += cal.get(Calendar.MILLISECOND);
		fileName += "_" + oFileName;
		
		return fileName;
	}
	
	// 메일전송하기 (아이디찾기, 비밀번호찾기)
	public String mailSend(String email, String title, String pwd) throws MessagingException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String content = "";
		
		// 메일 전송을 위한 객체 : MimeMessage(), MimeMessageHelper()
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); //예외처리
		
		// 메일 보관함에 작성한 메세지들의 정보를 모두 저장시킨 후 작업 처리
		messageHelper.setTo(email);		// 받는 사람 메일 주소
		messageHelper.setSubject(title);	// 메일 제목
		messageHelper.setText(content);		// 메일 내용
		
		// 메세지 보관함의 내용(content)에, 발신자의 필요한 정보를 추가로 담아서 전송처리한다.
		content = content.replace("\n", "<br>");
		content += "<br><hr><h3> 임시비밀번호 : "+pwd+"</h3><hr><br>";
		content += "<p><img src='cid:main.jpg' width='500px'></p>";		// cid:  -> 예약어, 첨부파일이 아닌 메일 본문에 이미지 집어넣기, 178번라인의ㅣ addInline로 보내줌
		content += "<p>방문하기 : <a href='http://49.142.157.251:9090/javaclassJ15/'>javaclass</a></p>";
		content += "<hr>";
		messageHelper.setText(content, true);
		
		// 본문에 기재될 그림 파일의 경로를 별도로 표시시켜준다. 그런 후 다시 보관함에 저장한다.
		// FileSystemResource file = new FileSystemResource("D:\\javaclass\\springframework\\works\\javaclassS\\src\\main\\webapp\\resources\\images\\20240621_111652_1.png");
		
		// request.getSession().getServletContext().getRealPath("/resources/images/main.jpg");
		FileSystemResource file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/main.jpg"));
		messageHelper.addInline("main.jpg", file);
		
		// 메일 전송하기
		mailSender.send(message);
		
		return "1";
	}
	
}
