package TFT.command;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeCommand {
    String empNum;            // EMP_NUM: 직원 번호 (Not Null)

    @NotEmpty(message = "* 아이디 : 필수 정보입니다.")
    String empId;             // EMP_ID: 직원 ID
    
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,16}$",
            message = "* 비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.")
    @NotEmpty(message = "* 비밀번호 : 필수 정보입니다.")
    String empPw;             // EMP_PW: 직원 비밀번호
    
    @NotEmpty(message = "* 이름 : 필수 정보입니다.")
    String empName;           // EMP_NAME: 직원 이름
    
    @NotEmpty(message = "* 주소 : 필수 정보입니다.")
    String empAddr;           // EMP_ADDR: 직원 주소
    String empAddrDetail;     // EMP_ADDR_DETAIL: 상세 주소
    
    @NotNull(message = "* 우편번호 : 필수 정보입니다.")
    Integer empPost;          // EMP_POST: 우편번호
    
    @NotEmpty(message = "* 전화번호 : 필수 정보입니다.")
    String empPhone;          // EMP_PHONE: 전화번호
    
    @NotEmpty(message = "* 주민등록번호 : 필수 정보입니다.")
    String empJumin;          // EMP_JUMIN: 주민등록번호
    
    @NotEmpty(message = "* 이메일 : 필수 정보입니다.")
    String empEmail;          // EMP_EMAIL: 이메일
    Date empHireDate;         // EMP_HIRE_DATE: 입사일
    
    @NotNull(message = "* 사진 : 필수 정보입니다.")
    MultipartFile empImage;          // EMP_IMAGE: 직원 사진 경로
}
