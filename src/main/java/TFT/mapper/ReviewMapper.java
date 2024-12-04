package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.PurchaseListReviewDTO;
import TFT.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {

	void reviewMerge(ReviewDTO dto);

	List<PurchaseListReviewDTO> reviewInfo(String purchaseNum);

	List<ReviewDTO> reviewSelectList(String goodsNum);

}
