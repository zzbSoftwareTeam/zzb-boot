package com.zzb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
			//配置安全策略
			//.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
	        //    @Override
	        //    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
	            	//自定义 接收用户请求的地址，返回访问该地址需要的所有权限
	                //o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
	            	//自定义判断用户是否可以访问的权限规则
	        //        //o.setAccessDecisionManager(myAccessDecisionManager);
	        //        return o;
	        //    }
	        //})
			//在这里配置哪些页面不需要认证一种方式
			.antMatchers("/login","/common/**","/third/**","/websocket/**","/im/**")
			//必须已经登录了应用
			//.authenticated()
			//没有任何的安全限制
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.successHandler(loginSuccessHandler())
			.loginPage("/login")
			.loginProcessingUrl("/tologin").usernameParameter("userName").passwordParameter("userPass")
			//.defaultSuccessUrl("/").failureUrl("/login?error=true")
			//自定义ajax登录返回
			.defaultSuccessUrl("/login/return").failureUrl("/login/return")
			.permitAll()
			.and()
			.logout().deleteCookies("remove").invalidateHttpSession(false)
			.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		//X-Frame-Options frame劫持问题，同域放开
		http.headers().frameOptions().sameOrigin();
		//csrf攻击防护关闭
		http.csrf().disable();
		//权限不够，403自定义
		//http.accessDeniedHandler(myAccessDeniedHandler);

	}
	
	/**定义认证用户信息获取来源，密码校验规则等*/  
    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        //inMemoryAuthentication 从内存中获取  
        //auth.inMemoryAuthentication().withUser("chengli").password("123456").roles("USER");  
          
        //注入userDetailsService，需要实现userDetailsService接口  
        auth.userDetailsService(userService);//.passwordEncoder(passwordEncoder());
    }
    	
    //在这里配置哪些页面不需要认证
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/common/**","/third/**","/websocket/**","/im/**");
    }*/
}
