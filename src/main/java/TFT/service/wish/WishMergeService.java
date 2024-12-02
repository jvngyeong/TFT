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
		System.out.println("서비스");
		String memberNum = memberMapper.getMemberNum(memberId);
		int i = wishMapper.wishMerge(goodsNum, memberNum);
		System.out.println("i = " + i);
		return wishMapper.wishCheck(goodsNum, memberNum);
	}
}
