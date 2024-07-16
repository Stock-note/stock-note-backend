package gunnu.stocknote.common.config;

import gunnu.stocknote.common.jwt.TokenProvider;
import gunnu.stocknote.user.interceptor.LoginCheckInterceptor;
import gunnu.stocknote.user.interceptor.TokenCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
            .order(1)
            .addPathPatterns("/api/v1/users/test");

        registry.addInterceptor(new TokenCheckInterceptor(tokenProvider))
            .order(2)
            .addPathPatterns("/api/v1/users/test");
    }
}
