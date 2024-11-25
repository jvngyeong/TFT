package TFT.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.mapper.MemberMapper;

@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memeberMapper;
	public void execute(String memberNum) {
		memeberMapper.memberDelete(memberNum);
	}

}
