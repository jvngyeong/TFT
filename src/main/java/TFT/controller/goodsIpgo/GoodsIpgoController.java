package TFT.controller.goodsIpgo;

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

import TFT.command.GoodsIpgoCommand;
import TFT.service.goods.GoodsListService;
import TFT.service.goodsIpgo.GoodsIpgoDeleteService;
import TFT.service.goodsIpgo.GoodsIpgoDetailService;
import TFT.service.goodsIpgo.GoodsIpgoListService;
import TFT.service.goodsIpgo.GoodsIpgoUpdateService;
import TFT.service.goodsIpgo.GoodsIpgoWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goodsIpgo")
public class GoodsIpgoController {
	@Autowired
	GoodsListService goodsListService;
	
	@Autowired
	GoodsIpgoWriteService goodsIpgoWriteService;
	
	@Autowired
	GoodsIpgoListService goodsIpgoListService;
	
	@Autowired
	GoodsIpgoDetailService goodsIpgoDetailService;
	
	@Autowired
	GoodsIpgoUpdateService goodsIpgoUpdateService;
	
	@Autowired
	GoodsIpgoDeleteService goodsIpgoDeleteService;
	
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList(Model model) {
		goodsIpgoListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
	@GetMapping("goodsIpgoWrite")
	public String goodsIpgoWrite() {
		return "thymeleaf/goodsIpgo/goodsIpgoWrite";
	}
	
	@GetMapping("goodsItem")
	public String goodsItem(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	
	@PostMapping("goodsIpgoWrite")
	public ResponseEntity<Map<String, String>> goodsIpgoWrite(@Validated GoodsIpgoCommand goodsIpgoCommand, BindingResult result,
			HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		if(result.hasErrors()) {
			for(FieldError error : result.getFieldErrors()) {
				if(error.getField().equals("ipgoPrice") && error.isBindingFailure()) {
					errors.put(error.getField(), "* 입고 가격에는 문자를 입력할 수 없습니다.");
				}
				else {
					errors.put(error.getField(), error.getDefaultMessage());
				}
			}
		}
		else {
			goodsIpgoWriteService.execute(goodsIpgoCommand, session);
		}
		return ResponseEntity.ok().body(errors);
	}
	
	@GetMapping("goodsIpgoDetail")
	public String goodsIpgoDetail(String ipgoNum, Model model) {
		goodsIpgoDetailService.execute(ipgoNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoDetail";
	}
	
	@GetMapping("goodsIpgoUpdate")
	public String goodsIpgoUpdate(String ipgoNum, Model model) {
		goodsIpgoDetailService.execute(ipgoNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoUpdate";
	}
	
	@PostMapping("goodsIpgoUpdate")
	public ResponseEntity<Map<String, String>> goodsIpgoUpdate(@Validated GoodsIpgoCommand goodsIpgoCommand, BindingResult result,
			HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		
		if(result.hasErrors()) {
			for(FieldError error : result.getFieldErrors()) {
				System.out.println(error.getDefaultMessage());
				if(error.getField().equals("ipgoPrice") && error.isBindingFailure()) {
					errors.put(error.getField(), "* 입고 가격에는 문자를 입력할 수 없습니다.");
				}
				else {
					errors.put(error.getField(), error.getDefaultMessage());
				}
			}
		}
		else {
			goodsIpgoUpdateService.execute(goodsIpgoCommand, session);
		}
		return ResponseEntity.ok().body(errors);
	}
	
	@GetMapping("goodsIpgoDelete")
	public String goodsIpgoDelete(String ipgoNum, Model model) {
		goodsIpgoDeleteService.execute(ipgoNum);
		goodsIpgoListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
}
