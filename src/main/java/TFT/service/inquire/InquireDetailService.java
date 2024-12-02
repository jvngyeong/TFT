package TFT.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.InquireDTO;
import TFT.mapper.InquireMapper;

@Service
public class InquireDetailService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String inquireNum, Model model) {
		InquireDTO dto = inquireMapper.inquireSelectOne(inquireNum);
		model.addAttribute("dto", dto);
	}
}
