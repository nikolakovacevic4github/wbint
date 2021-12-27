package com.wbint.web.rest;

import com.wbint.config.ApplicationProperties;
import com.wbint.config.AsyncConfiguration;
import com.wbint.service.WBService;
import com.wbint.service.dto.UserExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/wb")
public class WBServiceResource {

    private final ApplicationProperties applicationProperties;

    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AsyncConfiguration.class);
    private final WBService wbService;

    public WBServiceResource(ApplicationProperties applicationProperties, WBService wbService) {
        this.applicationProperties = applicationProperties;
        this.wbService = wbService;
    }

    @GetMapping("/searchUser")
    public ResponseEntity<UserExist> createUser(@RequestParam String email) {
        log.debug("REST request search user with email: {}", email);
        UserExist userExist = new UserExist();
        wbService.getWBToken();
        Boolean isExist = wbService.isUserExist(email);
        userExist.setUserExist(isExist);
        if(isExist) {
            userExist.setRedirectUrl(applicationProperties.getRedirectUrl());
        }
        return ResponseEntity.ok(userExist);
    }
}
