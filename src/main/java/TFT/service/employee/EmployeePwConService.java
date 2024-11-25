package TFT.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TFT.mapper.EmployeeMapper;

@Service
public class EmployeePwConService {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(String empPw, String encodedPw) {
		if(passwordEncoder.matches(empPw, encodedPw)) {
			return "200";
		}
		return "000";
	}
}
