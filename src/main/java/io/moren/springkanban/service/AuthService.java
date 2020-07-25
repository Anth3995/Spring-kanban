package io.moren.springkanban.service;

import io.moren.springkanban.dto.LoginDto;
import io.moren.springkanban.dto.RegistrationDto;
import io.moren.springkanban.dto.TokenDto;
import io.moren.springkanban.model.Role;
import io.moren.springkanban.model.User;
import io.moren.springkanban.repository.RoleRepository;
import io.moren.springkanban.repository.UserRepository;
import io.moren.springkanban.security.JwtProperties;
import io.moren.springkanban.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public void signup(RegistrationDto request) {
        userService.save(request);
    }

    public TokenDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setUsername(loginDto.getUsername());
        tokenDto.setType(JwtProperties.PREFIX);
        tokenDto.setAuthorisation(
                jwtUtil.generateToken(authentication)
        );

        return tokenDto;
    }
}
