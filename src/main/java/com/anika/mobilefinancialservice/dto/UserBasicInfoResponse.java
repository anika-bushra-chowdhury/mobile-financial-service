package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoResponse {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String photo;

    @NotNull
    private UserType userType;

    private String phoneNumber;

    private String errorMessage;
}
