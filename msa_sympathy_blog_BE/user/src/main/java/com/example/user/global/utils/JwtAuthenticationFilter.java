package com.example.user.global.utils;

import com.example.user.global.domain.entity.UserBlog;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7); // "jwt " 다음에 있는 토큰 추출
            try {
                // JWT 토큰을 검증하고 유효성을 확인하는 로직을 여기에 추가하세요.
                UserBlog user = jwtUtil.getEmailFromTokenAndValidate(token);
                if (user != null) {
                    // 토큰에서 추출한 이메일을 사용하여 사용자 정보를 가져오는 코드 (예: 데이터베이스 조회)
//                    UserDetails user = authService.loadUserByUsername(email);
                    // 사용자 정보를 기반으로 인증 객체 생성
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    // SecurityContext에 인증 정보 설정
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // 토큰 검증에 실패하거나 다른 예외가 발생한 경우, 로그를 남기고 다음 필터로 요청을 전달합니다.
                logger.error("Failed to process authentication token", e);
            }
        }
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//        String bearerToken = request.getHeader("Authorization");
//        if(bearerToken != null && bearerToken.startsWith("jwt ")) {
//            String token = bearerToken.substring(4);
////            Token 까서 (jwtUtil)
//            String email = jwtUtil.getByEmailFromTokenAndValidate(token);
////            userDetails 생성(authService)
//            UserDetails user = authService.loadUserByUsername(email);
//            UsernamePasswordAuthenticationToken authentication
//                    = new UsernamePasswordAuthenticationToken(user, null,
//                    user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(request,response);
//
//    }
}
