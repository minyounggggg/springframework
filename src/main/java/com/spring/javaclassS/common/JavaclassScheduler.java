package com.spring.javaclassS.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * - cron 사용예
 * 		매달 10일 14일에 실행 : corn = "0 0 14 10 * ?"
 * 		매달 10일 20일 14시에 실행 : corn = "0 0 14 10,20 * ?"
 * 		매달 마지막날 23시에 실행 실행 : corn = "0 0 23 L * ?"
 * 		매일 9시 ~ 9시 55분. 18시 ~ 18시 55분에 5분 간격으로 실행 
 * 					cron = "0 0/5 9,18 * * *"
 * 		매일  9시 ~ 18시 00분에 5분ㄴ간격으로 실행
 * 					cron = "0 0./5 9-18 * * *"
 * 		매년 7월달내 월~금 10시30분에 실행 : 요일(월:1, 화:2, 수 :3~~~)
 * 					cron = "0 30 10 ? 7 1-5
 * 		매년 7월달내 월~금 10시30분에 실행 : 요일(월:1, 화:2, 수 :3~~~)
 * 					cron = "0 30 10 ? 7 1-5
 * 
 * 
 * 
 * */

//@Component
//@Service		둘중 뭐로 올려도 상관없어용
public class JavaclassScheduler {
	
	@Autowired
	JavaMailSender mailSender;
	
	// 정해진 시간에 자동으로 실행 (cron = 초 분 시 일 월 요일) 기본값으로 '*'을 입력시켜준다.
	//@Scheduled(cron = "0/10 * * * * *")  // 10초에 한번씩 자동실행
	public void scheduleRun1() {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		System.out.println("10초에 한번씩 메세지가  출력됩니다." + strToday);
	}
	
	//@Scheduled(cron = "0 0/1 * * * *")  // 1분에 한번씩 자동실행
	public void scheduleRun2() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		System.out.println("1분에 한번씩 메세지가  출력됩니다." + strToday);
	}
	
	//@Scheduled(cron = "5 * * * * *")  // 5초에 한번씩 자동실행, 앞에/를붙여야 매번 출력학ㅆ다는 뜻
	public void scheduleRun3() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		System.out.println("5초에 한번씩 메세지가  출력됩니다." + strToday);
	}
	
	//@Scheduled(cron = "0 15 10 * * *")  // 매일 10시 14분에 한번씩 자동실행
	public void scheduleRun4() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		System.out.println("매일 10시 15분에 한번씩 자동실행 출력됩니다." + strToday);
	}
	
	//@Scheduled(cron = "0 44 10 * * *")  // 매일 10시 23분에 한번씩 자동실행
	public void scheduleRun5() throws MessagingException {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strToday = sdf.format(today);
		System.out.println("매일 10시 44분에 메일을 전송합니다. __" + strToday);
		
		String email = "minmin0325@naver.com";
		String title = "신제품안내 메일입니다.";
		String content = "여름신사웇 ㅁ안내 메일입니다.";
		
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		// 메일 전송을 위한 객체 : MimeMessage(), MimeMessageHelper()
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); //예외처리
		
		// 메일 보관함에 작성한 메세지들의 정보를 모두 저장시킨 후 작업 처리
		messageHelper.setTo(email);		// 받는 사람 메일 주소
		messageHelper.setSubject(title);	// 메일 제목
		messageHelper.setText(content);		// 메일 내용
		
		// 메세지 보관함의 내용(content)에, 발신자의 필요한 정보를 추가로 담아서 전송처리한다.
		content = content.replace("\n", "<br>");
//		content += "<br><hr><h3> 임시비밀번호 : "+pwd+"</h3><hr><br>";
		content += "<br><hr><h3>"+content+"</h3><hr><br>";
		content += "<p>신상품</p>";		// cid:  -> 예약어, 첨부파일이 아닌 메일 본문에 이미지 집어넣기, 178번라인의ㅣ addInline로 보내줌
		content += "<p>방문하기 : <a href='http://49.142.157.251:9090/javaclassJ15/'>javaclass</a></p>";
		content += "<hr>";
		messageHelper.setText(content, true);
		
		// 본문에 기재될 그림 파일의 경로를 별도로 표시시켜준다. 그런 후 다시 보관함에 저장한다.
		// FileSystemResource file = new FileSystemResource("D:\\javaclass\\springframework\\works\\javaclassS\\src\\main\\webapp\\resources\\images\\20240621_111652_1.png");
		
		// request.getSession().getServletContext().getRealPath("/resources/images/main.jpg");
		//FileSystemResource file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/main.jpg"));
		FileSystemResource file = new FileSystemResource(("D:\\javaclass\\springframework\\works\\javaclassS\\src\\main\\java\\com\\spring\\javaclassS\\common\\JavaclassScheduler.java"));
		messageHelper.addInline("main.jpg", file);
		
		// 메일 전송하기
		mailSender.send(message);
	}
	
}
