package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsPLDTO")
public class GoodsPLDTO {
	PurchaseListDTO purchaseListDTO;
	GoodsDTO goodsDTO;
}
