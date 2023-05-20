package com.gdsc.canigraduate.api;

import com.gdsc.canigraduate.domain.user.SessionConst;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.UserGraduationInfo;
import com.gdsc.canigraduate.dto.response.ResponseDto;
import com.gdsc.canigraduate.dto.user.*;
import com.gdsc.canigraduate.service.user.LoginService;
import com.gdsc.canigraduate.service.user.UserGraduationService;
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
    private final UserGraduationService userGraduationService;

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

    @PostMapping("/user/verification/{token}")
    public ResponseEntity<ResponseDto> verificationUser(@PathVariable("token")String token){
        userService.checkVerificationUser(token);
        boolean verification = userService.isVerificationUser(token);
        if(verification == false)
            return ResponseEntity.ok().body(new ResponseDto("졸업요건에 만족하지 못했습니다."));
        return ResponseEntity.ok().body(new ResponseDto("졸업요건에 만족합니다."));
    }

    @GetMapping("/user/credit/{token}")
    public ResponseEntity<UserCreditResponse> showCredit(@PathVariable("token")String token){
        User user = userService.findByToken(token);
        return ResponseEntity.ok().body(new UserCreditResponse(user.getMajorCredit(),user.getCultureCredit(),user.getNormalCredit(),user.getBasicMajorCredit(),user.getTechMajorCredit(),user.getPresentCredit()));
    }

    @PostMapping("user/graduation/{token}")
    public ResponseEntity<ResponseDto> graduationUser(@RequestBody UserGraduationInfoRequest info, @PathVariable String token){
        UserGraduationInfo userGraduationInfo = userGraduationService.toEntity(info);
        userGraduationService.save(token,userGraduationInfo);
        boolean graduation = userGraduationService.checkGraduation(token);
        if(!graduation)
            return ResponseEntity.ok().body(new ResponseDto("졸업요건에 만족하지 못하였습니다."));

        return ResponseEntity.ok().body(new ResponseDto("졸업요건에 만족합니다."));
    }
}
