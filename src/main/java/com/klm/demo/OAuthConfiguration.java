package com.klm.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuthConfiguration {

	@Bean(name = "oAuthGetToken")
	@ConfigurationProperties("mockservice.oauth")
	public ClientCredentialsResourceDetails oAuthGetToken() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public OAuth2ClientContext clientGetContext() {
		return new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
	}

	@Bean(name = "oAuthRestTemplate")
	public OAuth2RestTemplate timelineGetOAuthRestTemplate(
			@Qualifier("oAuthGetToken") ClientCredentialsResourceDetails resource,
			@Qualifier("clientGetContext") OAuth2ClientContext clientContext) {

		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource, clientContext);
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setOutputStreaming(false);
		ClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory);
		oAuth2RestTemplate.setRequestFactory(requestFactory);
		System.out.println("Go::::"+oAuth2RestTemplate.getAccessToken().toString());
		return oAuth2RestTemplate;
	}
	
	

}
