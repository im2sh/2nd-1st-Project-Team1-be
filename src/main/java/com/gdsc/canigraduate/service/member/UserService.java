package com.gdsc.canigraduate.service.member;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.dto.UserDto;
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
    public Long join(UserDto userDto){
        User user = userDto.toEntity();
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

}
