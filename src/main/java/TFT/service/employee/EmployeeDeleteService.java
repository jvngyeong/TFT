package TFT.service.employee;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.domain.EmployeeDTO;
import TFT.mapper.EmployeeMapper;

@Service
public class EmployeeDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empNum) {
		EmployeeDTO dto = employeeMapper.employeeSelectOne(empNum);
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = "C:\\Real_Time_Data_Process\\eclipse-workspace\\TFT\\target\\classes\\static\\upload";
		File file = new File(fileDir + "/" + dto.getEmpStoreImage());
		System.out.println(file.getName());
//		if (file.exists())
			file.delete();
		
		employeeMapper.employeeDelete(empNum);
	}
}
