package TFT.service.review;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.ReviewCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.ReviewDTO;
import TFT.mapper.GoodsMapper;
import TFT.mapper.MemberMapper;
import TFT.mapper.ReviewMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ReviewWriteService {
	@Autowired
	ReviewMapper reviewMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public void execute(ReviewCommand reviewCommand, HttpSession session) {
		ReviewDTO dto = new ReviewDTO();
		BeanUtils.copyProperties(reviewCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		dto.setMemberId(auth.getUserId());
		reviewMapper.reviewMerge(dto);
	}
}
