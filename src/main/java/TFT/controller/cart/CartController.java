package TFT.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.service.cart.CartListService;
import TFT.service.cart.CartMergeService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	CartMergeService cartMergeService;
	
	@Autowired
	CartListService cartListService;
	
	@PostMapping("cartMerge")
	public @ResponseBody String cartMerge(String goodsNum, String memberId, String cartQty) {
		return cartMergeService.execute(goodsNum, memberId, cartQty);
	}
	
	@GetMapping("cartList")
	public String cartList(String memberId, Model model) {
		cartListService.execute(memberId, model);
		return "thymeleaf/cart/cartList";
	}
}
