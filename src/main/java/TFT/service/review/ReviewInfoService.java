package TFT.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.PurchaseListReviewDTO;
import TFT.mapper.ReviewMapper;

@Service
public class ReviewInfoService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String purchaseNum, Model model) {
		List<PurchaseListReviewDTO> plrList = reviewMapper.reviewInfo(purchaseNum);
		model.addAttribute("plrList", plrList);
	}
}
