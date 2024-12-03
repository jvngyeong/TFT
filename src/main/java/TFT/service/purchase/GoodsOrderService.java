package TFT.service.purchase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFT.command.PurchaseCommand;
import TFT.domain.AuthInfoDTO;
import TFT.domain.PurchaseDTO;
import TFT.mapper.MemberMapper;
import TFT.mapper.PurchaseMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsOrderService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PurchaseMapper purchaseMapper;
	public String execute(PurchaseCommand purchaseCommand, Integer[] cartQties, HttpSession session) {
		String purchaseNum = purchaseMapper.selectPurchaseNum();
		PurchaseDTO dto = new PurchaseDTO();
		BeanUtils.copyProperties(purchaseCommand, dto);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		dto.setMemberNum(memberNum);
		dto.setPurchasePrice(purchaseCommand.getTotalPaymentPrice());
		dto.setPurchaseNum(purchaseNum);
		
		purchaseMapper.purchaseInsert(dto);
		
		//구매 리스트
		String goodsNums[] = purchaseCommand.getGoodsNums().split("-");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchaseNum", purchaseNum);
		map.put("memberNum", memberNum);
		map.put("goodsNums", goodsNums);
		map.put("cartQties", cartQties);
		purchaseMapper.purchaseListInsert(map);
		
		return purchaseNum;
	}
}
