package TFT.service.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.DeliveryMapper;

@Service	
public class DeliveryUpdateService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public String execute(String purchaseNum) {
		deliveryMapper.deliveryUpdate(purchaseNum);
		return "200";
	}
}
