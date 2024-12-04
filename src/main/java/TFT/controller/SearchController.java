package TFT.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import TFT.domain.AccountDTO;
import TFT.domain.LeagueEntryDTO;
import TFT.domain.SummonerDTO;
import TFT.service.AccountSearchService;
import TFT.service.SummonerInfoService;
import TFT.service.SummonerSearchService;

@Controller
public class SearchController {
	@Autowired
	AccountSearchService accountSearchService;
	
	@Autowired
	SummonerSearchService summonerSearchService;
	
	@Autowired
	SummonerInfoService summonerInfoService;
	
	@PostMapping("search")
	public String search(String sumName, Model model) {
		AccountDTO accountDTO = accountSearchService.execute(sumName);
		SummonerDTO summonerDTO = summonerSearchService.execute(accountDTO.getPuuid());
		Set<LeagueEntryDTO> leagueEntryDTOs = summonerInfoService.execute(summonerDTO.getId());
		model.addAttribute("leagueEntryDTOs", leagueEntryDTOs);
		sumName = accountDTO.getGameName() + "#" + accountDTO.getTagLine();
		model.addAttribute("sumName", sumName);
		model.addAttribute("summonerDTO", summonerDTO);
		return "thymeleaf/user/userInfo";
	}
}
