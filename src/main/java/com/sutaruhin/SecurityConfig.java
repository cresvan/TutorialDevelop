package com.sutaruhin;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*@Bean アノテーションはDIコンテナーの登録対象にする記述です。これにより securityFilterChain メソッドがSpring Securityから呼ばれ、内部に記述した設定が行われます。*/
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.formLogin(login -> login
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.defaultSuccessUrl("/user/list")
			.failureUrl("/login?error")
			.permitAll()
		).logout(logout -> logout
			.logoutSuccessUrl("/login")		//Spring Securityでは /logout にアクセスするとログアウト処理が行われるようになっています。
		).authorizeHttpRequests(auth -> auth
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.permitAll()
			.anyRequest().authenticated()
		);		//URLごとのアクセス制御を行なう「認可」の設定です。cssなどのstatic配下のリソースはログインしなくてもアクセス可能にし、その他はログイン必要に設定しています。

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	//passwordEncoder メソッドは、Spring Securityがハッシュ化されたパスワードを比較するために使用します。

}
