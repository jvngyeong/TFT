package TFT.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import TFT.domain.SummonerDTO;

@Service
public class SummonerSearchService {

    private final RestTemplate restTemplate;

    @Value("${riot.api.key}")
    private String riotApiKey;

    public SummonerSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public SummonerDTO execute(String puuid) {
    	// URL 생성
        String url = "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-puuid/" + puuid + "?api_key=" + riotApiKey;
        
        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("Origin", "https://developer.riotgames.com");

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        // API 호출 및 응답을 SummonerResponse 객체로 변환
        ResponseEntity<SummonerDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, SummonerDTO.class);
        return response.getBody();
    }
}
