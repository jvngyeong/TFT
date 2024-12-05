package TFT.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import TFT.domain.RiotAPI.MatchDTO;

@Service
public class MatchInfoService {
	 private final RestTemplate restTemplate;

    @Value("${riot.api.key}")
    private String riotApiKey;

    public MatchInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void execute(List<String> matchList, Model model) {
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("Origin", "https://developer.riotgames.com");

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<MatchDTO> matchDTOList = new ArrayList<MatchDTO>();
        for(String match : matchList) {
	        String url = "https://asia.api.riotgames.com/tft/match/v1/matches/"+match+"/?api_key="+riotApiKey;
	        ResponseEntity<MatchDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, MatchDTO.class);
	        System.out.println(response.getBody().getMetadata().getMatch_id());
	        matchDTOList.add(response.getBody());
        }
        model.addAttribute("matchDTOList", matchDTOList);
    }
}
