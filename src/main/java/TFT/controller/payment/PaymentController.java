package TFT.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.service.purchase.INIstdpayPcReturn;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	INIstdpayPcReturn iNIstdpayPcReturn;
	
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest req){
		iNIstdpayPcReturn.execute(req);
		return "redirect:/";
	}
	
	@RequestMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
}