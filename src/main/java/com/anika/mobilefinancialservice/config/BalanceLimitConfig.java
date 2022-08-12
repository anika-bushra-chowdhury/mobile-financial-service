package com.anika.mobilefinancialservice.config;

import com.anika.mobilefinancialservice.enums.UserType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 11:08 AM
 */

public enum BalanceLimitConfig {

    CU_BALANCE_CONFIG(UserType.CUSTOMER, BigDecimal.ZERO, null),
    AG_BALANCE_CONFIG(UserType.AGENT, BigDecimal.ZERO, null),
    SYSTEM_BALANCE_CONFIG(UserType.SYSTEM, null, null),
    ;

    private final UserType userType;
    private final BigDecimal minBalance;
    private final BigDecimal maxBalance;

    BalanceLimitConfig(UserType userType, BigDecimal minBalance, BigDecimal maxBalance) {
        this.userType = userType;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
    }


    public UserType getUserType() {
        return userType;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public BigDecimal getMaxBalance() {
        return maxBalance;
    }

    private static final Map<UserType, BalanceLimitConfig> MAP_BY_USER_TYPE = new HashMap<>();

    static void populateMap() {
        for (BalanceLimitConfig config : values()) {
            MAP_BY_USER_TYPE.put(config.userType, config);
        }
    }

    public static BalanceLimitConfig getByUserType(UserType userType) {
        return MAP_BY_USER_TYPE.get(userType);
    }

}
