package com.kucp1127.ReMindr.GiftSuggestion.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class GiftsSuggestionService {
    private final WebClient webClient;

    @Value("${adarsh.api.key}")
    private String apiKey;

    public GiftsSuggestionService(WebClient.Builder webClientBuilder) {
        // Base URL for the Gemini API
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com").build();
    }

    public Mono<String> generateChat(String username, String prompt) {


        String updatedPrompt = " "+ prompt +"Analyze the provided reminder data and generate a notification message" +
                " with tailored suggestions. If the reminder is for a birthday, produce a concise notification that " +
                "includes 3–5 personalized gift ideas (considering the recipient’s interests and a moderate budget) " +
                "along with brief explanations for each. If the reminder is for office work, generate a notification " +
                "listing essential items or accessories to ensure nothing important is forgotten, highlighting their " +
                "practicality. For an anniversary reminder, craft a notification that recommends thoughtful and creative " +
                "gift ideas reflecting shared experiences or milestones, with short rationales for each." +
                " Format the suggestions as a clear, bullet-point list suitable for display as a push notification message.";

        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", updatedPrompt);

        Map<String, Object> partsWrapper = new HashMap<>();
        partsWrapper.put("parts", Collections.singletonList(textPart));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", Collections.singletonList(partsWrapper));

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.0-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class);
    }

}
