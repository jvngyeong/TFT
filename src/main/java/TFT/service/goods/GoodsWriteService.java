package TFT.service.goods;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import TFT.command.GoodsCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.GoodsDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.EmployeeMapper;
import TFT.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(GoodsCommand goodsCommand, HttpSession session) {
		GoodsDTO goodsDTO = new GoodsDTO();
		BeanUtils.copyProperties(goodsCommand, goodsDTO);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		goodsDTO.setEmpNum(empNum);
		
		String goodsNum = autoNumMapper.getAutoNum("goods_", "goods_num", "7", "goods");
		goodsDTO.setGoodsNum(goodsNum);
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		
		MultipartFile mf = goodsCommand.getGoodsMainImage();
		String originalFile = mf.getOriginalFilename();
		
		String extension = originalFile.substring(originalFile.lastIndexOf('.'));
		
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension;
		
		File file = new File(fileDir + "/" + storeFileName);
		
		try {
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goodsDTO.setGoodsMainImage(originalFile);
		goodsDTO.setGoodsMainStoreImage(storeFileName);
		
		if(!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
			String originalTotal = "";
			String storeTotal = "";
			for(MultipartFile mpf : goodsCommand.getGoodsDetailImage()) {
				originalFile = mpf.getOriginalFilename();
				extension = originalFile.substring(originalFile.lastIndexOf('.'));
				storeName = UUID.randomUUID().toString().replace("-", "");
				storeFileName = storeName + extension;
				file = new File(fileDir + "/" + storeFileName);
				try {
					mpf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
			goodsDTO.setGoodsDetailImage(originalTotal);
			goodsDTO.setGoodsDetailStoreImage(storeTotal);
		}
		goodsMapper.goodsInsert(goodsDTO);
	}
}
