/***
 * @author amir-reza abbasi
 */
package com.javasampleapproach.jqueryboostraptable.controller;


import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.javasampleapproach.jqueryboostraptable.model.User;

import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;

// @CrossOrigin(origins = "http://localhost:3000") this annotation for react.js
@Service
@Controller
public class WebController {
	

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	

	@Autowired
    private WebConfiguration webconfiguration;

	 
	  @GetMapping("/members")
	   public String viewMembers(Model model,String keyword){
	 		//data have devices information
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
	 		
	 		 
	 		if(keyword != null) {
	 			model.addAttribute("members",userRepo.search(keyword));
	 			
	 		}else {
	 		
	 		model.addAttribute("members",userRepo.findAll());
	 		
	 		}
	 		return "membersPage"; 
	     }
	  
	  
	  @GetMapping("/Scanner")
	  public String qrScan() {
		  
		  return "scanner";
	  }
	  

	  @GetMapping("/fa/profile")
	  public String profile(Model model) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("user",user);
		  return "userprofile";
	  }
	  @GetMapping("/fa/adminprofile")
	  public String aprofile(Model model,Integer id) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			User u = userRepo.findById(id).get();
			model.addAttribute("userName", "خوش آمدید   " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("user",u);
			model.addAttribute("cuser",user);
		  return "adminuserprofile";
	  }
	  @GetMapping("/access-denied")
	   public String access(Model model){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
	 		 
	 		 return "access-denied";
	     }
	  
	  @PostMapping("/saveUser")
		public String save(User u) {
			 System.out.println(u.getFinger() =="1");
			 if(u.getFinger().contentEquals("1")) {
				 userService.save(u,0);
			 }else {
				 userService.save(u,1);
			 }
			
			return "redirect:/members";
		}
	  
	  @PostMapping("/saveeUser")
		public String saveue(User u, @RequestParam("file") MultipartFile file) {
			 System.out.println(u.getFinger() =="1");
			  
			 if(u.getFinger().contentEquals("1")) {
				 if(!file.isEmpty()) {
				 userService.saveuemza(u, 0, file);
				 }else {
					 User us = userRepo.findById(u.getId()).get();
					 u.setEmza(us.getEmza());
					 userService.save(u, 0);
				 }
			 }else if(u.getFinger().contentEquals("2")) {
				 if(!file.isEmpty()) {
					 userService.saveuemza(u,1, file);
					 }else {
						 
						 User us = userRepo.findById(u.getId()).get();
						 u.setEmza(us.getEmza());
						 userService.save(u, 1);
					 }
			 }
			 if(u.getFinger().contentEquals("3")) {
				 if(!file.isEmpty()) {
				 userService.saveuemza(u, 2, file);
				 }else {
					 User us = userRepo.findById(u.getId()).get();
					 u.setEmza(us.getEmza());
					 userService.save(u, 2);
				 }
			 }else if(u.getFinger().contentEquals("4")) {
				 if(!file.isEmpty()) {
					 userService.saveuemza(u,3, file);
					 }else {
						 
						 User us = userRepo.findById(u.getId()).get();
						 u.setEmza(us.getEmza());
						 userService.save(u, 3);
					 }
			 }
				 
			 
			return "redirect:/members";
		}
	  
	  
	  @PostMapping("/fa/saveUser")
		public String profile(User u, @RequestParam("file") MultipartFile file) {
		  User user = userRepo.findById(u.getId()).get();
		  System.out.println("User--------->"+user);
		  user.setAddress(u.getAddress());
		  user.setMobile(u.getMobile());
		  user.setEmail(u.getEmail());
		  if(u.getPass() != null) {
			user.setPass(u.getPass());
		  }
		  if(u.getFullname() != null) {
			  user.setFullname(u.getFullname());
		  }
		  if(!file.isEmpty()) {
			  userService.savepro(user, file);
		  }else {
		  System.out.println("u ------->" +u);
		  System.out.println("after ifs --------> " +user);
			 
		  System.out.println("after ifs --------> " +u.getFinger());
		  user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
			  userRepo.save(user);
			  }

				 
			 
			return "redirect:/fa/profile";
		}
		
		@RequestMapping(value={ "/login"}, method = RequestMethod.GET)
		public ModelAndView login(){
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("login");
		    return modelAndView;
		}
		
		@RequestMapping(value="/registration", method = RequestMethod.GET)
		public ModelAndView registration(){
		    ModelAndView modelAndView = new ModelAndView();
		    User user = new User();
		    modelAndView.addObject("user", user);
		    modelAndView.setViewName("registration");
		    return modelAndView;
		}
		@RequestMapping(value = "/registration", method = RequestMethod.POST)
		public ModelAndView createNewUser(@Valid  User user, BindingResult bindingResult) {
		    ModelAndView modelAndView = new ModelAndView();
		    List<User> us = userRepo.findBypersonalId(user.getPersonalId());
		    System.out.println("sdsdsdffdfdf");
		    System.out.println(user);
		    
		    System.out.println("---------------------");
		    System.out.println(us);
		    if (!us.isEmpty()) {
		        bindingResult
		                .rejectValue("personalId", "error.user",
		                        "هم اکنون کاربری با این شماره کارمندی موجود است");
		    }
		    if (bindingResult.hasErrors()) {
		        modelAndView.setViewName("registration");
		    } else {	
		    	user.setFinger("2");
		    	 userService.save(user,1);
		    }
		    return modelAndView;
		}
		@GetMapping("/deleteUser")
		public String deleteUser(Integer id,Model model) {
			try {
				userRepo.deleteById(id);
				
			} catch (Exception e) {
				model.addAttribute("messege", "این کاربر در یک آفیش حضور دارد");
				return "errorPage";
			}
		
		return "redirect:/members";
		}
		@GetMapping("/findOneUser")
		@ResponseBody
		public Optional<User> findOneUser(Integer id) {
			return userRepo.findById(id);
		}
		@GetMapping("/findAllUser")
		@ResponseBody
		public List<User> findAllUser() {
			return userRepo.findAll();
		}
	
		

	
	
	
	}


