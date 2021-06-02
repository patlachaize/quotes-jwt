package ch.es.pl.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class QuotesApplication {
	@Autowired
	private AuthFilter authFilter;

	@Bean
	public FilterRegistrationBean< AuthFilter > filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/quotes/*");
		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
}
