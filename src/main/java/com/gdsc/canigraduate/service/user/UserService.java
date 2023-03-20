package com.gdsc.canigraduate.service.user;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.user.UserPwModificationRequest;
import com.gdsc.canigraduate.dto.user.UserSignUpRequest;
import com.gdsc.canigraduate.exception.IncorrectPassword;
import com.gdsc.canigraduate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserSignUpRequest userSignUpRequest){
        User user = userSignUpRequest.toEntity();
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user){
        List<User> findUser = userRepository.findAllByClassId(user.getClassId());
        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 학번입니다.");
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    public User findByClassId(String classId){
        List<User> users = userRepository.findAllByClassId(classId);
        return users.get(0);
    }
    public User findByToken(String token){
        return userRepository.findUserByToken(token);
    }

    @Transactional
    public void modifyUserPw(String token, UserPwModificationRequest request){
        User user = userRepository.findUserByToken(token);

        if(user == null){
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }


        if(!(user.getUserPw().equals(request.getNowUserPw()))) {
            throw new IncorrectPassword("이전 비밀번호가 일치하지 않습니다.");
        }
        else if(!(request.getUpdateUserPw().equals(request.getPwConfirm()))) {
            throw new IncorrectPassword("변경하려는 번호와 입력한 비밀번호를 다시 한 번 확인해주십시오.");
        }
        else {
            user.pwUpdate(request.getUpdateUserPw());
        }

    }
}
