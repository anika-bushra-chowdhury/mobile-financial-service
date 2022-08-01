package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.User;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:28 PM
 */

public interface UserService {
    User userRegistration(User registrationRequest);
}
