package com.adaLaundry.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ResponseTokenDTO {
    private String username;

    private String role;

    private String token;
}
