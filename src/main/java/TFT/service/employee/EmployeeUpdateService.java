package TFT.service.employee;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import TFT.command.EmployeeCommand;
import TFT.domain.EmployeeDTO;
import TFT.domain.FileDTO;
import TFT.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(EmployeeCommand employeeCommand, HttpSession session) {
		EmployeeDTO dto = new EmployeeDTO();
		String encodedPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		BeanUtils.copyProperties(employeeCommand, dto);
		dto.setEmpPw(encodedPw);
		
		//파일 시스템에서 삭제하기
		//1. 디렉토리 정보 가져오기
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		if(employeeCommand.getEmpImage() != null) {
			if(!employeeCommand.getEmpImage().getOriginalFilename().isEmpty()) {
				//2. 파일 객체를 불러오기
				MultipartFile mf = employeeCommand.getEmpImage();
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
				dto.setEmpImage(originalFile);
				dto.setEmpStoreImage(storeFileName);
			}
		}
		
		//session에 있는 값들을 지우고 새로운 값들을 삽입
		List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
		
		int i = employeeMapper.employeeUpdate(dto);
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
