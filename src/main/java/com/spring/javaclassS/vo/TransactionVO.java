package com.spring.javaclassS.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class TransactionVO {
	private int idx;
	
	@NotEmpty	//hibernate.validator로 올리기, @NotEmpty는 공백을 체크하는 것으로 size를 설정하주면 @NotEmpty는 생략가능 
	@Size(min=2, max=4)		//몇글자 받을껀지 (몇 bite)
	private String mid;
	
	@Size(min=2, max=10, message = "성함의 길이가 잘못됐습니다/2~10자리")
	private String name;
	
	@Range(min=19, message="미성년자는 가입할 수 없습니다.")
	private int age;
	
	private String address;
	
	//user2 테이블에서의 필드추가
	private String job;
	
}
