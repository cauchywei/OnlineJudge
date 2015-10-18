package org.sssta.oj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.sssta.oj.models.User;
import org.sssta.oj.repositories.UserRepository;
import org.sssta.oj.response.IllegalArgumentResponse;
import org.sssta.oj.response.MissingArgumentResponse;
import org.sssta.oj.response.Response;
import org.sssta.oj.response.ServerErrorResponse;
import org.sssta.oj.response.user.UserExistResponse;
import org.sssta.oj.response.user.UserInfoResponse;
import org.sssta.oj.response.user.UserNotExistResponse;
import org.sssta.oj.security.UserAuthentication;
import org.sssta.oj.security.UserRole;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	Pattern usernamePattern = Pattern.compile("^[_a-zA-Z][_a-zA-z0-9]{3,15}$");
	Pattern passwordPattern = Pattern.compile("^.{4,16}$");
	Pattern genderPattern = Pattern.compile("^男|女|保密$");


	boolean exist(String username){
		return userRepository.findIdByUsername(username) != null
	}

	boolean exist(Long id){
		return userRepository.exists(id);
	}

	static String checkMissingArgument(BindingResult bindingResult,String... args){
		if (bindingResult.hasErrors()) {
			for (String param : args){
				if (bindingResult.hasFieldErrors(param)) {
					return param;
				}
			}

		}
		return null;
	}

	@RequestMapping("/{username}/check")
	Response checkUsername(@PathVariable("username") String username) {
		return exist(username)?new UserExistResponse(username):new UserNotExistResponse(username)
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	Response register(@Valid User user, BindingResult bindingResult) {


		String missingArg = checkMissingArgument(bindingResult,"username","password","nickname");

		if (missingArg != null){
			return new MissingArgumentResponse(missingArg)
		}

		String username = user.getUsername();

		if (exist(username)){
			return new UserExistResponse(username);
		}

		Matcher matcher = usernamePattern.matcher(username);
		if (!matcher.matches()){
			return new IllegalArgumentResponse("username","Username must with a letter, length between 4 and 16, only contains letters, number and underscore.");
		}

		matcher = passwordPattern.matcher(user.getPassword());
		if (!matcher.matches()){
			return new IllegalArgumentResponse("password","The length of password must between 4 and 16");
		}

		if (user.getNickname().length() < 2 || user.getNickname().length() > 16) {
			return new IllegalArgumentResponse("nickname","The length of nickname must between 2 and 16");
		}

		matcher = genderPattern.matcher(user.getPassword());

		if (!matcher.matches()){
			return new IllegalArgumentResponse("gender","Gender must be one of the following values : 男, 女, 保密");
		}


		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		user.setRegisterTime(timeInMillis);
		user.setLatestActiveTime(timeInMillis);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.token = tokenHandler.createTokenForUser(user);
		user.grantRole(UserRole.USER)

		try {
			userRepository.save(user);
		}catch (Exception e){
			return new ServerErrorResponse()
		}

		user.setId(userRepository.findIdByUsername(user.getUsername()));

		return new UserInfoResponse(user)
	}


	@RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
	public User getCurrent() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication) {
			return ((UserAuthentication) authentication).getDetails();
		}
		return new User(authentication.getName()); //anonymous user support
	}

	@RequestMapping(value = "/api/users/current", method = RequestMethod.PATCH)
	public ResponseEntity<String> changePassword(@RequestBody final User user) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final User currentUser = userRepository.findByUsername(authentication.getName());

		if (user.getNewPassword() == null || user.getNewPassword().length() < 4) {
			return new ResponseEntity<String>("new password to short", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (!pwEncoder.matches(user.getPassword(), currentUser.getPassword())) {
			return new ResponseEntity<String>("old password mismatch", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		currentUser.setPassword(pwEncoder.encode(user.getNewPassword()));
		userRepository.saveAndFlush(currentUser);
		return new ResponseEntity<String>("password changed", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/api/users/{user}/grant/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> grantRole(@PathVariable User user, @PathVariable UserRole role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.grantRole(role);
		userRepository.saveAndFlush(user);
		return new ResponseEntity<String>("role granted", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/api/users/{user}/revoke/role/{role}", method = RequestMethod.POST)
	public ResponseEntity<String> revokeRole(@PathVariable User user, @PathVariable UserRole role) {
		if (user == null) {
			return new ResponseEntity<String>("invalid user id", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		user.revokeRole(role);
		userRepository.saveAndFlush(user);
		return new ResponseEntity<String>("role revoked", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/api/users", method = RequestMethod.GET)
	public List<User> list() {
		return userRepository.findAll();
	}
}
