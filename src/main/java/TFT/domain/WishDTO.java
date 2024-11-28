package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("wishDTO")
@Data
public class WishDTO {
	String memberNum;
	String goodsNum;
	Date wishDate;
}
