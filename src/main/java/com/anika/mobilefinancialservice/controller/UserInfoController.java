package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.dto.UserBasicInfoRequest;
import com.anika.mobilefinancialservice.dto.UserBasicInfoResponse;
import com.anika.mobilefinancialservice.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:21 PM
 */

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserInfoController {

    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User registrationRequest) {
        return userService.userRegistration(registrationRequest);
    }

    @GetMapping(value = "/get-user-info/{phnNO}")
    public User getUserInfo(@PathVariable String phnNO) {
        return userService.getUserInfo(phnNO);
    }

    @GetMapping(value = "/logIn")
    public UserBasicInfoResponse getUserInfo(@RequestBody UserBasicInfoRequest infoRequest) {
        return userService.getUserBasicInfo(infoRequest);
    }
}
