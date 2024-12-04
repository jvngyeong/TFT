package TFT.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.ReviewDTO;
import TFT.mapper.ReviewMapper;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String goodsNum, Model model) {
		List<ReviewDTO> list = reviewMapper.reviewSelectList(goodsNum);
		model.addAttribute("reviewList", list);
	}
}
