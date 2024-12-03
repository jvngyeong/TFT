package TFT.controller.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TFT.command.PurchaseCommand;
import TFT.domain.PurchaseDTO;
import TFT.mapper.PurchaseMapper;
import TFT.service.purchase.GoodsBuyService;
import TFT.service.purchase.GoodsOrderService;
import TFT.service.purchase.IniPayReqService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	GoodsBuyService GoodsBuyService;
	
	@Autowired
	GoodsOrderService goodsOrderService;
	
	@Autowired
	IniPayReqService iniPayReqService;
	
	@Autowired
	PurchaseMapper purchaseMapper;
	
	@GetMapping("goodsOrder")
	public String goodsOrder(String[] goodsNums, @RequestParam Integer[] cartQties, Model model) {
		GoodsBuyService.execute(goodsNums, cartQties, model);
		return "thymeleaf/purchase/goodsOrder";
	}
	
	@PostMapping("goodsOrder")
	public String goodsOrder(PurchaseCommand purchaseCommand, @RequestParam("cartQty") Integer[] cartQties,
			HttpSession session, Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, cartQties, session);
		return "redirect:paymentOk?purchaseNum="+purchaseNum;
	}
	
	@GetMapping("paymentOk")
	public String paymentOk(String purchaseNum, Model model) {
		try {
			iniPayReqService.execute(purchaseNum, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";
	}
	
}
