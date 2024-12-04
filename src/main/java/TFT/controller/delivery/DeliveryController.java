package TFT.controller.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.service.delivery.DeliveryDeleteService;
import TFT.service.delivery.DeliveryInfoService;
import TFT.service.delivery.DeliveryUpdateService;
import TFT.service.delivery.DeliveryWriteService;

@Controller
@RequestMapping("delivery")
public class DeliveryController {
	@Autowired
	DeliveryInfoService deliveryInfoService;
	
	@Autowired
	DeliveryWriteService deliveryWriteService;
	
	@Autowired
	DeliveryUpdateService deliveryUpdateService;
	
	@Autowired
	DeliveryDeleteService deliveryDeleteService;
	
	@GetMapping("deliveryWrite")
	public String deliveryWrite(String purchaseNum, Model model) {
		deliveryInfoService.execute(purchaseNum, model);
		return "thymeleaf/delivery/deliveryWrite";
	}
	
	@PostMapping("deliveryWrite")
	public String deliveryWrite(String purchaseNum, String deliveryNum, Model model) {
		deliveryWriteService.execute(purchaseNum, deliveryNum);
		return "redirect:/delivery/deliveryWrite?purchaseNum="+purchaseNum;
	}
	
	@GetMapping("deliveryUpdate")
	public @ResponseBody String deliveryUpdate(String purchaseNum) {
		return deliveryUpdateService.execute(purchaseNum);
	}
	
	@RequestMapping("deliveryDelete")
	public String deliveryDelete(String purchaseNum) {
		deliveryDeleteService.execute(purchaseNum);
		return "redirect:/delivery/deliveryWrite?purchaseNum="+purchaseNum;
	}
}
