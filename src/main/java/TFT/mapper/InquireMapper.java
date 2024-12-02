package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.InquireDTO;

@Mapper
public interface InquireMapper {

	void inquireWrite(InquireDTO dto);

	List<InquireDTO> inquireSelectList(String goodsNum);

	InquireDTO inquireSelectOne(String inquireNum);

	void inquireUpdate(InquireDTO dto);

}
