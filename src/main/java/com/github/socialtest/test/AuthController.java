package com.github.socialtest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/kakao")
    public String getCode(@RequestParam(name = "code") String code ){
        return "code : "+code;
    }

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

//    @PostMapping("/naver")
//    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }
}