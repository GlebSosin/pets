package com.pets.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets.db.entities.User;
import com.pets.services.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userServie;

//	@GetMapping("/test")
//	public String all() {
//		String result = "";
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//
//		OAuth2AuthorizedClient client = clientService
//				.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//
//		String accessToken = client.getAccessToken().getTokenValue();
//		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//		Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
//				.setApplicationName("Oauth2").build();
//		try {
//			Userinfoplus userinfo = oauth2.userinfo().get().execute();
//			result = userinfo.toPrettyString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	@GetMapping("/saveGoogleUser")
	public User saveGoogleUser() {
		User user = userServie.saveGoogleUserIfNeeded();

		return user;
	}

}
