package TFT.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsIpgoCommand {
	@NotEmpty(message = "상품 번호를 선택해주세요.")
	String goodsNum;
	
	String ipgoNum;
	
	@NotNull(message = "입고 갯수를 입력해주세요.")
	Integer ipgoQty;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date ipgoDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "제조일을 선택해주세요.")
	Date madeDate;
	
	@NotNull(message = "입고 가격을 입력해주세요.")
	Integer ipgoPrice;
	String empNum;
}
