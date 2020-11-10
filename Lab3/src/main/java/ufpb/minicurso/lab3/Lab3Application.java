package ufpb.minicurso.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ufpb.minicurso.lab3.filter.TokenFilter;

@EnableJpaAuditing
@SpringBootApplication
public class Lab3Application {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/api/disciplinas/likes/{id}",
				"/api/disciplinas/nota/{id}",
				"/api/disciplinas/comentarios/{id}");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}

}
