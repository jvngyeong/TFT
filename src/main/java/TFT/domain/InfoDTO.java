package TFT.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import TFT.domain.RiotAPI.ParticipantDTO;
import lombok.Data;

@Data
@Alias("infoDTO")
public class InfoDTO {
	 long gameDatetime; 
	 float gameLength;
	 String gameVariation;
	 String gameVersion;
	 List<ParticipantDTO> participants;
	 int queueId;
	 int tftSetNumber;
}
