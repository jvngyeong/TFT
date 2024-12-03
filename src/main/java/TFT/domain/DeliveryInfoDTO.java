package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor	
@Alias("deliveryInfoDTO")
public class DeliveryInfoDTO {
	GoodsPLDTO goodsPLDTO;
	PurchaseDTO purchaseDTO;
	PaymentDTO paymentDTO;
	DeliveryDTO deliveryDTO;
}

