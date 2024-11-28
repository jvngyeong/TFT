package TFT.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.domain.AuthInfoDTO;
import TFT.mapper.CartMapper;
import TFT.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartGoodsRemoveService {
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public String execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		int i = cartMapper.cartGoodsRemove(memberNum, goodsNum);
		if(i > 0) {
			return auth.getUserId();
		}
		return null;
	}
}
