package TFT.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import TFT.domain.RiotAPI.LeagueEntryDTO;

@Service
public class SummonerInfoService {
    private final RestTemplate restTemplate;

    @Value("${riot.api.key}")
    private String riotApiKey;

    public SummonerInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<LeagueEntryDTO> execute(String id) {
        // URL 생성
        String url = "https://kr.api.riotgames.com/tft/league/v1/entries/by-summoner/" + id + "?api_key=" + riotApiKey;

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("Origin", "https://developer.riotgames.com");

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출 및 응답을 Set<LeagueEntryDTO>로 변환
        ResponseEntity<LeagueEntryDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, LeagueEntryDTO[].class);
        
        // 배열을 Set으로 변환
        Set<LeagueEntryDTO> leagueEntrySet = new HashSet<>(List.of(response.getBody()));
        
        return leagueEntrySet;
    }
}
