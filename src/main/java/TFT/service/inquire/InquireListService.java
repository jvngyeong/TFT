package TFT.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import TFT.domain.InquireDTO;
import TFT.mapper.InquireMapper;

@Service
public class InquireListService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String goodsNum, Model model) {
		List<InquireDTO> list = inquireMapper.inquireSelectList(goodsNum);
		model.addAttribute("list", list);
	}
}
