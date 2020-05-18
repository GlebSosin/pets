package com.singlesignon.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

@RestController
public class LoginController {
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;

	@GetMapping("/")
	public String all() {
		String result = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

		OAuth2AuthorizedClient client = clientService
				.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

		String accessToken = client.getAccessToken().getTokenValue();
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
				.setApplicationName("Oauth2").build();
		try {
			Userinfoplus userinfo = oauth2.userinfo().get().execute();
			result = userinfo.toPrettyString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
