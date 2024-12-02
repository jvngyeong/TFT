package TFT.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.command.LoginCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.LoginDTO;
import TFT.mapper.LoginMapper;
import TFT.service.login.LoginCheckService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	LoginCheckService loginCheckService;
	
	@Autowired
	LoginMapper loginMapper;
	
	@GetMapping("loginForm")
	public String loginForm(LoginCommand loginCommand, HttpServletRequest request, HttpSession session, Model model) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			System.out.println("로그인창에 존재하는 쿠키 !" + cookie.getName());
			System.out.println("쿠키 생명 시간 = " + cookie.getValue());
			if(cookie.getName().equals("isAutoLogin")) {
				LoginDTO dto = loginMapper.loginIdCheck(cookie.getValue());
				dto.setPassword(loginMapper.getLoginPw(dto));
				AuthInfoDTO auth = new AuthInfoDTO();
				auth.setUserId(dto.getId());
				auth.setUserPw(dto.getPassword());
				auth.setGrade(dto.getGrade());
				session.setAttribute("auth", auth);
			}
			if(cookie.getName().equals("isIdStore")) {
				loginCommand.setId(cookie.getValue());
				loginCommand.setIdStore(true);
				model.addAttribute("loginCommand", loginCommand);
			}
		}
		return "thymeleaf/login/loginForm";
	}
	
	@PostMapping("loginCheck")
	public @ResponseBody String loginCheck(@RequestParam boolean isAutoLogin, @RequestParam boolean isIdStore, 
			LoginCommand loginCommand, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		String popup = request.getParameter("popup");
		loginCommand.setAutoLogin(isAutoLogin);
		loginCommand.setIdStore(isIdStore);
		System.out.println("로그인 할 때 isAutoLogin 값은 ? = "+isAutoLogin);
		System.out.println("로그인 할 때 isIdStore 값은 ? = "+isIdStore);
		return loginCheckService.execute(loginCommand, session, response, popup);
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName()+"의 maxage = " +cookie.getValue());
			if(cookie.getName().equals("isAutoLogin")) {
				cookie.setPath("/");
				cookie.setMaxAge(0);	
			}
			response.addCookie(cookie);
			System.out.println("로그아웃 할 때 삭제한 쿠키 = " + cookie.getName());
		}
		session.invalidate();
		return "redirect:/";
	}
}