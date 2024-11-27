package TFT.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.CartMapper;
import TFT.mapper.MemberMapper;

@Service
public class CartMergeService {
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	public String execute(String goodsNum, String memberId, String cartQty) {
		String memberNum = memberMapper.getMemberNum(memberId);
		Integer i = cartMapper.cartMerge(goodsNum, memberNum, cartQty);
		if(i > 0) {
			return memberNum;
		}
		return "";
	}
}
