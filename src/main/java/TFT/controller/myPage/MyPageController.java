package TFT.controller.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.domain.AuthInfoDTO;
import TFT.mapper.EmployeeMapper;
import TFT.mapper.MemberMapper;
import TFT.service.employee.EmployeeInfoService;
import TFT.service.member.MemberInfoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("myPage")
public class MyPageController {
	@Autowired
	MemberInfoService memberInfoService;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	EmployeeInfoService employeeInfoService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping("memMyPage")
	public String memMyPage(String memberId, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(memberId);
		memberInfoService.execute(memberNum, model);
		return "thymeleaf/myPage/memMyPage";
	}
	
	@GetMapping("empMyPage")
	public String empMyPage(String empId, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		employeeInfoService.execute(empNum, model);
		return "thymeleaf/myPage/empMyPage";
	}
}