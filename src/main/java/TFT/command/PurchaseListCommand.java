package TFT.command;

import lombok.Data;

@Data	
public class PurchaseListCommand {
	String goodsNum;
	String purchaseNum;
	Integer purchaseQty;
	Integer goodsUnitPrice;
}
