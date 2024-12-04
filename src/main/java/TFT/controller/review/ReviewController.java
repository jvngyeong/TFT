package TFT.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.command.ReviewCommand;
import TFT.service.review.ReviewInfoService;
import TFT.service.review.ReviewListService;
import TFT.service.review.ReviewWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	ReviewListService reviewListService;
	
	@Autowired
	ReviewWriteService reviewWriteService;
	
	@Autowired
	ReviewInfoService reviewInfoService;
	
	@GetMapping("reviewList")
	public String reviewList(String goodsNum, Model model) {
		reviewListService.execute(goodsNum, model);
		return "thymeleaf/review/reviewList";
	}
	
	@GetMapping("reviewWrite")
	public String reviewWrite(String purchaseNum, Model model) {
		reviewInfoService.execute(purchaseNum, model);
		model.addAttribute("purchaseNum", purchaseNum);
		return "thymeleaf/review/reviewWrite";
	}
	
	@PostMapping("reviewWrite")
	public String reviewWrite(ReviewCommand reviewCommand, String goodsName, HttpSession session) {
		reviewWriteService.execute(reviewCommand, session);
		return "thymeleaf/close";
	}
}
