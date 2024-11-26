package TFT.mapper;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.LoginDTO;

@Mapper
public interface LoginMapper {

	public LoginDTO loginIdCheck(String id);

	public String getLoginPw(LoginDTO dto);

}
