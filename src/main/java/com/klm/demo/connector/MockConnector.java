package com.klm.demo.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.klm.demo.artificats.Fare;
import com.klm.demo.artificats.Location;

@Component
public class MockConnector {

	@Value("location.url")
	private String locationUrl;

	@Value("fare.url")
	private String fareUrl;
	@Autowired
	@Qualifier("oAuthRestTemplate")
	private OAuth2RestTemplate oAuth2RestTemplate;

	public Location getLocations(String code) {
		String url = locationUrl + code;
		ResponseEntity<Location> rateResponse = (ResponseEntity<Location>) oAuth2RestTemplate.exchange(url,
				HttpMethod.GET, null, Location.class);
		return rateResponse.getBody();
	}

	public Fare getFares(String origin, String destination) {
		String url = fareUrl + "?origin=" + origin + "&destination=" + destination;
		ResponseEntity<Fare> rateResponse = oAuth2RestTemplate.exchange(url, HttpMethod.GET, null, Fare.class);
		return rateResponse.getBody();
	}
}
