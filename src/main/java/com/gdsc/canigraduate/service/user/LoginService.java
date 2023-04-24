package com.gdsc.canigraduate.service.user;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by im2sh
 */

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public User authenticated(String classId, String password){
        List<User> findUsers = userRepository.findAllByClassId(classId);
        if(findUsers.isEmpty()){
            return null;
        }
        User user = findUsers.get(0);
        if(!(user.getUserPw().equals(password))){
            return null;
        }
        return user;
    }
}
