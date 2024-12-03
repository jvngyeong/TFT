package TFT.controller.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.service.delivery.DeliveryInfoService;

@Controller
@RequestMapping("delivery")
public class DeliveryController {
	@Autowired
	DeliveryInfoService deliveryInfoService;
	
	@GetMapping("deliveryWrite")
	public String deliveryWrite(String purchaseNum, Model model) {
		deliveryInfoService.execute(purchaseNum, model);
		return "thymeleaf/delivery/deliveryWrite";
	}
}
