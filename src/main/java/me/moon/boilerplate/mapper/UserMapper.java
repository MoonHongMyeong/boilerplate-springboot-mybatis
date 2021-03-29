package me.moon.boilerplate.mapper;

import me.moon.boilerplate.dto.user.LoginUserDto;
import me.moon.boilerplate.dto.user.UserResponseDto;
import me.moon.boilerplate.dto.user.UserSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    boolean toExistEmail(String email);
    void insertUser(UserSaveRequestDto userDto);
    LoginUserDto selectUserByEmail(String email);
    void deleteUser(Long id);
    void updateUser(Long id, String name, String phone, String picture);
    UserResponseDto selectUserById(Long id);
}
