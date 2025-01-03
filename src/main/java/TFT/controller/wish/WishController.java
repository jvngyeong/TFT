package TFT.controller.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.service.wish.WishCheckService;
import TFT.service.wish.WishListService;
import TFT.service.wish.WishMergeService;

@Controller
@RequestMapping("wish")
public class WishController {
	@Autowired
	WishMergeService wishMergeService;
	
	@Autowired
	WishCheckService wishCheckService;
	
	@Autowired
	WishListService wishListService;
	
	@RequestMapping("wishMerge")
	public @ResponseBody String wishMerge(String goodsNum, String memberId) {
		return wishMergeService.execute(goodsNum, memberId);
	}
	
	@RequestMapping("wishCheck")
	public @ResponseBody String wishCheck(String goodsNum, String memberId) {
		return wishCheckService.execute(goodsNum, memberId);
	}
	
	@GetMapping("wishList")
	public String wishList(String memberId, Model model) {
		wishListService.execute(memberId, model);
		return "thymeleaf/wish/wishList";
	}
	
}
