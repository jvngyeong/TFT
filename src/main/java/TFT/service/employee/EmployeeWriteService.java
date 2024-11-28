package TFT.service.employee;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import TFT.command.EmployeeCommand;
import TFT.domain.EmployeeDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.EmployeeMapper;

@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		String encodedPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		BeanUtils.copyProperties(employeeCommand, dto);
		String empNum = autoNumMapper.getAutoNum("emp_", "emp_num", "5", "employees");
		dto.setEmpPw(encodedPw);
		dto.setEmpNum(empNum);

		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		
		MultipartFile mf = employeeCommand.getEmpImage();
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
		dto.setEmpImage(originalFile);
		dto.setEmpStoreImage(storeFileName);
		
		employeeMapper.employeeRegist(dto);
	}
}
