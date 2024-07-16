package gunnu.stocknote.user.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import gunnu.stocknote.common.dto.ResponseDTO;
import gunnu.stocknote.common.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;


@RequiredArgsConstructor
public class TokenCheckInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String token = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);

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

        return true;
    }
}
