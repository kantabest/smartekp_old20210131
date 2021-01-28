package com.easyworks.smartekp.member.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class Member {
	private String id;
	private String pw;
	private String role;
	private boolean enabled;
	private int regId;
	private String team;
	private String name;
	private String statusCode;
}

