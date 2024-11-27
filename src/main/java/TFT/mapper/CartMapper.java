package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.GoodsCartDTO;

@Mapper
public interface CartMapper {
	public Integer cartMerge(String goodsNum, String memberNum, String cartQty);

	public List<GoodsCartDTO> cartSelectList(String memberId);
}
