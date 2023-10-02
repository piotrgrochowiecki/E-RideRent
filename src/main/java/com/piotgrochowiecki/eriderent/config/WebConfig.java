package com.piotgrochowiecki.eriderent.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Wybór języka zostanie zapisany przy użyciu ciasteczka (przechowywany po stronie klienta)
    // HINT: Nasz bean musi przysłonić bean typu LocalContextResolver o nazwie "localeResolver",
    // inaczej Spring Boot będzie dalej używał swojego.
    @Bean(name = "localeResolver")
    public LocaleContextResolver localeContextResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pl"));
        return cookieLocaleResolver;
    }

    // Tworzymy beana, który będzie zmieniał wybór języka na podstawie parametru "lang"
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // Rejestrujemy naszego bean zmieniającego język
    // HINT: Interceptor to mechanizm, który dziala jak filtr, ale dla kontrolerów, a nie dla serwletów.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/403").setViewName("/403");
    }

}
