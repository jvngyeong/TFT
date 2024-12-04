package TFT.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.PaymentDTO;
import TFT.domain.PurchaseDTO;
import TFT.domain.PurchaseDeliveryDTO;

@Mapper
public interface PurchaseMapper {

	String selectPurchaseNum();

	void purchaseInsert(PurchaseDTO dto);

	PurchaseDTO purchaseSelectOne(String purchaseNum);

	void paymentInsert(PaymentDTO dto);

	void purchaseStatusUpdate(String purchaseNum);

	//List<PurchaseDTO> purchaseSelectList(String memberNum);
	List<PurchaseDeliveryDTO> purchaseSelectList(String memberNum);
	
	void purchaseListInsert(Map<String, Object> map);

}
