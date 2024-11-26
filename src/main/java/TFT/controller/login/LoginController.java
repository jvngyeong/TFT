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
			System.out.println(cookie.getName() + "존재");
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
	public @ResponseBody String loginCheck(@RequestParam Boolean isAutoLogin, @RequestParam Boolean isIdStore, 
			LoginCommand loginCommand, HttpSession session, HttpServletResponse response) {
		loginCommand.setAutoLogin(isAutoLogin);
		loginCommand.setIdStore(isIdStore);
		return loginCheckService.execute(loginCommand, session, response);
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("isAutoLogin")) {
				cookie.setPath("/");
				cookie.setMaxAge(0);	
			}
			response.addCookie(cookie);
			System.out.println(cookie.getName());
		}
		session.invalidate();
		return "redirect:/";
	}
}
