package TFT.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoNumMapper {
	public String getAutoNum(String prefix, String column, String len, String table);
}
