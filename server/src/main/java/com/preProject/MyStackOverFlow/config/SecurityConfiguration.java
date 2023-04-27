package com.preProject.MyStackOverFlow.config;

import com.preProject.MyStackOverFlow.auth.filter.JwtAuthenticationFilter;
import com.preProject.MyStackOverFlow.auth.filter.JwtVerificationFilter;
import com.preProject.MyStackOverFlow.auth.handler.MemberAuthenticationSuccessHandler;
import com.preProject.MyStackOverFlow.auth.handler.OAuth2MemberSuccessHandler;
import com.preProject.MyStackOverFlow.auth.jwt.JwtTokenizer;
import com.preProject.MyStackOverFlow.auth.utils.CustomAuthorityUtils;
import com.preProject.MyStackOverFlow.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    public SecurityConfiguration(JwtTokenizer jwtTokenizer,
                                 CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // (1)
                .and()
                .csrf().disable()        // (2)
                .cors(withDefaults())    // (3)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .httpBasic().disable()
                .exceptionHandling()  // 추가
                .and()
                .apply(new CustomFilterConfigurer())  // 추가
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/members").permitAll()         // 회원가입-아무나
                        .antMatchers(HttpMethod.PUT, "/members/**").permitAll() // 회원수정-회원만
                        .antMatchers(HttpMethod.GET, "/members/**").permitAll() // 마이페이지-회원만
                        .antMatchers(HttpMethod.DELETE, "/members/**").hasRole("USER")  // 회원삭제-회원만

                        .antMatchers(HttpMethod.POST, "/boards").hasRole("USER")         // 게시글등록-회원만
                        .antMatchers(HttpMethod.PUT, "/boards/**").hasRole("USER")  // 게시글수정-회원만
                        .antMatchers(HttpMethod.GET, "/boards").permitAll()    // 게시글조회-아무나
                        .antMatchers(HttpMethod.GET, "/boards/**").permitAll() // 게시글상세보기-아무나
                        .antMatchers(HttpMethod.DELETE, "/boards/**").hasRole("USER")  //게시글 삭제-회원만
                        .antMatchers(HttpMethod.PATCH, "/boards/vote").hasRole("USER")  //게시글 투표-회원만

                        .antMatchers(HttpMethod.POST, "/answer").hasRole("USER")       //댓글작성-회원만
                        .antMatchers(HttpMethod.PUT, "/answer/**").hasRole("USER")  //댓글수정-회원만
                        .antMatchers(HttpMethod.DELETE, "/answer/**").hasRole("USER")  //댓글삭제-회원만
                        .antMatchers(HttpMethod.PATCH, "/answer/vote").hasRole("USER")  //답변 투표-회원만
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils))  // (1)
                );
        return http.build();
    }

    // (7)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // (8)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));   // (8-1)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));  // (8-2)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();   // (8-3)
        source.registerCorsConfiguration("/**", configuration);      // (8-4)     주의 사항: 콘텐츠 표시 오류로 인해 '/**'를 '\/**'로 표기했으니 실제 코드 구현 시에는 '\(역슬래시)'를 빼 주세요.
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {  // (2-1)
        @Override
        public void configure(HttpSecurity builder) throws Exception {  // (2-2)
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);  // (2-3)

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);  // (2-4)

            jwtAuthenticationFilter.setFilterProcessesUrl("/process_login");          // (2-5)
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);


            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class)
                    .addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);

//        @Override
//        public void configure(HttpSecurity builder) throws Exception {
//            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
//
//            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
//        }


//            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

//            builder.addFilter(jwtAuthenticationFilter) // (2-6)
//                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
//            builder.addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}

