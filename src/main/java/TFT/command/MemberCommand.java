package TFT.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;       // MEMBER_NUM (NOT NULL)
    @NotEmpty(message = "* 이름 : 필수 정보입니다.")
	String memberName;      // MEMBER_NAME
    
    @NotEmpty(message = "* 아이디 : 필수 정보입니다.")
	String memberId;        // MEMBER_ID
    
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,16}$",
            message = "* 비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.")
    @NotEmpty(message = "* 비밀번호 : 필수 정보입니다.")
	String memberPw;        // MEMBER_PW
    
    @NotEmpty(message = "* 비밀번호 확인 : 필수 정보입니다.")
	String memberPwCon;        // MEMBER_PW
    
    @NotEmpty(message = "* 주소 : 필수 정보입니다.")
	String memberAddr;      // MEMBER_ADDR
	String memberAddrDetail; // MEMBER_ADDR_DETAIL
	
    @NotNull(message = "* 우편번호 : 필수 정보입니다.")
	Integer memberPost;     // MEMBER_POST
    
	Date memberRegist;      // MEMBER_REGIST
	
	@NotNull(message = "* 성별 : 필수 정보입니다.")
	char gender;            // GENDER
	
    @NotEmpty(message = "* 전화번호 : 필수 정보입니다.")
	String memberPhone1;    // MEMBER_PHONE1
	String memberPhone2;    // MEMBER_PHONE2
	
    @NotEmpty(message = "* 이메일 : 필수 정보입니다.")
	String memberEmail;     // MEMBER_EMAIL
    
    @NotNull(message = "* 생년월일 : 필수 정보입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;       // MEMBER_BIRTH
	Integer memberPoint;    // MEMBER_POINT
	char memberEmailConf;   // MEMBER_EMAIL_CONF
}
