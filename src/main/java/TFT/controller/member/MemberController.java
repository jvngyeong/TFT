package TFT.controller.member;

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

import TFT.command.MemberCommand;
import TFT.service.member.MemberDeleteService;
import TFT.service.member.MemberInfoService;
import TFT.service.member.MemberListService;
import TFT.service.member.MemberPwConService;
import TFT.service.member.MemberUpdateService;
import TFT.service.member.MemberWriteService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberWriteService memberWriteService;
	
	@Autowired
	MemberListService memberListService;
	
	@Autowired
	MemberInfoService memberInfoService;
	
	@Autowired
	MemberPwConService memberPwConService;
	
	@Autowired
	MemberUpdateService memberUpdateService;
	
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@GetMapping("memberWrite")
	public String memberWrite() {
		return "thymeleaf/member/memberWrite";
	}
	
	@PostMapping("memberWrite")
	public @ResponseBody ResponseEntity<?> memberWrite(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		System.out.println(result);
		Map<String, String> errors = new HashMap<>();
		if(!result.hasErrors()) {
			if(!memberCommand.getMemberPw().equals(memberCommand.getMemberPwCon())) {
				errors.put("memberPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
				return ResponseEntity.ok().body(errors);
			}
			memberWriteService.execute(memberCommand);
			return ResponseEntity.ok().body(errors);
		}
		else {
			if(!memberCommand.getMemberPw().equals(memberCommand.getMemberPwCon())) {
				errors.put("memberPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
			}
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.ok().body(errors);
		}
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memberList";
	}
	
	@RequestMapping("memberInfo")
	public String memberInfo(String memberNum, Model model) {
		memberInfoService.execute(memberNum, model);
		return "thymeleaf/member/memberInfo";
	}
	@GetMapping("memberPwCheck")
	public String memberPwCheck(String memberNum, String task, Model model) {
		memberInfoService.execute(memberNum, model);
		model.addAttribute("task", task);
		return "thymeleaf/member/memberPwCheck";
	}
	@PostMapping("memberPwCheck")
	public @ResponseBody String memberPwCheck(String memberPw, String encodedPw) {
		return memberPwConService.execute(memberPw, encodedPw);
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(String memberNum, Model model) {
		memberInfoService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
	}
	@PostMapping("memberUpdate")
	public @ResponseBody ResponseEntity<?> memberUpdate(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		System.out.println(result);
		Map<String, String> errors = new HashMap<>();
		if(!result.hasErrors()) {
			if(!memberCommand.getMemberPw().equals(memberCommand.getMemberPwCon())) {
				errors.put("memberPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
				return ResponseEntity.ok().body(errors);
			}
			memberUpdateService.execute(memberCommand);
			return ResponseEntity.ok().body(errors);
		}
		else {
			if(!memberCommand.getMemberPw().equals(memberCommand.getMemberPwCon())) {
				errors.put("memberPwCon", "비밀번호 확인 : 비밀번호와 동일하게 입력해주세요.");
			}
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.ok().body(errors);
		}
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(String memberNum) {
		memberDeleteService.execute(memberNum);
		return "thymeleaf/member/memberList";
	}
}
