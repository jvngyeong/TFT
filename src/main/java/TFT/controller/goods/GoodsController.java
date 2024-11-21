package TFT.controller.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@GetMapping("goodsWrite")
	public String goodsWrite() {
		return "thymeleaf/goods/goodsWrite";
	}
}
