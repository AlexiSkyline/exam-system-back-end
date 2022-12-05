package com.exams.system.app.models.response;

import com.exams.system.app.models.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LoginResponse {
    private User userDetails;
    private String accessToken;
}