package TFT.controller.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.command.InquireCommand;
import TFT.service.inquire.InquireDetailService;
import TFT.service.inquire.InquireListService;
import TFT.service.inquire.InquireUpdateService;
import TFT.service.inquire.InquireWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireWriteService inquireWriteService;
	
	@Autowired
	InquireListService inquireListService;
	
	@Autowired
	InquireDetailService inquireDetailService;
	
	@Autowired
	InquireUpdateService inquireUpdateService;
	
	@PostMapping("goodsInquire")
	public String goodsInquire(String goodsNum, Model model) {
		inquireListService.execute(goodsNum, model);
		model.addAttribute("goodsNum", goodsNum);
		return "thymeleaf/inquire/goodsInquire";
	}
	
	@GetMapping("inquireWrite")
	public String inquireWrite(String goodsNum, Model model) {
		model.addAttribute("goodsNum", goodsNum);
		return "thymeleaf/inquire/inquireWrite";
	}
	
	@PostMapping("inquireWrite")
	public String inquireWrite(InquireCommand inquireCommand, HttpSession session) {
		inquireWriteService.execute(inquireCommand, session);
		return "thymeleaf/close";
	}
	
	@GetMapping("inquireList")
	public String inquireList(Model model) {
		inquireListService.execute(null, model);
		return "thymeleaf/inquire/inquireList";
	}
	
	@GetMapping("inquireAnswer")
	public String inquireAnswer(String inquireNum, Model model) {
		inquireDetailService.execute(inquireNum, model);
		return "thymeleaf/inquire/inquireAnswer";
	}
	
	@PostMapping("inquireAnswer")
	public String inquireAnswer(InquireCommand inquireCommand, HttpSession session) {
		inquireUpdateService.execute(inquireCommand, session);
		return "thymeleaf/close";
	}
}
