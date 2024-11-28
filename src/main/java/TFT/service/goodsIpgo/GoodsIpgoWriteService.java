package TFT.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.GoodsIpgoCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.GoodsIpgoDTO;
import TFT.mapper.AutoNumMapper;
import TFT.mapper.EmployeeMapper;
import TFT.mapper.GoodsIpgoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsIpgoWriteService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		
		String ipgoNum = autoNumMapper.getAutoNum("ipgo_", "ipgo_num", "6", "goods_ipgo");
		dto.setIpgoNum(ipgoNum);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setEmpNum(empNum);
		
		goodsIpgoMapper.goodsIpgoWrite(dto);
	}
}
