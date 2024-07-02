package com.spring.javaclassS.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.javaclassS.service.DbtestService;
import com.spring.javaclassS.service.StudyService;
import com.spring.javaclassS.vo.CrawlingVO;
import com.spring.javaclassS.vo.CrimeVO;
import com.spring.javaclassS.vo.MailVO;
import com.spring.javaclassS.vo.UserVO;

@Controller
@RequestMapping("/study")
public class StudyController {
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	DbtestService dbtestService;
	
	@Autowired
	JavaMailSender mailSender;
	

	@RequestMapping(value = "/ajax/ajaxForm", method = RequestMethod.GET)
	public String ajaxFormGet() {
		return "study/ajax/ajaxForm";
	}
	
	@ResponseBody		//ajax를 사용할떄는 @ResponseBody 선언해서 사용하도록한다. return엔 jsp경로가 아닌 넘길 값의 변수?
	@RequestMapping(value = "/ajax/ajaxTest1", method = RequestMethod.POST)
	public String ajaxTest1Post(int idx) {
		System.out.println("idx : " + idx);
		//return "study/ajax/ajaxForm";
		return idx + "";	//int로 받아왔으니까 문자로 출력하기 위해 + "" 를 붙여준다.
	}
	
	@ResponseBody															// produces = ajax 값전달 시 한글이 깨질때 처리
	@RequestMapping(value = "/ajax/ajaxTest2", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String ajaxTest2Post(String str) {
		System.out.println("str : " + str);
		return str;
	}
	
	@RequestMapping(value = "/ajax/ajaxTest3_1", method = RequestMethod.GET)
	public String ajaxTest3_1Get() {
		return "study/ajax/ajaxTest3_1";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest3_1", method = RequestMethod.POST)
	public String[] ajaxTest3_1Post(String dodo) {
		return studyService.getCityStringArray(dodo);
	}
	
	@RequestMapping(value = "/ajax/ajaxTest3_2", method = RequestMethod.GET)
	public String ajaxTest3_2Get() {
		return "study/ajax/ajaxTest3_2";
	}

	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest3_2", method = RequestMethod.POST)
	public ArrayList<String> ajaxTest3_2Post(String dodo) {
		return studyService.getCityArrayList(dodo);
	}
	
	@RequestMapping(value = "/ajax/ajaxTest3_3", method = RequestMethod.GET)
	public String ajaxTest3_3Get() {
		return "study/ajax/ajaxTest3_3";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest3_3", method = RequestMethod.POST)
	public HashMap<Object, Object> ajaxTest3_3Post(String dodo) {
		ArrayList<String> vos = studyService.getCityArrayList(dodo);
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("city", vos);
		
		return map;
	}
	
	@RequestMapping(value = "/ajax/ajaxTest4", method = RequestMethod.GET)
	public String ajaxTest4Get() {
		return "study/ajax/ajaxTest4";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest4", method = RequestMethod.POST)
	public HashMap<Object, Object> ajaxTest4Post(String user) {
		return studyService.getUserData(user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest4-1", method = RequestMethod.POST)
	public UserVO ajaxTest4_1Post(String mid) {
		return studyService.getUserMidSearch(mid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajax/ajaxTest4-2", method = RequestMethod.POST)
	public ArrayList<UserVO> ajaxTest4_2Post(String mid) {
		return studyService.getUserMidList(mid);
	}
	
	@RequestMapping(value = "/restapi/restapi", method = RequestMethod.GET)
	public String restapiGet() {
		return "study/restapi/restapi";
	}
	
	@RequestMapping(value = "/restapi/restapiTest1/{message}", method = RequestMethod.GET)
	public String restapiTest1Get(@PathVariable String message) {
		System.out.println("message : " + message);
		return "message : " + message;
	}
	
	@RequestMapping(value = "/restapi/restapiTest4", method = RequestMethod.GET)
	public String restapiTest4Get() {
		return "study/restapi/restapiTest4";
	}
	
	@ResponseBody
	@RequestMapping(value = "/restapi/saveCrimeData", method = RequestMethod.POST)
	public void saveCrimeDataPost(CrimeVO vo) {
		studyService.setSaveCrimeData(vo);
		//System.out.println("vo : " + vo);
	}
	
	@RequestMapping(value = "/mail/mailForm", method = RequestMethod.GET)
	public String mailFormGet() {
		return "study/mail/mailForm";
	}
	
	// 메일전송하기
	@RequestMapping(value = "/mail/mailForm", method = RequestMethod.POST)
	public String mailFormPost(MailVO vo, HttpServletRequest request) throws MessagingException {
		String title = vo.getTitle();
		String toMail = vo.getToMail();
		String content = vo.getContent();
		
		// 메일 전송을 위한 객체 : MimeMessage(), MimeMessageHelper()
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); //예외처리
		
		// 메일 보관함에 작성한 메세지들의 정보를 모두 저장시킨 후 작업 처리
		messageHelper.setTo(toMail);		// 받는 사람 메일 주소
		messageHelper.setSubject(title);	// 메일 제목
		messageHelper.setText(content);		// 메일 내용
		
		// 메세지 보관함의 내용(content)에, 발신자의 필요한 정보를 추가로 담아서 전송처리한다.
		content = content.replace("\n", "<br>");
		content += "<br><hr><h3>javaclass에서 보냅니당</h3><hr><br>";
		content += "<p><img src='cid:main.jpg' width='500px'></p>";		// cid:  -> 예약어, 첨부파일이 아닌 메일 본문에 이미지 집어넣기, 178번라인의ㅣ addInline로 보내줌
		content += "<p>방문하기 : <a href='http://49.142.157.251:9090/javaclassJ15/'>javaclass</a></p>";
		content += "<hr>";
		messageHelper.setText(content, true);
		
		// 본문에 기재될 그림 파일의 경로를 별도로 표시시켜준다. 그런 후 다시 보관함에 저장한다.
		// FileSystemResource file = new FileSystemResource("D:\\javaclass\\springframework\\works\\javaclassS\\src\\main\\webapp\\resources\\images\\20240621_111652_1.png");
		
		// request.getSession().getServletContext().getRealPath("/resources/images/main.jpg");
		FileSystemResource file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/main.jpg"));
		messageHelper.addInline("main.jpg", file);
		
		// 첨부파일 보내기
		file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/chicago.jpg"));
		messageHelper.addAttachment("chicago.jpg", file);
		file = new FileSystemResource(request.getSession().getServletContext().getRealPath("/resources/images/sanfran.zip"));
		messageHelper.addAttachment("sanfran.zip", file);
		
		// 메일 전송하기
		mailSender.send(message);
		
		return "redirect:/message/mailSendOk";
	}
	
	// 파일 업로드 연습폼 호출하기
	@RequestMapping(value = "/fileUpload/fileUpload", method = RequestMethod.GET)
	public String fileUploadGet(HttpServletRequest request, Model model) {
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/fileUpload/");
		
		String[] files = new File(realPath).list();
		
		model.addAttribute("files", files);
		model.addAttribute("fileCount", files.length);
		
		return "study/fileUpload/fileUpload";
	}
	
	// 파일 업로드 연습폼 호출하기
	@RequestMapping(value = "/fileUpload/fileUpload", method = RequestMethod.POST)
	public String fileUploadPost(MultipartFile fName, String mid) {
		
		int res = studyService.fileUpload(fName, mid);
		
		if(res != 0) return "redirect:/message/fileUploadOk";
		else return "redirect:/message/fileUploadNo";
	}
	
	@ResponseBody
	@RequestMapping(value = "/fileUpload/fileDelete", method = RequestMethod.POST)
	public String fileDeletePost(HttpServletRequest request, String file) {
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/fileUpload/");
		
		String res = "0";
		File fName = new File(realPath + file);
		
		if(fName.exists()) {		// .exists 존재하는지 물어보는
			fName.delete();
			res = "1";
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/fileUpload/fileDeleteAll", method = RequestMethod.POST)
	public String fileDeleteAllPost(HttpServletRequest request, String file) {
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/fileUpload/");
		
		String res = "0";
		File targetFolder = new File(realPath);
		if(!targetFolder.exists()) return "0";
		
		File[] files = targetFolder.listFiles();
		
		if(files.length != 0) {
			for(File f : files) {
				if(!f.isDirectory()) f.delete();  //폴더까지 지워지지 않게 폴더말고 파일만 전체삭제 되도록
			}
			res = "1";
		}
		
		return res;
	}
	
	@RequestMapping(value = "/fileUpload/multiFile", method = RequestMethod.GET)
	public String multifileGet() {
		return "study/fileUpload/multiFile";
	}
	
	@RequestMapping(value = "/fileUpload/multiFile", method = RequestMethod.POST)
	public String multifilePost(MultipartHttpServletRequest mFile) {
		
		int res = studyService.multiFileUpload(mFile);
		
		if(res != 0) return "redirect:/message/multiFileUploadOk";
		else return "redirect:/message/multiFileUploadNo";
	}
	
	@RequestMapping(value = "/fileUpload/multiFile2", method = RequestMethod.GET)
	public String multifile2Get() {
		return "study/fileUpload/multiFile2";
	}
	
	@RequestMapping(value = "/fileUpload/multiFile2", method = RequestMethod.POST)
	public String multifile2Post(MultipartHttpServletRequest mFile, HttpServletRequest request, String imgNames) {
		//String[] imgNames = request.getParameter("imgNames").split("/");	// "/"로 분리해서 배열로 담기
		
		int res = studyService.multiFileUpload(mFile);
		
		if(res != 0) return "redirect:/message/multiFileUploadOk";
		else return "redirect:/message/multiFileUploadNo";
	}
	
	// 크롤링연습(jsoup)
	@RequestMapping(value = "/crawling/jsoup", method = RequestMethod.GET)
	public String jsoupGet() {
		return "study/crawling/jsoup";
	}
	/*
	// 크롤링연습 처리(jsoup)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String jsoupPost(String url, String selector) throws IOException {
		Connection conn = Jsoup.connect(url);
		
		Document document = conn.get();
		//System.out.println("document : " + document);
		
		Elements selects = document.select(selector);
		//System.out.println("selects : " + selects);
		//System.out.println("selects : " + selects.text());
		
		String str = "";
		int i = 0;
		for(Element select : selects) {
			i++;
			//System.out.println(i + " : " + select);
			System.out.println(i + " : " + select.text());
			str += i + " : " + select + "<br/>";
		}
		
		
		return str;
	}
	*/
	
	// 크롤링연습 처리(jsoup)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup", method = RequestMethod.POST)
	public ArrayList<String> jsoupPost(String url, String selector) throws IOException {
		Connection conn = Jsoup.connect(url);
		
		Document document = conn.get();
		//System.out.println("document : " + document);
		
		Elements selects = document.select(selector);
		//System.out.println("selects : " + selects);
		//System.out.println("selects : " + selects.text());
		
		ArrayList<String> vos = new ArrayList<String>();
		int i = 0;
		for(Element select : selects) {
			i++;
			System.out.println(i + " : " + select);
			//System.out.println(i + " : " + select.text());
			vos.add(i + " : " + select.html().replace("data-onshow-","")); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		
		
		return vos;
	}
	
	// 크롤링연습 처리2 (jsoup2)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup2", method = RequestMethod.POST)
	public ArrayList<CrawlingVO> jsoup2Post() throws IOException {
		Connection conn = Jsoup.connect("https://news.naver.com/");
		
		Document document = conn.get();
		
		Elements selects = null;
		
		ArrayList<String> titleVos = new ArrayList<String>();
		selects = document.select("div.cjs_t");
		for(Element select : selects) {
			titleVos.add(select.html()); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		ArrayList<String> imageVos = new ArrayList<String>();
		selects = document.select("div.cjs_news_mw");
		for(Element select : selects) {
			imageVos.add(select.html().replace("data-onshow-","")); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		ArrayList<String> broadcastVos = new ArrayList<String>();
		selects = document.select("div.cjs_ctw");
		for(Element select : selects) {
			broadcastVos.add(select.html()); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		
		ArrayList<CrawlingVO> vos = new ArrayList<CrawlingVO>();
		CrawlingVO vo = null;
		for(int i=0; i<titleVos.size(); i++) {
			vo = new CrawlingVO();
			vo.setItem1(titleVos.get(i));
			vo.setItem2(imageVos.get(i));
			vo.setItem3(broadcastVos.get(i));
			vos.add(vo);
		}
		
		return vos;
	}
	
	// 크롤링연습 처리3 (다음 연예뉴스) (jsoup2)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup3", method = RequestMethod.POST)
	public ArrayList<CrawlingVO> jsoup3Post() throws IOException {
		Connection conn = Jsoup.connect("https://entertain.daum.net/");
		
		Document document = conn.get();
		
		Elements selects = null;
		
		ArrayList<String> titleVos = new ArrayList<String>();
		selects = document.select("a.link_txt.valid_link");
		for(Element select : selects) {
			titleVos.add(select.html()); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		ArrayList<String> imageVos = new ArrayList<String>();
		selects = document.select("a.link_thumb");
		System.out.println("selects : " + selects);
		for(Element select : selects) {
			imageVos.add(select.html()); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		ArrayList<String> broadcastVos = new ArrayList<String>();
		selects = document.select("span.info_thumb");
		for(Element select : selects) {
			broadcastVos.add(select.html()); // 객체형식이라 replace앞에 html붙여서 바꿔주기
		}
		
		ArrayList<CrawlingVO> vos = new ArrayList<CrawlingVO>();
		CrawlingVO vo = null;
		for(int i=0; i<titleVos.size(); i++) {
			vo = new CrawlingVO();
			vo.setItem1(titleVos.get(i));
			vo.setItem2(imageVos.get(i));
			vo.setItem3(broadcastVos.get(i));
			vos.add(vo);
		}
		
		return vos;
	}
	
	// 크롤링연습 처리4 (네이버 검색어를 이용한 검색처리) (jsoup2)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup4", method = RequestMethod.POST)
	public ArrayList<String> jsoup4Post(String search, String searchSelector) throws IOException {
		Connection conn = Jsoup.connect(search);
		
		Document document = conn.get();
		
		Elements selects = document.select(searchSelector);
		
		ArrayList<String> vos = new ArrayList<String>();
		
		int i=0;
		for (Element select : selects) {
			i++;
			vos.add(i + " : " + select.html());
		}
		
		return vos;
	}
	
	// 크롤링연습 처리5 (네이버 검색어를 이용한 그림 검색처리) (jsoup2)
	@ResponseBody
	@RequestMapping(value = "/crawling/jsoup5", method = RequestMethod.POST)
	public ArrayList<String> jsoup5Post(String search, String searchSelector) throws IOException {
		Connection conn = Jsoup.connect(search);
		
		Document document = conn.get();
		
		Elements selects = document.select(searchSelector);
		
		ArrayList<String> vos = new ArrayList<String>();
		
		int i=0;
		for (Element select : selects) {
			i++;
			vos.add(i + " : " + select.html().replace("data-lazy",""));
		}
		
		return vos;
	}
	
	// 크롤링연습(selenium)
	@RequestMapping(value = "/crawling/selenium", method = RequestMethod.GET)
	public String seleniumGet() {
		return "study/crawling/selenium";
	}
	
	// 크롤링연습처리 (selenium) CGV상여작 크롤링
	@ResponseBody
	@RequestMapping(value = "/crawling/selenium", method = RequestMethod.POST)
	public List<HashMap<String, Object>> seleniumPost(HttpServletRequest request) {
		List<HashMap<String, Object>> vos = new ArrayList<HashMap<String, Object>>();
		
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/resources/crawling/");
			System.setProperty("webdriver.chrome.driver", realPath + "chromedriver.exe");
			
			WebDriver driver = new ChromeDriver();
			driver.get("http://www.cgv.co.kr/movies/");
			
			// 현재 상역작만 보기
			WebElement btnMore = driver.findElement(By.id("chk_nowshow"));
			btnMore.click();
			
			// 더보기 버튼을 클릭한다.
			btnMore = driver.findElement(By.className("link-more"));
			btnMore.click();
			
			// 화면이 다 열리는 동안 시간을 지연시켜준다.
			try {Thread.sleep(2000);} catch (Exception e) {}
			
			// 낱개의 vos객체(element)를 HashMap에 등록 후 List로 처리해서 프론트로 넘겨준다.
			List<WebElement> elements = driver.findElements(By.cssSelector("div.sect-movie-chart ol li"));
			for(WebElement element : elements) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				String image = "<img src='"+element.findElement(By.tagName("img")).getAttribute("src") +"' width='200px' />";
				String link = element.findElement(By.tagName("a")).getAttribute("href");
				String title = "<a href='"+link+"' target='_blank'>" + element.findElement(By.className("title")).getText() + "</a>";
				String percent = element.findElement(By.className("percent")).getText();
				
				map.put("image", image);
				map.put("link", link);
				map.put("title", title);
				map.put("percent", percent);
				vos.add(map);
			}
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("vos : " + vos);
		return vos;
	}
	
	// 크롤링연습처리 (selenium) srt열차 조회하기
	@ResponseBody
	@RequestMapping(value = "/crawling/train", method = RequestMethod.POST)
	public List<HashMap<String, Object>> trainPost(HttpServletRequest request, String stationStart, String stationStop) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/resources/crawling/");
			System.setProperty("webdriver.chrome.driver", realPath + "chromedriver.exe");
			
			WebDriver driver = new ChromeDriver();
			driver.get("http://srtplay.com/train/schedule");
			
			WebElement btnMore = driver.findElement(By.xpath("//*[@id=\"station-start\"]/span"));
			btnMore.click();
			try { Thread.sleep(2000);} catch (InterruptedException e) {}
			
			btnMore = driver.findElement(By.xpath("//*[@id=\"station-pos-input\"]"));
	        btnMore.sendKeys(stationStart);
	        btnMore = driver.findElement(By.xpath("//*[@id=\"stationListArea\"]/li/label/div/div[2]"));
	        btnMore.click();
	        btnMore = driver.findElement(By.xpath("//*[@id=\"stationDiv\"]/div/div[3]/div/button"));
	        btnMore.click();
		    try { Thread.sleep(2000);} catch (InterruptedException e) {}
		    
		    btnMore = driver.findElement(By.xpath("//*[@id=\"station-arrive\"]/span"));
		    btnMore.click();
		    try { Thread.sleep(2000);} catch (InterruptedException e) {}
		    btnMore = driver.findElement(By.id("station-pos-input"));
		    
		    btnMore.sendKeys(stationStop);
		    btnMore = driver.findElement(By.xpath("//*[@id=\"stationListArea\"]/li/label/div/div[2]"));
		    btnMore.click();
		    btnMore = driver.findElement(By.xpath("//*[@id=\"stationDiv\"]/div/div[3]/div/button"));
		    btnMore.click();
		    try { Thread.sleep(2000);} catch (InterruptedException e) {}
		    
		    btnMore = driver.findElement(By.xpath("//*[@id=\"sr-train-schedule-btn\"]/div/button"));
		    btnMore.click();
		    try { Thread.sleep(2000);} catch (InterruptedException e) {}
		    
		    List<WebElement> timeElements = driver.findElements(By.cssSelector(".table-body ul.time-list li"));
 			
		    HashMap<String, Object> map = null;
		    
		    for(WebElement element : timeElements){
				map = new HashMap<String, Object>();
				String train=element.findElement(By.className("train")).getText();
				String start=element.findElement(By.className("start")).getText();
				String arrive=element.findElement(By.className("arrive")).getText();
				String time=element.findElement(By.className("time")).getText();
				String price=element.findElement(By.className("price")).getText();
				map.put("train", train);
				map.put("start", start);
				map.put("arrive", arrive);
				map.put("time", time);
				map.put("price", price);
				array.add(map);
			}
		    
		    // 요금조회하기 버튼을 클릭한다.(처리 안됨 - 스크린샷으로 대체)
		    btnMore = driver.findElement(By.xpath("//*[@id=\"scheduleDiv\"]/div[2]/div/ul/li[1]/div/div[5]/button"));
		    //System.out.println("요금 조회버튼클릭");
		    btnMore.click();
		    try { Thread.sleep(2000);} catch (InterruptedException e) {}
		    
		    // 지정경로에 브라우저 화면 스크린샷 저장처리
	  		realPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/");
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(scrFile, new File(realPath + "screenshot.png"));
			
	        driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	
}
