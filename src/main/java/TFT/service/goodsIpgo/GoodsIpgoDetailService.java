package TFT.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.GoodsIpgoDTO;
import TFT.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoDetailService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(String ipgoNum, Model model) {
		GoodsIpgoDTO dto = goodsIpgoMapper.goodsIpgoSelectOne(ipgoNum);
		model.addAttribute("dto", dto);
	}
}
