package br.com.odaguiri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.odaguiri.servlet.ShortenedUrlServlet;

@SpringBootApplication
public class NeuedaShortenedurlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuedaShortenedurlApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<ShortenedUrlServlet> servletRegistrationBean() {
		ServletRegistrationBean<ShortenedUrlServlet> bean = 
				new ServletRegistrationBean<ShortenedUrlServlet>(new ShortenedUrlServlet(), "/");
		return bean;
	}
}
