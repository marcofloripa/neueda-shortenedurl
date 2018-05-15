package br.com.odaguiri.servlet;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.odaguiri.dto.ShortenedUrlResponse;
import br.com.odaguiri.properties.GlobalProperties;

@RestController
@RequestMapping("/")
public class ShortenedUrlResource {

	@Autowired
	GlobalProperties globalProperties;
	
	@GetMapping("{hashUrl}")
	public ResponseEntity<?> redirect(@PathVariable String hashUrl) throws URISyntaxException {
		String urlApi = globalProperties.getUrl();
		String apiResource = globalProperties.getResource();
		final StringBuilder url = new StringBuilder(urlApi + apiResource)
			.append(hashUrl);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<ShortenedUrlResponse> response = restTemplate.getForEntity(url.toString(), ShortenedUrlResponse.class);
	    	if (HttpStatus.OK.equals(response.getStatusCode())) {
	    		URI uri = new URI(response.getBody().getLongUrl());
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(uri);
				return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		    }
	    } catch (RestClientException ex) {
	    	throw new RuntimeException(ex);
	    }
	    return ResponseEntity.notFound().build();
	}
	
}
