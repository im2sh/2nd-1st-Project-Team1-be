package com.gdsc.canigraduate.repository;

import com.gdsc.canigraduate.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByClassId(String classId);
    Optional<User> findById(Long id);
    User findUserByToken(String Token);
}
