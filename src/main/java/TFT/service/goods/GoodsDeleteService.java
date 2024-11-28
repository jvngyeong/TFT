package TFT.service.goods;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.domain.FileDTO;
import TFT.domain.GoodsDTO;
import TFT.mapper.GoodsMapper;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(String goodsNum) {
		GoodsDTO dto = goodsMapper.goodsSelectOne(goodsNum);
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		File file = new File(fileDir + "/" + dto.getGoodsMainStoreImage());
		if (file.exists())
			file.delete();
		if (dto.getGoodsDetailImage() != null) {
			for (String fileName : dto.getGoodsDetailStoreImage().split("/")) {
				file = new File(fileDir + "/" + fileName);
				if (file.exists())
					file.delete();
			}
		}
		goodsMapper.goodsDelete(goodsNum);
	}
}
