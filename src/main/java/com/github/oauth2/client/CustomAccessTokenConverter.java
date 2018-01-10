package com.github.oauth2.client;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Aldwin Delgado
 */
@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(claims);
        oAuth2Authentication.setDetails(claims);
        return oAuth2Authentication;
    }
}
