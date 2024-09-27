package com.mbc.team.login;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {

	LoginDTO logincheck(String id, String pw);

	LoginDTO myinfo(String id);

	

}
