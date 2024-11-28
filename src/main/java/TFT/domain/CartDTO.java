package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("cartDTO")
@Data
public class CartDTO {
	String memberNum;
	String goodsNum;
	Date cartDate;
	Integer cartQty;
}
