package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/register")
    public User register(User registrationRequest) {
        userService.userRegistration(registrationRequest);
        return null;
    }
}
