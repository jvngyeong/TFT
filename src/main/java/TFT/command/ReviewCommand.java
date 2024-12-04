package TFT.command;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewCommand {
	Integer reviewNum;
    String goodsNum;
    String purchaseNum; 
    Date reviewDate;
    String reviewContents;   
    String memberId;
}
