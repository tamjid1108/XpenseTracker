package com.example.tj.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tj.model.SecurityUser;
import com.example.tj.model.User;
import com.example.tj.payload.SignUpDTO;
import com.example.tj.repository.UserRepository;


@Controller
@RequestMapping("/")
public class AuthController {
	
	private UserRepository userRepository;
	
	
	public AuthController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	@GetMapping("/")
	public String home(Map<String, Object> model, @AuthenticationPrincipal SecurityUser user) {
		model.put("message", "Hello " + user.getUsername());
		return "home";
	}
	
	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}
	
	@GetMapping("/login")
	public String login() {
	    return "login";
	}
	
	@GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            Exception ex = (Exception) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
	
	@PostMapping(path="/signup", consumes = "application/x-www-form-urlencoded")
	public String registerUser(SignUpDTO signUpDTO, Model model){

		String message = null;
		
		if(userRepository.existsByUsername(signUpDTO.getUsername())){
			message = "Username is already taken";
			model.addAttribute("message", message);
			return "signup";
        }

   
        if(userRepository.existsByEmail(signUpDTO.getEmail())){
        	message = "Email is already taken";
			model.addAttribute("message", message);
			return "signup";
        }

        // create user object
        User user = new User();
  
        user.setName(signUpDTO.getName());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles("ROLE_USER");

        userRepository.save(user);

        return "redirect:/";

    }
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
