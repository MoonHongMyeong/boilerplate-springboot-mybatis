package me.moon.boilerplate.controller;

import lombok.RequiredArgsConstructor;
import me.moon.boilerplate.dto.user.*;
import me.moon.boilerplate.service.LoginService;
import me.moon.boilerplate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    //회원조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto user = userService.getUser(userId);
        return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
    }

    //회원가입
    @PostMapping()
    public ResponseEntity register(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        userService.addUser(userSaveRequestDto);
        return new ResponseEntity(message("회원가입 성공!"), HttpStatus.CREATED);
    }

    //회원수정
    @PutMapping("/{userId}")
    public ResponseEntity modifyUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.modifyUser(userId, userUpdateRequestDto);
        return new ResponseEntity(message("수정을 완료했습니다."), HttpStatus.OK);
    }

    //회원탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity withdrawal(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(message("회원탈퇴를 완료했습니다."), HttpStatus.OK);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserDto userDto) {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Optional<LoginUserDto> user = userService.findUserByEmailAndPassword(email, password);
        if (user.isPresent()) {
            loginService.loginUser(user.get().getEmail());
            return new ResponseEntity(message("로그인 성공!"), HttpStatus.OK);
        } else {
            return new ResponseEntity(message("로그인 실패 ㅜㅜ"), HttpStatus.NOT_FOUND);
        }
    }
    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity logout() {
        loginService.logoutUser();
        return new ResponseEntity(message("로그아웃하셨습니다."), HttpStatus.OK);
    }

    private Map<String,String> message(String msg){
        Map<String, String> message = new HashMap<>();
        message.put("msg", msg);
        return message;
    }
}
