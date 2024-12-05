package TFT.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import TFT.domain.RiotAPI.AccountDTO;

@Service
public class AccountSearchService {

    private final RestTemplate restTemplate;

    @Value("${riot.api.key}")
    private String riotApiKey;

    public AccountSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Summoner 정보를 가져오는 메서드
    public AccountDTO execute(String summonerName) {
        // '#'의 인덱스 찾기
        int index = summonerName.indexOf('#');

        // '#'이 존재하지 않거나, 인덱스가 잘못된 경우 처리
        if (index == -1 || index == 0 || index == summonerName.length() - 1) {
            throw new IllegalArgumentException("Invalid summoner name format.");
        }

        // gameName과 tagLine 분리
        String gameName = summonerName.substring(0, index);
        String tagLine = summonerName.substring(index + 1);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

        // URL 생성
        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine
                + "?api_key=" + riotApiKey;

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("Origin", "https://developer.riotgames.com");

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출 및 응답을 SummonerResponse 객체로 변환
        ResponseEntity<AccountDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, AccountDTO.class);
        return response.getBody();
    }
}

