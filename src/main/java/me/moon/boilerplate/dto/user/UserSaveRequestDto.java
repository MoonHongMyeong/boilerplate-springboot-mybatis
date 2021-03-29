package me.moon.boilerplate.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String picture;
    private String role;

    @Builder
    public UserSaveRequestDto(String name, String email, String password, String phone, String picture, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.picture = picture;
        this.role = role;
    }
}
