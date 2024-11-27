package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartDTO")
public class CartDTO {
	String memberNum;
	String goodsNum;
	Date cartDate;
	String cartQty;
}
