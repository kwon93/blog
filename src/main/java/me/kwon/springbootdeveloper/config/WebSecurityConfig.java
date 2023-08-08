package me.kwon.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;


    @Bean //스프링 시큐리티 기능 비활성화
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
        //정적 리소스에는 스프링 시큐리티 사용을 비활성화 한다.
    }

    //특정 http 요청에대해 웹 기반 보안을 구성, 인증 인가 및 로그인 로그아웃 관련 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http.authorizeHttpRequests()
                .requestMatchers("/login", "/signup","/user").permitAll() //특정요청과 일치하는 url에 대한 엑세를 설정. permitAll:누구나 접근 가능
                .anyRequest().authenticated() //위에서 설정한 url이외의 요청에대해 설정 , authenticated: 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근할 수 있다.
                .and()
                .formLogin()
                //form기반 로그인 설정
                .loginPage("/login") //로그인 페이지 경로 설정
                .defaultSuccessUrl("/articles") //로그인이 완료되었을 때 이동할 경로 설정
                .and()
                .logout()
                //로그아웃 설정
                .logoutSuccessUrl("/login") //로그아웃이 완료되었ㅇ르때의 이동할 경로 설정
                .invalidateHttpSession(true) //로그아웃 이후에 세션을 전체 삭제할지 여부를 설정
                .and()
                .csrf().disable() //csrf 공격 방지 연습용 사이트이기에 비활성화 실무에서는 활성화하자.
                .build();
    }

    //인증관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService)
            throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                //사용자 서비스를 설정
                .passwordEncoder(bCryptPasswordEncoder) //비밀번호를 암호화하기위한 인코더 설정
                .and()
                .build();

    }

    //패스워드 인코더로 사용할 빈
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
