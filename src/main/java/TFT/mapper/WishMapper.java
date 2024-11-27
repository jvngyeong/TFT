package TFT.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishMapper {
	public void wishMerge(String goodsNum, String memberNum);

	public String wishCheck(String goodsNum, String memberNum);
}
