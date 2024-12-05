package TFT.domain.RiotAPI;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("traitDTO")
public class TraitDTO {
	String name;
    int numUnits;
    int style;
    int tier_current;
    int tier_total;
}
