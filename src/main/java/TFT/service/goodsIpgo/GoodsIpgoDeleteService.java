package TFT.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoDeleteService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(String ipgoNum) {
		goodsIpgoMapper.goodsIpgoDelete(ipgoNum);
	}
}
