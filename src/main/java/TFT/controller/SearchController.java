package TFT.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import TFT.domain.RiotAPI.AccountDTO;
import TFT.domain.RiotAPI.LeagueEntryDTO;
import TFT.domain.RiotAPI.MatchDTO;
import TFT.domain.RiotAPI.SummonerDTO;
import TFT.service.AccountSearchService;
import TFT.service.GetMatchService;
import TFT.service.MatchInfoService;
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
	
	@Autowired
	GetMatchService getMatchService;
	
	@Autowired
	MatchInfoService matchInfoService;
	
	@RequestMapping("search")
	public String search(String sumName, Model model) {
		AccountDTO accountDTO = accountSearchService.execute(sumName);
		SummonerDTO summonerDTO = summonerSearchService.execute(accountDTO.getPuuid());
		//List<String> matchList = getMatchService.execute(accountDTO.getPuuid());
		getMatchService.execute(accountDTO.getPuuid(), model, accountDTO.getGameName());
		Set<LeagueEntryDTO> leagueEntryDTOs = summonerInfoService.execute(summonerDTO.getId());
		model.addAttribute("leagueEntryDTOs", leagueEntryDTOs);
		sumName = accountDTO.getGameName() + "#" + accountDTO.getTagLine();
		model.addAttribute("sumName", sumName);
		model.addAttribute("accountDTO", accountDTO);
		model.addAttribute("summonerDTO", summonerDTO);
		//matchInfoService.execute(matchList, model);
		//matchInfoService.execute2(accountDTO.getPuuid(), model);
		return "thymeleaf/user/userInfo";
	}
}
