package com.gdsc.canigraduate.service.user;

import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.UserGraduationInfo;
import com.gdsc.canigraduate.dto.user.UserGraduationInfoRequest;
import com.gdsc.canigraduate.repository.UserGraduationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserGraduationService {
    private final UserGraduationInfoRepository userGraduationInfoRepository;
    private final UserService userService;

    @Transactional
    public Long save(String token,UserGraduationInfo info){
        User user = userService.findByToken(token);
        UserGraduationInfo userGraduationInfo = user.getUserGraduationInfo();
        if(userGraduationInfo != null)
            userGraduationInfoRepository.delete(userGraduationInfo);
        userService.setGraduationInfo(user,info);
        return userGraduationInfoRepository.save(info).getId();
    }

    public UserGraduationInfo findById(Long id){
        Optional<UserGraduationInfo> info = userGraduationInfoRepository.findById(id);
        UserGraduationInfo userGraduationInfo = info.get();
        return userGraduationInfo;
    }
    public UserGraduationInfo toEntity(UserGraduationInfoRequest request){
        UserGraduationInfo info = request.toEntity(request.isBasic(), request.isCoreSociety(), request.isCoreScience(), request.isCulture(), request.isEnglish(), request.isIntern(), request.isConsulting());
        return info;
    }

    @Transactional
    public boolean checkGraduation(String token){
        User user = userService.findByToken(token);
        UserGraduationInfo info = findById(user.getUserGraduationInfo().getId());
        if(info.isBasic() && info.isCoreSociety() && info.isCoreScience() && info.isCulture() && info.isIntern() && info.isEnglish() && info.isConsulting()) {
            userService.setIsConditionGraduation(user, true);
            return true;
        }
        userService.setIsConditionGraduation(user, false);
        return false;
    }
}
