package gunnu.stocknote.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import gunnu.stocknote.common.dto.ResponseDTO;
import gunnu.stocknote.common.jwt.TokenProvider;
import gunnu.stocknote.user.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        if (auth != null) {
            String token = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);

            if (token == null) {
                String jsonResponse = new ObjectMapper().writeValueAsString(
                    ResponseDTO.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message("로그인이 필요합니다")
                        .build()
                );
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(jsonResponse);

                return false;
            }

            if (!tokenProvider.validateToken(token)) {
                String jsonResponse = new ObjectMapper().writeValueAsString(
                    ResponseDTO.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("올바르지 않은 토큰입니다")
                        .build()
                );

                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getWriter().write(jsonResponse);

                return false;
            }

            if (auth.role().compareTo(UserRole.ADMIN) == 0) {
                //나중에 관리자가 생기면 필요한 로직
            }

            request.setAttribute("userId", tokenProvider.getUserIdFromToken(token));
        }
        return true;
    }
}
