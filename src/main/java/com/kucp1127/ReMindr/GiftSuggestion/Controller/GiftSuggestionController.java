package com.kucp1127.ReMindr.GiftSuggestion.Controller;

import com.kucp1127.ReMindr.GiftSuggestion.Service.GiftsSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
public class GiftSuggestionController {

    @Autowired
    private GiftsSuggestionService giftsSuggestionService;


    public Mono<ResponseEntity<?>> getChatOutput(String username,String prompt){
        return giftsSuggestionService.
                generateChat(username,prompt).
                map(response -> ResponseEntity.ok(response));
    }

}
