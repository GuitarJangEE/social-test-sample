package com.github.socialtest.test;

import com.github.socialtest.test.users.UserEntity;
import com.github.socialtest.test.users.UserJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserJpa userJpa;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Integer memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Integer findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userJpa.findByEmailJoin(oAuthInfoResponse.getEmail())
                .map(userEntity -> userEntity.getUserId())
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Integer newMember(OAuthInfoResponse oAuthInfoResponse) {
        UserEntity member = UserEntity.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickName(oAuthInfoResponse.getNickname())

//                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return 1;
    }
}
