package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.UserGraduationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGraduationInfoRepository extends JpaRepository<UserGraduationInfo, Long> {
}
