package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("loginDTO")
public class LoginDTO {
	String id;
	String password;
	String grade;
}
