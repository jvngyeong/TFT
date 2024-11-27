package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsCartDTO")
public class GoodsCartDTO {
	GoodsDTO goodsDTO;
	CartDTO cartDTO;
}
