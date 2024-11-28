package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.GoodsIpgoDTO;

@Mapper
public interface GoodsIpgoMapper {
	public void goodsIpgoWrite(GoodsIpgoDTO dto);

	public List<GoodsIpgoDTO> goodsIpgoSelectList();

	public GoodsIpgoDTO goodsIpgoSelectOne(String ipgoNum);

	public void goodsIpgoUpdate(GoodsIpgoDTO dto);

	public void goodsIpgoDelete(String ipgoNum);
}
