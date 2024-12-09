package TFT.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import TFT.domain.RiotAPI.UnitDTO;

@Service
public class GetMatchService {
    private final RestTemplate restTemplate;

    @Value("${riot.api.key}")
    private String riotApiKey;

    public GetMatchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void execute(String puuid, Model model, String gameName) {
    	int asd = 0;
    	long placement = 0;
    	long firstPlace = 0;
        List<MatchDTO> matchDTOList = new ArrayList<>();
        int start = 0;
        int count = 100;
        List<String> usedChampion = new ArrayList<String>();
        List<String> usedTrait = new ArrayList<String>();
        List<String> usedItem = new ArrayList<String>();

        // 매치 ID 리스트를 받아오기 위한 URL 설정
        String url = "https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/" + puuid + "/ids?start=" + start + "&count=" + count + "&api_key=" + riotApiKey;

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
        headers.set("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.set("Origin", "https://developer.riotgames.com");

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 반복문을 통해 여러 페이지에서 매치 ID 가져오기
        while (true) {
            // API 호출하여 매치 ID 리스트 받아오기
            ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<String>>() {}
            );
            asd += 1;
            System.out.println("위에서 호출함 / 호출 횟수 = " + asd);

            List<String> matchIds = response.getBody();
            if (matchIds == null || matchIds.isEmpty()) {
                break; // 더 이상 매치 ID가 없으면 종료
            }

            // 각 매치 ID에 대해 상세 정보 가져오기
            for (String matchId : matchIds) {
            	if (matchDTOList.size() >= 20) {
                    break;
                }
                String matchUrl = "https://asia.api.riotgames.com/tft/match/v1/matches/" + matchId + "/?api_key=" + riotApiKey;

                try {
                    ResponseEntity<MatchDTO> matchResponse = restTemplate.exchange(matchUrl, HttpMethod.GET, entity, MatchDTO.class);
                    MatchDTO matchDTO = matchResponse.getBody();
                    asd += 1;
                    System.out.println("아래에 호출함 / 호출 횟수 = " + asd);

                    // 매치 정보가 유효할 때
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
                        if(matchDTO.getInfo().getTft_set_number() != 13) {
                        	break;
                        }
                        for(ParticipantDTO pdto : matchDTO.getInfo().getParticipants()) {
                        	if(pdto.getRiotIdGameName().equals(gameName)) {
                        		placement += pdto.getPlacement();
                        		if(pdto.getPlacement() == 1) {
                        			firstPlace += 1;
                        		}
                        	}
                        }
                        
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
                Thread.sleep(200); // API 호출 간 간격 주기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        
        Map<String, Integer> frequencyMap = new HashMap<>();
        Map<String, Integer> frequencyMap2 = new HashMap<>();
        Map<String, Integer> frequencyMap3 = new HashMap<>();

        // 아이템과 챔피언을 중복 없이 추가하는 대신, 등장한 횟수를 계산
        for (MatchDTO dto : matchDTOList) {
            for (ParticipantDTO pdto : dto.getInfo().getParticipants()) {
                if(pdto.getRiotIdGameName().equals(gameName)) {
                	for(TraitDTO tdto : pdto.getTraits()) {
                		String trait = tdto.getName();
                		usedTrait.add(trait);
                		frequencyMap3.put(trait, frequencyMap3.getOrDefault(trait, 0) + 1);
                	}
                	
                	for (UnitDTO udto : pdto.getUnits()) {
                        String championId = udto.getCharacter_id();
                        usedChampion.add(championId);  // 중복 가능
                        // 챔피언의 등장 횟수를 계산
                        frequencyMap.put(championId, frequencyMap.getOrDefault(championId, 0) + 1);
                        for (String item : udto.getItemNames()) {
                            usedItem.add(item);  // 중복 가능
                            // 아이템의 등장 횟수를 계산
                            frequencyMap2.put(item, frequencyMap2.getOrDefault(item, 0) + 1);
                        }
                    }
                }
            }
        }

        // 빈도별로 내림차순 정렬
        List<Map.Entry<String, Integer>> usedChampionList = new ArrayList<>(frequencyMap.entrySet());
        usedChampionList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        List<Map.Entry<String, Integer>> usedItemList = new ArrayList<>(frequencyMap2.entrySet());
        usedItemList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        
        List<Map.Entry<String, Integer>> usedTraitList = new ArrayList<>(frequencyMap3.entrySet());
        usedTraitList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        // 결과 출력 (상위 5개)
        for (int i = 0; i < 5; i++) {
            if (i < usedItemList.size()) {
                System.out.println(i + "번째로 많이 사용한 아이템 = " + usedItemList.get(i).getKey() + " / " + usedItemList.get(i).getValue() + "회");
            }
            if (i < usedChampionList.size()) {
                System.out.println(i + "번째로 많이 사용한 챔피언 = " + usedChampionList.get(i).getKey() + " / " + usedChampionList.get(i).getValue() + "회");
            }
            if (i < usedTraitList.size()) {
                System.out.println(i + "번째로 많이 사용한 특성 = " + usedTraitList.get(i).getKey() + " / " + usedTraitList.get(i).getValue() + "회");
            }
        }

     // 챔피언 및 아이템에 대한 순위 총합을 계산할 Map 생성
        Map<String, Integer> championPlacementSum = new HashMap<>();
        Map<String, Integer> itemPlacementSum = new HashMap<>();
        Map<String, Integer> traitPlacementSum = new HashMap<>();

        for (MatchDTO dto : matchDTOList) {
            for (ParticipantDTO pdto : dto.getInfo().getParticipants()) {
                if (pdto.getRiotIdGameName().equals(gameName)) {
                	for(TraitDTO tdto : pdto.getTraits()) {
                		for (int i = 0; i < 6; i++) {
                			if(tdto.getName().equals(usedTraitList.get(i).getKey())) {
                				traitPlacementSum.put(tdto.getName(), traitPlacementSum.getOrDefault(tdto.getName(), 0) + pdto.getPlacement());
                			}
                		}
                	}
                    for (UnitDTO udto : pdto.getUnits()) {
                        for (int i = 0; i < 6; i++) {
                            // 챔피언 ID가 일치하면
                            if (udto.getCharacter_id().equals(usedChampionList.get(i).getKey())) {
                                // 챔피언 순위 합계에 추가
                                championPlacementSum.put(udto.getCharacter_id(), 
                                    championPlacementSum.getOrDefault(udto.getCharacter_id(), 0) + pdto.getPlacement());
                            }
                            // 아이템이 일치하면
                            for (String item : udto.getItemNames()) {
                                if (item.equals(usedItemList.get(i).getKey())) {
                                    // 아이템 순위 합계에 추가
                                    itemPlacementSum.put(item, 
                                        itemPlacementSum.getOrDefault(item, 0) + pdto.getPlacement());
                                }
                            }
                        }
                    }
                }
            }
        }

        // 결과 출력 (챔피언 순위 총합)
        for (Map.Entry<String, Integer> entry : championPlacementSum.entrySet()) {
            System.out.println("챔피언: " + entry.getKey() + " 순위 총합: " + entry.getValue());
        }

        // 결과 출력 (아이템 순위 총합)
        for (Map.Entry<String, Integer> entry : itemPlacementSum.entrySet()) {
            System.out.println("아이템: " + entry.getKey() + " 순위 총합: " + entry.getValue());
        }

        // 결과 출력 (특성 순위 총합)
        for (Map.Entry<String, Integer> entry : traitPlacementSum.entrySet()) {
            System.out.println("특성: " + entry.getKey() + " 순위 총합: " + entry.getValue());
        }
        
        System.out.println("총 매치 개수: " + matchDTOList.size());
        model.addAttribute("matchDTOList", matchDTOList);
        model.addAttribute("placement", placement);
        model.addAttribute("firstPlace", firstPlace);
        model.addAttribute("usedItemList", usedItemList);
        model.addAttribute("usedChampionList", usedChampionList);
        model.addAttribute("usedTraitList", usedTraitList);
        model.addAttribute("championPlacementSum", championPlacementSum);
        model.addAttribute("itemPlacementSum", itemPlacementSum);
        model.addAttribute("traitPlacementSum", traitPlacementSum);
    }
}
