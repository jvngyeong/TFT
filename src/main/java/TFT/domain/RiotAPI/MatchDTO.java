package TFT.domain.RiotAPI;

import org.apache.ibatis.type.Alias;

import TFT.domain.InfoDTO;
import lombok.Data;

@Data
@Alias("matchDTO")
public class MatchDTO {
	MetadataDTO metadata;
	InfoDTO info;
}
