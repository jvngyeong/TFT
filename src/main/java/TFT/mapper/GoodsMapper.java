package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {
	public void goodsInsert(GoodsDTO goodsDTO);

	public List<GoodsDTO> goodsSelectList();

	public GoodsDTO goodsSelectOne(String goodsNum);

	public int goodsUpdate(GoodsDTO dto);

	public void goodsDelete(String goodsNum);

	public List<GoodsDTO> goodsWishListSelect(String[] goodsNums);

	public void getGoodsNum(String purchaseNum, String goodsName);
}
