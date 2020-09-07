package com.pratilipi.storyapp.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pratilipi.storyapp.config.JwtTokenUtil;
import com.pratilipi.storyapp.models.JwtRequest;
import com.pratilipi.storyapp.models.JwtResponse;
import com.pratilipi.storyapp.models.UserModel;
import com.pratilipi.storyapp.repositories.UserRepo;
import com.pratilipi.storyapp.services.JwtUserDetailsService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserRepo userRepo;

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

		Optional<UserModel> o = userRepo.findByUserNameAndPassword(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		if (!o.isPresent()) {
			logger.info("Invalid Credentials : {}, {}", authenticationRequest.getUsername(),
					authenticationRequest.getPassword());
			return ResponseEntity.of(Optional.empty());
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, o.get().getId(), o.get().getUserName()));
	}

	@CrossOrigin
	@PostMapping(path = { "/register" })
	public void signup(@RequestBody UserModel userModel) {
		userRepo.save(userModel);
	}

}