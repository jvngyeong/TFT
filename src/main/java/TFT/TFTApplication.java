package TFT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import TFT.mapper.LoginMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
public class TFTApplication {
	@Autowired
	LoginMapper loginMapper;

	public static void main(String[] args) {
		SpringApplication.run(TFTApplication.class, args);
	}
	
	@GetMapping("/")
	public String index(HttpServletRequest request) {
		return "thymeleaf/index";
	}
	
}
