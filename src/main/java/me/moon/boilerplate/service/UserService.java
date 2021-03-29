package me.moon.boilerplate.service;

import lombok.RequiredArgsConstructor;
import me.moon.boilerplate.dto.user.*;
import me.moon.boilerplate.mapper.UserMapper;
import me.moon.boilerplate.utils.PasswordEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    //회원조회
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId) {
        return userMapper.selectUserById(userId);
    }

    //회원가입
    @Transactional
    public void addUser(UserSaveRequestDto userSaveRequestDto) {
        if (toExistEmail(userSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("해당 이메일이 이미 존재합니다.");
        }
        UserSaveRequestDto userDto = encryptUser(userSaveRequestDto);
        userMapper.insertUser(userDto);
    }

    private UserSaveRequestDto encryptUser(UserSaveRequestDto userSaveRequestDto) {
        String encryptedPassword = PasswordEncryptor.encrypt(userSaveRequestDto.getPassword());
        return UserSaveRequestDto.builder()
                .name(userSaveRequestDto.getName())
                .password(encryptedPassword)
                .email(userSaveRequestDto.getEmail())
                .phone(userSaveRequestDto.getPhone())
                .picture(userSaveRequestDto.getPicture())
                .role("user")
                .build();
    }

    private boolean toExistEmail(String email) {
        return userMapper.toExistEmail(email);
    }

    //회원수정
    @Transactional
    public void modifyUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        String name = userUpdateRequestDto.getName();
        String phone = userUpdateRequestDto.getPhone();
        String picture = userUpdateRequestDto.getPicture();
        userMapper.updateUser(userId, name, phone, picture);
    }

    //회원탈퇴
    @Transactional
    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }
    
    //이메일과 비밀번호로 회원 찾기
    public Optional<LoginUserDto> findUserByEmailAndPassword(String email, String password) {

        Optional<LoginUserDto> user = Optional.ofNullable(userMapper.selectUserByEmail(email));

        if (!user.isPresent()) {
            throw new IllegalArgumentException("이메일을 잘못 입력했습니다.");
        }

        boolean isSamePassword = PasswordEncryptor.isMatch(password, user.get().getPassword());

        if (!isSamePassword) {
            throw new IllegalArgumentException("비밀번호를 잘못 입력했습니다.");
        }

        return user;
    }


}
