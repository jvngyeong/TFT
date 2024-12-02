package TFT.service.inquire;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.InquireCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.InquireDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.InquireMapper;
import TFT.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireWriteService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	AutoNumMapper autoNumMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(InquireCommand inquireCommand, HttpSession session) {
		InquireDTO dto = new InquireDTO();
		BeanUtils.copyProperties(inquireCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		dto.setMemberNum(memberNum);
		
		inquireMapper.inquireWrite(dto);
	}
}
