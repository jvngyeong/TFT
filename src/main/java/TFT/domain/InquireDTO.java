package TFT.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquireDTO")
public class InquireDTO {
    String inquireNum;        
    String memberNum;      
    String goodsNum;       
    String inquireSubject;  
    String inquireContents; 
    String inquireKind;     
    Date inquireDate;    
    String inquireAnswer;  
    Date inquireAnswerDate; 
    String empNum;        
}
