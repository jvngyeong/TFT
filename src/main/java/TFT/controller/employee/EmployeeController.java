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
import TFT.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeWriteService employeeWriteService;
	
	@GetMapping("employeeInfo")
	public String employeeInfo() {
		return "thymeleaf/employee/employeeInfo";
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
			employeeWriteService.execute(employeeCommand);
			return ResponseEntity.ok().body(errors);
		}
		else {
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.ok().body(errors);
		}
	}
}
