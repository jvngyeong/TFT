package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseDeliveryDTO")
public class PurchaseDeliveryDTO {
	PurchaseDTO purchaseDTO;
	DeliveryDTO deliveryDTO;
}
