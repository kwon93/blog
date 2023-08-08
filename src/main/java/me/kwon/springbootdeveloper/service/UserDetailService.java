package me.kwon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kwon.springbootdeveloper.domain.User;
import me.kwon.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
                                        //사용자 정보를 가져오는 UserDetailsService 구현
    private final UserRepository userRepository;


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
