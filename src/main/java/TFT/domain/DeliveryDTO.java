package TFT.domain;

import lombok.Data;

@Data
public class DeliveryDTO {
	String purchaseNum;
	String deliveryNum;
	String deliveryDate;
	String deliveryStatus;
}
