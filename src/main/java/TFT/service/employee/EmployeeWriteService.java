package TFT.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.EmployeeCommand;
import TFT.domain.EmployeeDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.EmployeeMapper;
import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employeeCommand, dto);
		String empNum = autoNumMapper.getAutoNum("emp_", "emp_num", "5", "employees");
		dto.setEmpNum(empNum);
		employeeMapper.employeeRegist(dto);
	}
}
