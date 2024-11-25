package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Alias("memberDTO")
public class MemberDTO {
	String memberNum;       // MEMBER_NUM (NOT NULL)
	String memberName;      // MEMBER_NAME
	String memberId;        // MEMBER_ID
	String memberPw;        // MEMBER_PW
	String memberAddr;      // MEMBER_ADDR
	String memberAddrDetail; // MEMBER_ADDR_DETAIL
	Integer memberPost;     // MEMBER_POST
	Date memberRegist;      // MEMBER_REGIST
	char gender;            // GENDER
	String memberPhone1;    // MEMBER_PHONE1
	String memberPhone2;    // MEMBER_PHONE2
	String memberEmail;     // MEMBER_EMAIL
	Date memberBirth;       // MEMBER_BIRTH
	Integer memberPoint;    // MEMBER_POINT
	char memberEmailConf;   // MEMBER_EMAIL_CONF
}
