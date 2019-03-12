package com.zzb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zzb.module.system.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService userService;    
    
	/**
	 * 
	 * @Description: TODO 登录成功回调
	 * @return LoginSuccessHandler  
	 * @author zengzhibin
	 * @date 2019年2月12日
	 */
	@Bean  
    public LoginSuccessHandler loginSuccessHandler(){  
        return new LoginSuccessHandler();  
    }
	
	/**
	 * 
	 * @Description: TODO 退出登录回调
	 * @return LoginSuccessHandler  
	 * @author zengzhibin
	 * @date 2019年1月30日
	 */
	@Bean  
    public LogoutSuccessLatHandler logoutSuccessLatHandler(){  
        return new LogoutSuccessLatHandler();  
    }
    /**
     * @Description: TODO 密码加密
     * @return BCryptPasswordEncoder  
     * @author zengzhibin
     * @date 2019年1月30日
     */
    @Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder();  
    }
    
    /**
     * 主配置方法
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//在这里配置哪些页面不需要认证一种方式
		http.authorizeRequests()
			.antMatchers("/login","/Hui/**","/third/**","/websocket/**","/im/**","/favicon.ico"
					,"/api/**","/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs")
			//必须已经登录了应用
			//.authenticated()
			//应用于所有请求
			.permitAll()
			//admin权限可以访问的请求
	        .antMatchers("/security/admin").hasRole("admin");
		//其他所有请求都需要验证
		http.authorizeRequests()
			.anyRequest().authenticated();
		//配置登录
		http.formLogin()
			.loginPage("/login")//登录页面服务地址
			.loginProcessingUrl("/tologin")//登录请求地址
			.usernameParameter("userName").passwordParameter("userPass")//登录请求参数
			.defaultSuccessUrl("/login/return")//自定义登录成功返回地址
			.failureUrl("/login/return")//自定义登录失败返回地址
			.successHandler(loginSuccessHandler())//一定要配置在defaultSuccessUrl后面才有用
			//.failureHandler(ctwAuthenticationFailureHandler)
			.permitAll()//应用于所有请求
			.and()
			.rememberMe()// 只需要以下配置即可启用记住密码
			//设置cookie有效期
            .tokenValiditySeconds(60 * 60 * 24 * 7)
            //设置cookie的私钥
            .key("zzb")
			.and()
			//配置退出登录
			.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
			.logoutUrl("/logout").logoutSuccessUrl("/login")
			.logoutSuccessHandler(logoutSuccessLatHandler())//退出成功回调		
			.permitAll();
		//限制账号只能登录一次
		http.sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login")
			.and();
		//X-Frame-Options frame劫持问题，同域放开
		http.headers().frameOptions().sameOrigin();
		//csrf攻击防护关闭
		http.csrf().disable();
		//权限不够，403自定义
		//http.accessDeniedHandler(myAccessDeniedHandler);
		
		//配置安全策略
		//http.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
        //    @Override
        //    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
            	//自定义 接收用户请求的地址，返回访问该地址需要的所有权限
                //o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
            	//自定义判断用户是否可以访问的权限规则
        //        //o.setAccessDecisionManager(myAccessDecisionManager);
        //        return o;
        //    }
        //});
		// 添加验证码验证
		//http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).exceptionHandling();
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
