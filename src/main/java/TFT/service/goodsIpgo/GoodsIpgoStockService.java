package TFT.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.GoodsIpgoDTO;
import TFT.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoStockService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(String goodsNum, Model model) {
		GoodsIpgoDTO dto = goodsIpgoMapper.goodsIpgoStockSelect(goodsNum);
		model.addAttribute("ipgoDTO", dto);
	}
}
