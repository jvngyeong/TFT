package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Alias("goodsDTO")
public class GoodsDTO {
	String goodsNum;
	Integer goodsPrice;
	String goodsContents;
	Integer visitCount;
	Date goodsUpdateDate;
	String empNum;
	Date goodsRegist;
	String updateEmpNum;
	String goodsName;
	
	String goodsMainImage;
	String goodsMainStoreImage;
	
	String goodsDetailImage;
	String goodsDetailStoreImage;
}
