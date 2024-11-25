package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("employeeDTO")
public class EmployeeDTO {
	String empNum; // EMP_NUM: 직원 번호 (Not Null)
	String empId; // EMP_ID: 직원 ID
	String empPw; // EMP_PW: 직원 비밀번호
	String empName; // EMP_NAME: 직원 이름
	String empAddr; // EMP_ADDR: 직원 주소
	String empAddrDetail; // EMP_ADDR_DETAIL: 상세 주소
	Integer empPost; // EMP_POST: 우편번호
	String empPhone; // EMP_PHONE: 전화번호
	String empJumin; // EMP_JUMIN: 주민등록번호
	String empEmail; // EMP_EMAIL: 이메일
	Date empHireDate; // EMP_HIRE_DATE: 입사일
	MultipartFile empImage; // EMP_IMAGE: 직원 사진 경로
}
