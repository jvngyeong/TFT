package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsIpgoDTO")
public class GoodsIpgoDTO {
	String goodsNum;
	String ipgoNum;
	Integer ipgoQty;
	Date ipgoDate;
	Date madeDate;
	Integer ipgoPrice;
	String empNum;
}
