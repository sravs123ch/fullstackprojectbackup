package com.kodnest.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.project.entity.Song;
import com.kodnest.project.entity.User;
import com.kodnest.project.model.LoginData;
import com.kodnest.project.service.SongService;
import com.kodnest.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
//public class UserController {
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	SongService songService;
//
//	private Object fetchAllSongs;
//
//	@PostMapping("/register")
//	
//	public String addUser(@ModelAttribute User user) {
//		//String email=user.getEmail();//email from user-form
//		//To Check email exist or not
//		boolean userExists=userService.emailExists(user);
//		
//		if(userExists==false) {
//			userService.saveUser(user);
//			System.out.println("User added successfully");
//		}
//		else {
//		System.out.println("Duplicate user");	
//		}
//		return "login";
//	}
//    @PostMapping("/validate")
//   // @RequestParam("email") String email,@RequestParam("password") String password,
//		public String validate(@RequestBody LoginData logindata,HttpSession session,Model model) {
//    	if(userService.validUser(logindata.getEmail(),logindata.getPassword())==true) {
//    		
//    		session.setAttribute("email", logindata.getEmail());
//    		
//    		String role=userService.getRole(logindata.getEmail());
//    
//			if(role.equals("admin")) {
//    			return "adminhome";
//    		}
//    		else {
//    			User user=userService.getUser(logindata.getEmail());
//    			boolean userstatus=user.isPremium();
//    			
//    			List<Song> feachAllSongs=songService.fetchAllSongs();
//    			model.addAttribute("songs",fetchAllSongs);
//    			
//    			model.addAttribute("ispremium",userstatus);
//    			
//    			return "customerhome";
//    			
//    		}
//    	}
//    	
//    	else {
//    		return "login";
//    	}
//    }
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//    	session.invalidate();
//		return "login";
//    	
//    }
//   
//    
//}




public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;

	@PostMapping("/register") 
	public String addUser(@ModelAttribute User user) {

		//to check a user with a a mail is present or not
		boolean userExists = userService.emailExists(user);

		if(userExists==false) {
			userService.saveUser(user);		
			System.out.println("User added successfully");
		}		else {
			System.out.println("Duplicate user");
		}

		return "login";

	}

	@PostMapping("/validate") 
	public String validate(@RequestBody LoginData logindata, HttpSession session, Model model) {

		if(userService.validUser(logindata.getEmail(), logindata.getPassword())==true) {
			
			session.setAttribute("email", logindata.getEmail());

			String role = userService.getRole(logindata.getEmail());

			if(role.equals("admin")) {
				return"adminhome";
			}else{
				User user = userService.getUser(logindata.getEmail());
				boolean userstatus = user.isPremium();
				List<Song> fetchAllSongs = songService.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				model.addAttribute("ispremium", userstatus);
				
				return "customerhome";
			}

		}
		else {
			return "login";
		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	
}





//@Controller
//public class UserController {
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	SongService songService;
//
//	private Object fetchAllSongs;
//
//	@PostMapping("/register")
//	
//	public String addUser(@ModelAttribute User user) {
//		//String email=user.getEmail();//email from user-form
//		//To Check email exist or not
//		boolean userExists=userService.emailExists(user);
//		
//		if(userExists==false) {
//			userService.saveUser(user);
//			System.out.println("User added successfully");
//		}
//		else {
//		System.out.println("Duplicate user");	
//		}
//		return "login";
//	}
//    @PostMapping("/validate")
//		public String validate(@RequestBody LoginData logindata,@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session,Model model) {
//    	if(userService.validUser(logindata.getEmail(),logindata.getPassword())==true) {
//    		
//    		session.setAttribute("email", email);
//    		
//    		String role=userService.getRole(email);
//    
//			if(role.equals("admin")) {
//    			return "adminhome";
//    		}
//    		else {
//    			User user=userService.getUser(email);
//    			boolean userstatus=user.isPremium();
//    			
//    			List<Song> feachAllSongs=songService.fetchAllSongs();
//    			model.addAttribute("songs",fetchAllSongs);
//    			
//    			model.addAttribute("ispremium",userstatus);
//    			
//    			return "customerhome";
//    			
//    		}
//    	}
//    	
//    	else {
//    		return "login";
//    	}
//    }
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//    	session.invalidate();
//		return "login";
//    	
//    }
//    
//}
//
