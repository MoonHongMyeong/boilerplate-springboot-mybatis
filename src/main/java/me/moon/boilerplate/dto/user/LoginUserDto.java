package me.moon.boilerplate.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginUserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String picture;
    private String modified_date;
    private String role;

}
