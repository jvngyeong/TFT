package TFT.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.domain.AuthInfoDTO;
import TFT.service.goods.GoodsDetailService;
import TFT.service.goods.GoodsListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	GoodsListService goodsListService;
	
	@Autowired
	GoodsDetailService goodsDetailService;
	
	@GetMapping("shopMain")
	public String shopMain() {
		return "thymeleaf/shop/shopMain";
	}
	
	@GetMapping("shopList")
	public String shopList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/shop/shopList";
	}
	
	@GetMapping("goodsDetail")
	public String shopGoodsDetail(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/shop/shopGoodsDetail";
	}
	
	@GetMapping("sessionCheck")
	public @ResponseBody String sessionCheck(HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(auth == null) {
			return "000";
		}
		else {
			if(auth.getGrade().equals("emp")) {
				return "900";
			}
			else {
				return auth.getUserId();
			}
		}
	}
}
