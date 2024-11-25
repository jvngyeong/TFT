package TFT.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TFT.mapper.MemberMapper;

@Service
public class MemberPwConService {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MemberMapper memberMapper;

	public String execute(String memberPw, String encodedPw) {
		if (passwordEncoder.matches(memberPw, encodedPw)) {
			return "200";
		}
		return "000";
	}
}
