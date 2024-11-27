package TFT.controller.goods;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.command.GoodsCommand;
import TFT.service.goods.GoodsDeleteService;
import TFT.service.goods.GoodsDetailService;
import TFT.service.goods.GoodsListService;
import TFT.service.goods.GoodsUpdateService;
import TFT.service.goods.GoodsWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsWriteService goodsWriteService;
	
	@Autowired
	GoodsListService goodsListService;
	
	@Autowired
	GoodsDetailService goodsDetailService;
	
	@Autowired
	GoodsUpdateService goodsUpdateService;
	
	@Autowired
	GoodsDeleteService goodsDeleteService;
	
	@GetMapping("goodsList")
	public String goodsList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}
	
	@GetMapping("goodsWrite")
	public String goodsWrite() {
		return "thymeleaf/goods/goodsWrite";
	}
	@PostMapping("goodsWrite")
	public @ResponseBody ResponseEntity<Map<String, String>> goodsWrite(@Validated GoodsCommand goodsCommand, BindingResult result, 
			HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		if(result.hasErrors()) {
			for(FieldError error : result.getFieldErrors()) {
				if(error.getField().equals("goodsPrice") && error.isBindingFailure()) {
					errors.put(error.getField(), "* 상품 가격에는 문자를 입력할 수 없습니다.");
				}
				else {
					errors.put(error.getField(), error.getDefaultMessage());
				}
			}
		}
		else {
			goodsWriteService.execute(goodsCommand, session);
		}
		return ResponseEntity.ok().body(errors);
	}
	
	@GetMapping("goodsDetail")
	public String goodsDetail(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsDetail";
	}
	
	@GetMapping("goodsUpdate")
	public String goodsUpdate(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsUpdate";
	}
	
	@PostMapping("goodsUpdate")
	public @ResponseBody ResponseEntity<Map<String, String>> goodsUpdate(@Validated GoodsCommand goodsCommand, BindingResult result, 
			HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		if(result.hasErrors()) {
			for(FieldError error : result.getFieldErrors()) {
				if(error.getField().equals("goodsPrice") && error.isBindingFailure()) {
					errors.put(error.getField(), "* 상품 가격에는 문자를 입력할 수 없습니다.");
				}
				else if(!error.getField().equals("goodsMainImage") && !error.getField().equals("goodsDetailImage")){
					errors.put(error.getField(), error.getDefaultMessage());
				}
			}
		}
		else {
			goodsUpdateService.execute(goodsCommand, session);
		}
		return ResponseEntity.ok().body(errors);
	}
	
	@RequestMapping("goodsDelete")
	public String goodsDelete(String goodsNum, Model model) {
		goodsDeleteService.execute(goodsNum);
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}
}
