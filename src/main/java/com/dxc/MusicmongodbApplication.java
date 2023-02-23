package com.dxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dxc.filter.JwtFilter;
@SpringBootApplication
public class MusicmongodbApplication {
	
	
	
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter()
	{
		final FilterRegistrationBean<JwtFilter> registrationBean=new FilterRegistrationBean<JwtFilter>();
		JwtFilter filter=new JwtFilter();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/api/music/*");
		return registrationBean;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicmongodbApplication.class, args);
	}

}
