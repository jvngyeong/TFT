package TFT.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	@NotNull(message = "* 가격을 입력해주세요.")
	Integer goodsPrice;
	
	@NotEmpty(message = "* 상품 설명을 입력해주세요.")
	String goodsContents;
	
	Integer visitCount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsUpdateDate;
	
	String empNum;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsRegist;
	
	String updateEmpNum;
	
	@NotEmpty(message = "* 상품 이름을 입력해주세요.")
	String goodsName;
	
	//DB에 파일명과 내용을 모두 저장하기 위해 MultipartFile 타입 사용!
	MultipartFile goodsMainImage;
	//MultipartFile goodsMainStoreImage;
	
	MultipartFile[] goodsDetailImage;
	//MultipartFile goodsDetailStoreImage;
}