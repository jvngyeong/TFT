package TFT.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.PurchaseDeliveryDTO;
import TFT.mapper.PurchaseMapper;

@Service
public class PurchaseInfoService {	
	@Autowired
	PurchaseMapper purchaseMapper;
	
	public void execute(String memberNum, Model model) {
		//List<PurchaseDTO> list = purchaseMapper.purchaseSelectList(memberNum);
		
		List<PurchaseDeliveryDTO> list = purchaseMapper.purchaseSelectList(memberNum);
		
		model.addAttribute("list", list);
	}
}
