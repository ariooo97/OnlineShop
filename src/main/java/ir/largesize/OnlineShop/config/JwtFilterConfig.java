package ir.largesize.OnlineShop.config;

import ir.largesize.OnlineShop.config.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    public FilterRegistrationBean jwtFilterRegister(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("jwtFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;

    }
}
