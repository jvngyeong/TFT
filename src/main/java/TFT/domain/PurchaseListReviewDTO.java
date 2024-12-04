package TFT.domain;

import lombok.Data;

@Data
public class PurchaseListReviewDTO {
	PurchaseListDTO purchaseListDTO;
	ReviewDTO reviewDTO;
	GoodsDTO goodsDTO;
}
