package com.wbint.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.StandardClaimAccessor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AadController {
    private final Logger log = LoggerFactory.getLogger(AadController.class);

    @GetMapping("/")
    public String index(
            OAuth2AuthenticationToken authentication,
            @RegisteredOAuth2AuthorizedClient("azure") OAuth2AuthorizedClient azureClient
    ) {
        log.debug("userName: " + authentication.getName());
        return "index";
    }

    @GetMapping("/graph")
    @ResponseBody
    public String graph(
            @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphClient
    ) {
        // toJsonString() is just a demo.
        // oAuth2AuthorizedClient contains access_token. We can use this access_token to access resource server.
        return graphClient.toString();
    }
}
