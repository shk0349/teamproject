package com.mbc.team.member;

public class MemberDTO {
	String id,nickname,pw,name,gender,birth,phone1,phone2,address,email,grade;

	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String id, String nickname, String pw, String name, String gender, String birth, String phone1,
			String phone2, String address, String email, String grade) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
		this.email = email;
		this.grade = grade;
	}

	
	
}
