package TFT.mapper;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeRegist(EmployeeDTO dto);
}
