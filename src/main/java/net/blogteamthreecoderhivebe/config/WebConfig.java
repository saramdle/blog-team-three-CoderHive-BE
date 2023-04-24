package net.blogteamthreecoderhivebe.config;

import net.blogteamthreecoderhivebe.converter.CareerRequestConverter;
import net.blogteamthreecoderhivebe.converter.LevelRequestConverter;
import net.blogteamthreecoderhivebe.converter.PlatformRequestConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://coderhive.vercel.app")
                //.allowedHeaders("*") // 어떤 헤더들을 허용할 것인지
                //.allowedMethods("*") // 어떤 메서드를 허용할 것인지 (GET, POST...)
                //.allowCredentials(true) // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
                ;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CareerRequestConverter());
        registry.addConverter(new LevelRequestConverter());
        registry.addConverter(new PlatformRequestConverter());
    }


}
