package br.com.odaguiri.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.odaguiri.dto.ShortenedUrlResponse;

public class ShortenedUrlServlet extends HttpServlet {

	private static final long serialVersionUID = 4112241261762109359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		//String requestURI = req.getRequestURI();
		String servletPath = req.getServletPath().replaceFirst("/", "");
		final StringBuilder uri = new StringBuilder("http://localhost:8080/api/shortened-urls/").append(servletPath);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<ShortenedUrlResponse> response = restTemplate.getForEntity(uri.toString(), ShortenedUrlResponse.class);
	    	if (HttpStatus.OK.equals(response.getStatusCode())) {
		    	resp.sendRedirect("http://www.google.com");
		    }
	    } catch (RestClientException ex) {
	    	throw new ServletException(ex);
	    }
	}

}
