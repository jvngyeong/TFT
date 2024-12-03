package TFT.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("paymentDTO")
public class PaymentDTO {
	String purchaseNum;
	String confirmnumber;
	String cardnum;
	String tid;
	String totalprice;
	String resultmessage;
	String paymethod;
	String appldate;
	String apptime;
	String purchaseName;
}
