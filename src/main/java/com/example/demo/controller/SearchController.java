package com.example.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.AccountDTO;
import com.example.demo.domain.LeagueEntryDTO;
import com.example.demo.domain.SummonerDTO;
import com.example.demo.service.AccountSearchService;
import com.example.demo.service.SummonerInfoService;
import com.example.demo.service.SummonerSearchService;

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
		System.out.println(summonerDTO.getId());
		Set<LeagueEntryDTO> leagueEntryDTOs = summonerInfoService.execute(summonerDTO.getId());
		model.addAttribute("leagueEntryDTOs", leagueEntryDTOs);
		sumName = accountDTO.getGameName() + "#" + accountDTO.getTagLine();
		model.addAttribute("sumName", sumName);
		return "thymeleaf/user/userInfo";
	}
}
