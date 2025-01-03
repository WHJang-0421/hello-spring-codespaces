package com.example.hello_codespaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.hello_codespaces.domain.User;
import com.example.hello_codespaces.service.SpringDataJpaUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(requests -> requests
                                .requestMatchers("/bower_components/**", "/*.js",
                                                "/*.jsx", "/main.css")
                                .permitAll()
                                .anyRequest().authenticated())
                                .formLogin(formLogin -> formLogin
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())
                                .csrf(csrf -> csrf.disable())
                                .httpBasic(Customizer.withDefaults())
                                .logout(logout -> logout.logoutSuccessUrl("/"));
                return http.build();
        }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

}
