package TFT.service.purchase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import TFT.domain.CartDTO;
import TFT.domain.GoodsCartDTO;
import TFT.mapper.CartMapper;
import TFT.mapper.GoodsMapper;

@Service
public class GoodsBuyService {
	@Autowired
	GoodsMapper goodsMapper;

	@Autowired
	CartMapper cartMapper;

	public void execute(String[] goodsNums, @RequestParam Integer[] cartQties, Model model) {
		List<GoodsCartDTO> list = new ArrayList<GoodsCartDTO>();
		int i = 0;
		String newGoodsNums = "";
		for (String goodsNum : goodsNums) {
			newGoodsNums += goodsNum;
			if (i < goodsNums.length - 1) {
				newGoodsNums += "-";
			}
			GoodsCartDTO dto = new GoodsCartDTO();
			dto.setGoodsDTO(goodsMapper.goodsSelectOne(goodsNum));

			CartDTO cdto = new CartDTO();
			cdto.setCartQty(cartQties[i]);
			cdto.setGoodsNum(goodsNum);
			dto.setCartDTO(cdto);

			list.add(dto);
			i++;
		}
		model.addAttribute("goodsNums", newGoodsNums);
		model.addAttribute("list", list);
	}
}
