package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
