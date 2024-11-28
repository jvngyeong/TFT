package TFT.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.GoodsCartDTO;
import TFT.mapper.CartMapper;
import TFT.mapper.GoodsMapper;
import TFT.mapper.MemberMapper;

@Service
public class CartListService {
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String memberId, Model model) {
		String memberNum = memberMapper.getMemberNum(memberId);
		List<GoodsCartDTO> list = cartMapper.cartSelectList(memberNum);
		for(GoodsCartDTO dto : list) {
			dto.setTotal(dto.getCartDTO().getCartQty() * dto.getGoodsDTO().getGoodsPrice());
		}
		model.addAttribute("list", list);
	}
}
