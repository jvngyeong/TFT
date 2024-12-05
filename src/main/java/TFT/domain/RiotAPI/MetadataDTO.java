package TFT.domain.RiotAPI;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("metadataDTO")
public class MetadataDTO {
	String data_version;
	String match_id;
	List<String> participants;
}
