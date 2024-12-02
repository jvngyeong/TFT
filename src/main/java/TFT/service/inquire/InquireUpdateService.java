package TFT.service.inquire;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.InquireCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.InquireDTO;
import TFT.mapper.EmployeeMapper;
import TFT.mapper.InquireMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireUpdateService {
	@Autowired
	InquireMapper inquireMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(InquireCommand inquireCommand, HttpSession session) {
		InquireDTO dto = new InquireDTO();
		BeanUtils.copyProperties(inquireCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setEmpNum(empNum);
		inquireMapper.inquireUpdate(dto);
	}
}
