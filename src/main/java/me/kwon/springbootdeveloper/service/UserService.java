package me.kwon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kwon.springbootdeveloper.domain.User;
import me.kwon.springbootdeveloper.dto.AddUserRequest;
import me.kwon.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        Long userId = userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .build()).getId();
        return userId;
    }
}
