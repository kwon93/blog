package me.kwon.springbootdeveloper.repository;

import me.kwon.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자를 식별
     * @param email //user의 이메일
     * @return //user를 반환
     */
    Optional<User> findByEmail(String email);

}
