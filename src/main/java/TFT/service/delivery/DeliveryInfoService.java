package TFT.service.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.DeliveryInfoDTO;
import TFT.mapper.DeliveryMapper;

@Service
public class DeliveryInfoService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(String purchaseNum, Model model) {
		List<DeliveryInfoDTO> list = deliveryMapper.deliveryInfoSelect(purchaseNum);
		model.addAttribute("list", list);
	}
}
