package TFT.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TFT.command.EmployeeCommand;
import TFT.domain.EmployeeDTO;
import TFT.mapper.EmployeeMapper;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		String encodedPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		BeanUtils.copyProperties(employeeCommand, dto);
		dto.setEmpPw(encodedPw);
		employeeMapper.employeeUpdate(dto);
	}
}
