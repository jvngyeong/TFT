package TFT.controller.employee;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TFT.command.EmployeeCommand;
import TFT.service.employee.EmployeeDeleteService;
import TFT.service.employee.EmployeeInfoService;
import TFT.service.employee.EmployeeListService;
import TFT.service.employee.EmployeePwConService;
import TFT.service.employee.EmployeeUpdateService;
import TFT.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeWriteService employeeWriteService;
	
	@Autowired
	EmployeeListService employeeListService;
	
	@Autowired
	EmployeeInfoService employeeInfoService;
	
	@Autowired
	EmployeePwConService employeePwConService;
	
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	
	@GetMapping("employeeList")
	public String employeeList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("employeeWrite")
	public String employeeWrite(Model model) {
		model.addAttribute(new EmployeeCommand());
		return "thymeleaf/employee/employeeWrite";
	}
	
	@PostMapping("employeeWrite")
	public @ResponseBody ResponseEntity<?> employeeWrite(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
		System.out.println(result);
		Map<String, String> errors = new HashMap<>();
		if(!result.hasErrors()) {
			if(!employeeCommand.getEmpPw().equals(employeeCommand.getEmpPwCon())) {
				errors.put("empPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
				return ResponseEntity.ok().body(errors);
			}
			employeeWriteService.execute(employeeCommand);
			return ResponseEntity.ok().body(errors);
		}
		else {
			if(!employeeCommand.getEmpPw().equals(employeeCommand.getEmpPwCon())) {
				errors.put("empPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
			}
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.ok().body(errors);
		}
	}
	
	@RequestMapping("employeeInfo")
	public String employeeInfo(String empNum, Model model) {
		employeeInfoService.execute(empNum, model);
		return "thymeleaf/employee/employeeInfo";
	}
	
	@GetMapping("empPwCheck")
	public String empPwCheck(String empNum, String task, Model model) {
		employeeInfoService.execute(empNum, model);
		model.addAttribute("task", task);
		return "thymeleaf/employee/empPwCheck";
	}
	
	@PostMapping("empPwCheck")
	public @ResponseBody String empPwCheck(String empPw, String encodedPw) {
		return employeePwConService.execute(empPw, encodedPw);
	}
	
	@GetMapping("employeeUpdate")
	public String employeeUpdate(String empNum, Model model) {
		employeeInfoService.execute(empNum, model);
		return "thymeleaf/employee/employeeUpdate";
	}
	
	@PostMapping("employeeUpdate")
	public @ResponseBody ResponseEntity<?> employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
		System.out.println(result);
		Map<String, String> errors = new HashMap<>();
		if(!result.hasErrors()) {
			if(!employeeCommand.getEmpPw().equals(employeeCommand.getEmpPwCon())) {
				errors.put("empPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
				return ResponseEntity.ok().body(errors);
			}
			employeeUpdateService.execute(employeeCommand);
			return ResponseEntity.ok().body(errors);
		}
		else {
			if(!employeeCommand.getEmpPw().equals(employeeCommand.getEmpPwCon())) {
				errors.put("empPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
			}
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.ok().body(errors);
		}
	}
	
	@GetMapping("employeeDelete")
	public String employeeDelete(String empNum) {
		employeeDeleteService.execute(empNum);
		return "thymeleaf/employee/employeeList";
	}
}
