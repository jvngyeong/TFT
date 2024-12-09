package TFT.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import TFT.domain.RiotAPI.MatchDTO;
import TFT.domain.RiotAPI.ParticipantDTO;
import TFT.domain.RiotAPI.TraitDTO;

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
	        for (ParticipantDTO participant : response.getBody().getInfo().getParticipants()) {
	            participant.setTraits(
	                participant.getTraits().stream()
	                    .sorted(
	                        Comparator.comparingInt((TraitDTO trait) -> trait.getStyle() == 3 ? 0 : 1) // style이 3인 경우 우선
	                                  .thenComparing(Comparator.comparingInt(TraitDTO::getTier_current).reversed()) // tier_current 내림차순
	                    )
	                    .collect(Collectors.toList())
	            );
	        }

	        matchDTOList.add(response.getBody());
        }
        model.addAttribute("matchDTOList", matchDTOList);
    }
    
    public void execute2(String puuid, Model model) {
        List<MatchDTO> matchDTOList = new ArrayList<>();
        int start = 0;
        int count = 100;

        while (true) {
            // URL 생성
            String url = "https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/" 
                         + puuid + "/ids?start=" + start + "&count=" + count + "&api_key=" + riotApiKey;

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
            headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
            headers.set("Origin", "https://developer.riotgames.com");

            // HttpEntity 생성
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // API 호출
            ResponseEntity<List<String>> response;
            try {
                response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<String>>() {}
                );
            } catch (Exception e) {
                System.err.println("Failed to fetch match IDs: " + e.getMessage());
                break;
            }

            List<String> matchIds = response.getBody();
            if (matchIds == null || matchIds.isEmpty()) {
                break; // 더 이상 매치 ID가 없으면 종료
            }

            for (String matchId : matchIds) {
                String url2 = "https://asia.api.riotgames.com/tft/match/v1/matches/" 
                              + matchId + "/?api_key=" + riotApiKey;

                try {
                    ResponseEntity<MatchDTO> response2 = restTemplate.exchange(url2, HttpMethod.GET, entity, MatchDTO.class);
                    MatchDTO matchDTO = response2.getBody();

                    // 참가자 정렬
                    if (matchDTO != null) {
                        for (ParticipantDTO participant : matchDTO.getInfo().getParticipants()) {
                            participant.setTraits(
                                participant.getTraits().stream()
                                    .sorted(
                                        Comparator.comparingInt((TraitDTO trait) -> trait.getStyle() == 3 ? 0 : 1) // style이 3인 경우 우선
                                                  .thenComparing(Comparator.comparingInt(TraitDTO::getTier_current).reversed()) // tier_current 내림차순
                                    )
                                    .collect(Collectors.toList())
                            );
                        }
                    	System.out.println(matchDTO.getInfo().getTftSetNumber());
                        matchDTOList.add(matchDTO);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to fetch match details for ID " + matchId + ": " + e.getMessage());
                }

                // 20개가 쌓이면 루프 종료
                if (matchDTOList.size() >= 20) {
                    break;
                }
            }
            // 20개가 쌓이면 루프 종료
            if (matchDTOList.size() >= 20) {
                break;
            }
            start += count; // 다음 페이지로 이동
            try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        System.out.println("길이 !! = " + matchDTOList.size());
        model.addAttribute("rank20", matchDTOList);
    }
}
