package TFT.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.mapper.EmployeeMapper;

@Service
public class EmployeeInfoService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(String empNum, Model model) {
		model.addAttribute("empNum", empNum);
		model.addAttribute("dto", employeeMapper.employeeSelectOne(empNum));
	}

}
