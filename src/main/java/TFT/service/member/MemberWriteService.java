package TFT.service.member;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TFT.command.MemberCommand;
import TFT.domain.MemberDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.MemberMapper;

@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		String encodedPw = passwordEncoder.encode(memberCommand.getMemberPw());
		BeanUtils.copyProperties(memberCommand, dto);
		dto.setMemberPw(encodedPw);
		String memberNum = autoNumMapper.getAutoNum("mem_", "member_num", "5", "members");
		dto.setMemberNum(memberNum);
		memberMapper.memberRegist(dto);
	}
}






