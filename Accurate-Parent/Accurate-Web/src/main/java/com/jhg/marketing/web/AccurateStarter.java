package com.jhg.marketing.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.jhg.marketing.web", "com.jhg.marketing.dao"})
@MapperScan(basePackages = "com.jhg.marketing.dao.mapper")
public class AccurateStarter implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(AccurateStarter.class, args);
    }

//	/**
//	 * 此方法把该拦截器实例化成一个bean,否则在拦截器里无法注入其它bean
//	 * @return
//	 */
//	@Bean
//	SessionInterceptor sessionInterceptor() {
//		return new SessionInterceptor();
//	}

//	@Value("${shiro.loginUrl}")
//	private String loginUrl;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(sessionInterceptor())
//				.addPathPatterns("/**")
//				.excludePathPatterns(
//						"/assets/**",loginUrl,"/file/**","/wx",
//						"/authorize","/userInfo","/register",
//						"/ajax/login","/WxPay/**","/bindUserInfo",
//						"/sendCaptcha","/MP_verify_FvDIipgU4kXqg5We.txt");
//	}

    /**
     * 设置为共享模式
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//    }

}
