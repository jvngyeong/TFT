package TFT.domain.RiotAPI;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("participantDTO")
public class ParticipantDTO {
	CompanionDTO companion;
    int goldLeft;
    int lastRound;
    int level;
    int placement;
    int playersEliminated;
    String puuid;
    String riotIdGameName;
    String riotIdTagline;
    float time_eliminated;
    int totalDamageToPlayers;
    List<TraitDTO> traits;
    List<UnitDTO> units;
}
