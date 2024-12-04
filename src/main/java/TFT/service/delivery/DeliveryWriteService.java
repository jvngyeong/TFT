package TFT.service.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.DeliveryMapper;

@Service
public class DeliveryWriteService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(String purchaseNum, String deliveryNum) {
		deliveryMapper.deliveryWrite(purchaseNum, deliveryNum);
	}
}
