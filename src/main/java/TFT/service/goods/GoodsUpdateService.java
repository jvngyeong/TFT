package TFT.service.goods;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import TFT.command.GoodsCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.FileDTO;
import TFT.domain.GoodsDTO;
import TFT.mapper.EmployeeMapper;
import TFT.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, HttpSession session) {
		String originalTotal = "";
		String storeTotal = "";
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		GoodsDTO dto = new GoodsDTO();
		BeanUtils.copyProperties(goodsCommand, dto);
		dto.setUpdateEmpNum(empNum);
		
		//파일 시스템에서 삭제하기
		//1. 디렉토리 정보 가져오기
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		System.out.println(fileDir);
		if(goodsCommand.getGoodsMainImage() != null) {
			if(!goodsCommand.getGoodsMainImage().getOriginalFilename().isEmpty()) {
				//2. 파일 객체를 불러오기
				MultipartFile mf = goodsCommand.getGoodsMainImage();
				//3. 파일 이름 가져오기
				String originalFile = mf.getOriginalFilename();
				//4. 확장자 분리하기
				String extension = originalFile.substring(originalFile.lastIndexOf('.'));
				//5. 새로운 파일명 만들기
				String storeName = UUID.randomUUID().toString().replace("-", "");
				//6. 새로운 파일명과 확장자를 붙이기
				String storeFileName = storeName + extension;
				//7. 파일 객체 만들기
				File file = new File(fileDir + "/" + storeFileName);
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dto.setGoodsMainImage(originalFile);
				dto.setGoodsMainStoreImage(storeFileName);
			}
		}
		if(!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
			for(MultipartFile mf : goodsCommand.getGoodsDetailImage()) {
				String originalFile = mf.getOriginalFilename();
				//4. 확장자 분리하기
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				//5. 새로운 파일명 만들기
				String storeName = UUID.randomUUID().toString().replace("-", "");
				//6. 새로운 파일명과 확장자를 붙이기
				String storeFileName =  storeName + extension;
				//7. 파일 객체 만들기
				File file = new File(fileDir + "/" + storeFileName);
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				originalTotal += originalFile + "/";
				storeTotal += storeFileName + "/";
			}
			dto.setGoodsDetailImage(originalTotal);
			dto.setGoodsDetailStoreImage(storeTotal);
		}
		
		//session에 있는 값들을 지우고 새로운 값들을 삽입
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
		
		//파일의 정보를 DB로부터 가져오기(파일 시스템을 지우기 위해서)
		GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsCommand.getGoodsNum());
		if(goodsDTO.getGoodsDetailImage() != null) {
			//session에 있는 정보를 DB로부터 제거
			List<String> dbOrg = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailImage().split("[/`]")));
		    List<String> dbStore = new ArrayList<>(Arrays.asList(goodsDTO.getGoodsDetailStoreImage().split("[/`]")));
			if(list != null) {
				for(FileDTO fdto : list) {
					for(String img : dbOrg) {
						if(fdto.getOrgFile().equals(img)) {
							dbOrg.remove(fdto.getOrgFile());
							dbStore.remove(fdto.getStoreFile());
							break;
						}
					}
				}
			}
			for(String img : dbOrg) {
				originalTotal += img + "/";
			}
			for(String img : dbStore) {
				storeTotal += img + "/";
			}
		}
		dto.setGoodsDetailImage(originalTotal);
		dto.setGoodsDetailStoreImage(storeTotal);
		int i = goodsMapper.goodsUpdate(dto);
		if(i > 0) {
			if(list != null) {
				for(FileDTO fd : list) {
					File file = new File(fileDir + "/" + fd.getStoreFile());
					if(file.exists()) {
						file.delete();
					}
				}
			}
		}
	}
}
