package TFT.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.MemberMapper;
import TFT.mapper.WishMapper;

@Service
public class WishCheckService {
	@Autowired
	WishMapper wishMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public String execute(String goodsNum, String memberId) {
		String memberNum = memberMapper.getMemberNum(memberId);
		return wishMapper.wishCheck(goodsNum, memberNum);
	}
}
