package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.WishDTO;

@Mapper
public interface WishMapper {
	public int wishMerge(String goodsNum, String memberNum);

	public String wishCheck(String goodsNum, String memberNum);

	public List<WishDTO> wishSelectList(String memberId);
}
