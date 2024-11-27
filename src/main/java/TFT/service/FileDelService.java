package TFT.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import TFT.domain.FileDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class FileDelService {
	//장바구니 알고리즘
	public int execute(String orgFile, String storeFile, HttpSession session) {
		int i = 0;
		boolean newFile = true;
		FileDTO dto = new FileDTO();
		dto.setOrgFile(orgFile);
		dto.setStoreFile(storeFile);
		//session에 저장하기 전에 이미 session이 존재하는지 여부를 체크
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileDTO>();
		}
		// session에 존재하면 삭제
		for(FileDTO fd : list) {
			if(fd.getStoreFile().equals(storeFile)) {
				newFile = false;
				list.remove(fd);
				break;
			}
		}
		if(newFile == true) {
			list.add(dto);
			i = 1;
		}
		session.setAttribute("fileList", list);	//list가 null인 경우에는 list가 session이 아니기 때문에 다시 설정해준거임
		return i;
	}
}
