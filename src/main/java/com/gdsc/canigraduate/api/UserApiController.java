package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.user.SessionConst;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.response.ResponseDto;
import com.gdsc.canigraduate.dto.user.UserLoginRequest;
import com.gdsc.canigraduate.dto.user.UserLoginResponse;
import com.gdsc.canigraduate.dto.user.UserPwModificationRequest;
import com.gdsc.canigraduate.dto.user.UserSignUpRequest;
import com.gdsc.canigraduate.service.user.LoginService;
import com.gdsc.canigraduate.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by im2sh
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class UserApiController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/user/signup")
    public ResponseEntity<ResponseDto> saveUser(@Validated @RequestBody UserSignUpRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ResponseDto("올바른 입력이 아닙니다."));
        }
        userService.join(request);
        return ResponseEntity.ok().body(new ResponseDto("회원가입이 완료되었습니다."));
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Validated @RequestBody UserLoginRequest userLoginRequest, BindingResult bindingResult, HttpServletRequest request){
        String classId = userLoginRequest.getClassId();
        String userPw = userLoginRequest.getUserPw();

        User user = loginService.authenticated(classId, userPw);
        if(user.equals(null) || bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new UserLoginResponse(null,null, null));
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, user);

        return ResponseEntity.ok().body(new UserLoginResponse(user.getClassId(),user.getName(),user.getToken()));
    }

    @PostMapping("/user/logout")
    public ResponseEntity<ResponseDto> logoutUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return ResponseEntity.ok().body(new ResponseDto("로그아웃 되었습니다."));
    }

    @PostMapping("/user/modifyPw/{token}")
    public ResponseEntity<ResponseDto> modifyUserPw(@PathVariable("token") String token,@Validated @RequestBody UserPwModificationRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ResponseDto("비밀번호를 다시 한 번 확인하세요."));
        }
        userService.modifyUserPw(token, request);
        return ResponseEntity.ok().body(new ResponseDto("비밀번호가 변경되었습니다."));
    }
}
