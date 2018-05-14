package br.com.odaguiri.dto;

public class ShortenedUrlRequest {

	private String hashUrl;

	public ShortenedUrlRequest() {}
	
	public ShortenedUrlRequest(String hashUrl) {
		super();
		this.hashUrl = hashUrl;
	}

	public String getHashUrl() {
		return hashUrl;
	}

	public void setHashUrl(String hashUrl) {
		this.hashUrl = hashUrl;
	}
	
}
