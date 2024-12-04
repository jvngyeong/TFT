package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.DeliveryDTO;
import TFT.domain.DeliveryInfoDTO;

@Mapper
public interface DeliveryMapper {
	List<DeliveryInfoDTO> deliveryInfoSelect(String purchaseNum);

	void deliveryWrite(String purchaseNum, String deliveryNum);

	List<DeliveryDTO> deliveryCheck(String purchaseNum);

	void deliveryUpdate(String purchaseNum);

	void deliveryDelete(String purchaseNum);
}
