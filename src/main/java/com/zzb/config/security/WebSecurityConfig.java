package com.zzb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zzb.module.system.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService userService;    
        
	@Bean  
    public LoginSuccessHandler loginSuccessHandler(){  
        return new LoginSuccessHandler();  
    }
    
   @Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder();  
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login","/css/**","/img/**","/js/**","/third/**").permitAll()
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login").usernameParameter("userName").passwordParameter("userPass")
			.defaultSuccessUrl("/")//.failureUrl("/login?error=true")
			.successHandler(loginSuccessHandler()).permitAll().and()
			.logout().deleteCookies("remove").invalidateHttpSession(false)
			.logoutUrl("/logout").logoutSuccessUrl("/login")
			.permitAll();
		//X-Frame-Options frame劫持问题，同域放开
		http.headers().frameOptions().sameOrigin();
		//csrf攻击防护关闭
		http.csrf().disable();
	}
	
	/**定义认证用户信息获取来源，密码校验规则等*/  
    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        //inMemoryAuthentication 从内存中获取  
        //auth.inMemoryAuthentication().withUser("chengli").password("123456").roles("USER");  
          
        //注入userDetailsService，需要实现userDetailsService接口  
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    	
}
