package TFT.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import TFT.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeRegist(EmployeeDTO dto);

	public List<EmployeeDTO> employeeSelectList();

	public EmployeeDTO employeeSelectOne(String empNum);

	public void employeeUpdate(EmployeeDTO dto);

	public void employeeDelete(String empNum);

	public String getEmpNum(String userId);
}
