package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.Balance;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.dto.UserBasicInfoRequest;
import com.anika.mobilefinancialservice.dto.UserBasicInfoResponse;
import com.anika.mobilefinancialservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:21 PM
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User registrationRequest) {

        log.info("Registration request: {}", registrationRequest.toString());

        User response = userService.userRegistration(registrationRequest);
        log.info("Registration response: {}", response.toString());

        return response;
    }

    @GetMapping(value = "/get-user-info/{phnNO}")
    public User getUserInfo(@PathVariable String phnNO) {

        log.info("User info query request for: {}", phnNO);

        User response = userService.getUserInfo(phnNO);
        log.info("User info response: {}", response.toString());

        return response;
    }

    @PostMapping(value = "/logIn")
    public UserBasicInfoResponse logIn(@RequestBody UserBasicInfoRequest infoRequest) {

        log.info("Log in request: {}", infoRequest.toString());

        UserBasicInfoResponse response = userService.logIn(infoRequest);
        log.info("Log in response: {}", response.toString());

        return response;
    }

    @GetMapping(value = "/get-balance/{phnNo}")
    public Balance getUserBalance(@PathVariable String phnNo) {

        log.info("Balance query for: {}", phnNo);

        Balance response = userService.getBalance(phnNo);
        log.info("Balance response: {}", response.toString());

        return response;
    }
}
