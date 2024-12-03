package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.DeliveryInfoDTO;

@Mapper
public interface DeliveryMapper {

	List<DeliveryInfoDTO> deliveryInfoSelect(String purchaseNum);
	
}
