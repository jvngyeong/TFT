package TFT.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.domain.AuthInfoDTO;
import TFT.service.cart.CartGoodsRemoveService;
import TFT.service.cart.CartListService;
import TFT.service.cart.CartMergeService;
import TFT.service.cart.CartQtyUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	CartMergeService cartMergeService;
	
	@Autowired
	CartListService cartListService;
	
	@Autowired
	CartGoodsRemoveService cartGoodsRemoveService;
	
	@Autowired
	CartQtyUpdateService cartQtyUpdateService;
	
	@PostMapping("cartMerge")
	public @ResponseBody String cartMerge(String goodsNum, String memberId, String cartQty) {
		return cartMergeService.execute(goodsNum, memberId, cartQty);
	}
	
	@GetMapping("cartList")
	public String cartList(String memberId, Model model) {
		cartListService.execute(memberId, model);
		return "thymeleaf/cart/cartList";
	}
	
	@GetMapping("cartGoodsRemove")
	public @ResponseBody String cartGoodsRemove(String goodsNum, HttpSession session) {
		return cartGoodsRemoveService.execute(goodsNum, session);
	}
	
	@GetMapping("cartQtyUpdate")
	public String cartQtyUpdate(String goodsNum, String cartQty, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		cartQtyUpdateService.execute(goodsNum, cartQty, memberId);
		cartListService.execute(memberId, model);
		return "thymeleaf/cart/cartList";
	}
}
