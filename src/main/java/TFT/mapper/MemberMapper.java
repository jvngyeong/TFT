package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.MemberDTO;

@Mapper
public interface MemberMapper {

	public void memberRegist(MemberDTO dto);

	public List<MemberDTO> memberListSelect();

	public MemberDTO memberSelectOne(String memberNum);

	public void memberUpdate(MemberDTO dto);

	public void memberDelete(String memberNum);

	public String getMemberNum(String memberId);

}
