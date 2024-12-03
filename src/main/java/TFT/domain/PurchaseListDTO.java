package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListDTO")
public class PurchaseListDTO {
	String goodsNum;
	String purchaseNum;
	Integer purchaseQty;
	Integer goodsUnitPrice;
}
