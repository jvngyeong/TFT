package TFT.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.GoodsIpgoCommand;
import TFT.domain.GoodsIpgoDTO;
import TFT.mapper.GoodsIpgoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsIpgoUpdateService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		goodsIpgoMapper.goodsIpgoUpdate(dto);
	}

}
