package TFT.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TFT.command.LoginCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.LoginDTO;
import TFT.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(LoginCommand loginCommand, HttpSession session, HttpServletResponse response) {
		LoginDTO dto = new LoginDTO();
		dto = loginMapper.loginIdCheck(loginCommand.getId());
		if(dto == null) {
			return "존재하지 않는 아이디입니다.";
		}
		dto.setPassword(loginMapper.getLoginPw(dto));
		if(passwordEncoder.matches(loginCommand.getPassword(), dto.getPassword())){
			AuthInfoDTO auth = new AuthInfoDTO();
			auth.setUserId(dto.getId());
			auth.setUserPw(loginCommand.getPassword());
			auth.setGrade(dto.getGrade());
			session.setAttribute("auth", auth);
			if(loginCommand.isAutoLogin()) {
				Cookie cookie = new Cookie("isAutoLogin", auth.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 30);
				// 웹 브라우저로 전달
				response.addCookie(cookie);
			}
			else {
				Cookie cookie = new Cookie("isAutoLogin", auth.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(0);
				// 웹 브라우저로 전달
				response.addCookie(cookie);
			}
			if(loginCommand.isIdStore()) {
				Cookie cookie = new Cookie("isIdStore", auth.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 30);
				// 웹 브라우저로 전달
				response.addCookie(cookie);
			}
			else {
				Cookie cookie = new Cookie("isIdStore", auth.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(0);
				// 웹 브라우저로 전달
				response.addCookie(cookie);
			}
			return "200";
		}
		else {
			return "비밀번호가 일치하지 않습니다.";
		}
	}
}
