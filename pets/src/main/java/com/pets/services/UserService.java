package com.pets.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.pets.db.entities.User;
import com.pets.db.repositorys.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	@Autowired
	private UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());


	public User saveGoogleUserIfNeeded() {
		User user = null;

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
			user = userRepository.findByEmail(userinfo.getEmail());
			if (user == null) {
				logger.info("saving new user: {}", userinfo.toPrettyString());
				user = userRepository.save(new User(userinfo.getGivenName(), userinfo.getFamilyName(),
						userinfo.getEmail(), "", userinfo.getPicture(), userinfo.getLocale()));
			}
		} catch (IOException e) {
			logger.error("failed to save google user: {}", e.toString());
		}

		return user;
	}

	
	
	
}
