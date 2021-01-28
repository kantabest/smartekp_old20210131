package com.easyworks.smartekp.member.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class Member {
	
	//database info
	private String memberId;
	private String memberName;
	private String loginId;
	private String loginPw;
	private String memberRole;
	private boolean usedYn; 
	private String remarks;
	private int updaterId;
	private String updatedDt;


	// reference info
	private String teamCode;
	private String teamName;	
	private String statusCode;
}

