package TFT.service.wish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.GoodsDTO;
import TFT.domain.WishDTO;
import TFT.mapper.GoodsMapper;
import TFT.mapper.MemberMapper;
import TFT.mapper.WishMapper;

@Service
public class WishListService {
	@Autowired
	WishMapper wishMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(String memberId, Model model) {
		String memberNum = memberMapper.getMemberNum(memberId);
		List<WishDTO> wishList = wishMapper.wishSelectList(memberNum);
		if(wishList.size() > 0) {
			String[] goodsNums = new String[wishList.size()];
			int i = 0;
			for(WishDTO dto : wishList) {
				String goodsNum = dto.getGoodsNum();
				goodsNums[i] = goodsNum;
				i++;
			}
			List<GoodsDTO> list = goodsMapper.goodsWishListSelect(goodsNums);
			model.addAttribute("list", list);
		}
		else {
			List<GoodsDTO> list = null;
			model.addAttribute("list", list);
		}
	}
}
