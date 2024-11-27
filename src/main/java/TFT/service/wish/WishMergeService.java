package TFT.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.MemberMapper;
import TFT.mapper.WishMapper;

@Service
public class WishMergeService {
	@Autowired
	WishMapper wishMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public String execute(String goodsNum, String memberId) {
		String memberNum = memberMapper.getMemberNum(memberId);
		wishMapper.wishMerge(goodsNum, memberNum);
		return wishMapper.wishCheck(goodsNum, memberNum);
	}
}
