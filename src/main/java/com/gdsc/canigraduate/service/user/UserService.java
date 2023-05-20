package com.gdsc.canigraduate.service.user;

import com.gdsc.canigraduate.domain.Department;
import com.gdsc.canigraduate.domain.user.User;
import com.gdsc.canigraduate.domain.user.UserGraduationInfo;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureDetail;
import com.gdsc.canigraduate.domain.user.lecture.UserLectureType;
import com.gdsc.canigraduate.dto.user.UserPwModificationRequest;
import com.gdsc.canigraduate.dto.user.UserSignUpRequest;
import com.gdsc.canigraduate.exception.IncorrectPassword;
import com.gdsc.canigraduate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by im2sh
 */

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

    @Transactional
    public Long save(User user){
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

    public List<UserLecture> findUserLectureListByUserId(Long userId){
        return userRepository.findUserLectureListById(userId);
    };

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

    @Transactional
    public void deleteAllUserLecture(Long userId){
        userRepository.deleteAllULById(userId);
    }

    @Transactional
    public void checkVerificationUser(String token){
        User user = findByToken(token);
        Department department = user.getDepartment();
        if(department.equals(Department.심화컴퓨터공학))
            deepComputer(user);
        else if(department.equals(Department.글로벌SW융합전공))
            globalSoftware(user);
    }

    public boolean isVerificationUser(String token){
        User user = findByToken(token);
        boolean graduation = user.isCreditGraduation();
        if(graduation == true)
            return true;
        else
            return false;
    }

    @Transactional
    public void globalSoftware(User user){
        List<UserLecture> userLectureList = user.getUserLectureList();
        Integer culture = 0;
        Integer major = 0;

        for (UserLecture userLecture : userLectureList) {
            culture += userLecture.getCultureCredit();
            major += userLecture.getMajorCredit();
        }
        if(major >= 51 && (culture >= 24 && culture <= 42) &&user.getPresentCredit() >= 130)
            user.userGraduation(true);
        user.userGraduation(false);
    }
    @Transactional
    public void deepComputer(User user){
        List<UserLecture> userLectureList = user.getUserLectureList();
        Integer admissionYear = user.getAdmissionYear();
        Integer basicMajor = 0;
        Integer technologyMajor= 0;
        Integer culture = 0;
        for (UserLecture userLecture : userLectureList) {
            List<UserLectureDetail> lectureDetail = userLecture.getLectureDetails();
            for (UserLectureDetail userLectureDetail : lectureDetail) {
                UserLectureType type = userLectureDetail.getUserLectureType();
                if(type.equals(UserLectureType.전공기반))
                    basicMajor+=1;
                else if(type.equals(UserLectureType.공학전공))
                    technologyMajor+=1;
            }
            culture += userLecture.getCultureCredit();
        }
        if(admissionYear < 2021){
            if(basicMajor >= 21 && technologyMajor >= 75 && culture >= 15 && user.getPresentCredit() >= 150)
                user.userGraduation(true);
        }
        else if(admissionYear >= 2021){
            if(basicMajor >= 18 && technologyMajor >= 60 && culture >= 15 && user.getPresentCredit() >= 140)
                user.userGraduation(true);
        }

        user.userGraduation(false);
    }

    @Transactional
    public void userDetailCreditCal(User user, Integer credit, UserLectureType type){
        user.addDetailCredit(credit, type);
    }

    @Transactional
    public void setGraduationInfo(User user, UserGraduationInfo info){
        user.setUserGraduationInfo(info);
    }

    @Transactional
    public void setIsConditionGraduation(User user, boolean yn){
        user.setIsConditionGradation(yn);
    }
}
