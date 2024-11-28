package TFT.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.CartMapper;
import TFT.mapper.MemberMapper;

@Service
public class CartQtyUpdateService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	CartMapper cartMapper;
	public void execute(String goodsNum, String cartQty, String memberId) {
		String memberNum = memberMapper.getMemberNum(memberId);
		cartMapper.cartQtyUpdate(memberNum, goodsNum, cartQty);
	}

}
