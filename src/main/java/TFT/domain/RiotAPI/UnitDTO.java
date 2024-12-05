package TFT.domain.RiotAPI;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("unitDTO")
public class UnitDTO {
	List<Integer> items;
    String characterId;
    String chosen;
    String name;
    int rarity;
    int tier;
}
