package TFT.controller.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController {
	@GetMapping("reviewList")
	public String reviewList(String goodsNum) {
		return "thymeleaf/review/reviewList";
	}
}
