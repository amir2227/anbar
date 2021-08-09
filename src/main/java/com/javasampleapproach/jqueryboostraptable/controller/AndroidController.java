package com.javasampleapproach.jqueryboostraptable.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.javasampleapproach.jqueryboostraptable.model.Location;
import com.javasampleapproach.jqueryboostraptable.model.Tajhizat;
import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.model.darkhast;
import com.javasampleapproach.jqueryboostraptable.repository.LocationRepository;
import com.javasampleapproach.jqueryboostraptable.repository.TajhizatRepository;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;
import com.javasampleapproach.jqueryboostraptable.repository.darkhastRepository;

@RestController
public class AndroidController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private darkhastRepository reqRepo;
	
	@Autowired
	private TajhizatRepository tRepo;
	
	@Autowired
	private LocationRepository locRepo;
	
	@PostMapping("/app/login")
	public String applogin(User user) {
		System.out.println("==========>pid::"+user.getPersonalId());
		System.out.println("user pass-------->"+user.getPass()+"---hash====>"+user.getToken());
		List<User> uList = userRepo.findBypersonalId(user.getPersonalId());
		if(!uList.isEmpty()) {
		User u = uList.get(0);
		
		if(u.getToken().contentEquals(user.getToken())) {
			u.setActive(1);
			userRepo.save(u);
			return u.getPersonalId();	
		}else {
			System.out.println("pass is incorrect");
			return "FAILURE";
		}
		}else {
			System.out.println("pid is incorrect");
			return "FAILURE";
		}
	}
	
	@PostMapping("/app/register")
	public String appregister(User user) {
		System.out.println(user);
		System.out.println("finger" +user.getFinger());
		List<User> us = userRepo.findAll();
		for (User u : us) {
			if(user.getPersonalId().contentEquals(u.getPersonalId())) {
				System.out.println("user already exists");
				return "USER_ALREADY_EXISTS";
			}
		}
		System.out.println("after loop");
		if(user.getFinger().contentEquals("1")) {
			System.out.println("in if");
		userService.save(user, 0);
		}
		else { 
			System.out.println("in else befor save");
			userService.save(user, 1);
			System.out.println("in else after save");
		}
		return "SUCCESS";
	}
	@PostMapping("/app/logout")
	public String appLogout(User user) {
		User u = userRepo.findBypersonalId(user.getPersonalId()).get(0);
		if(u != null) {
		if(u.getToken().contentEquals(user.getToken())) {
			u.setActive(0);
			userRepo.save(u);
			return "SUCCESS";	
		}else {
			return "FAILURE";
		}
		}else {
			return "FAILURE";
		}	
	}
	
	@GetMapping("/app/getUserReq/{pid}/{token}")
	public List<darkhast> findallreq(@PathVariable String pid,@PathVariable String token) throws Exception{
		List<User> uList = userRepo.findBypersonalId(pid);
			if(uList.isEmpty()) {
				throw new UserNotFoundException("User Not Found!");
			}else {
				User user = uList.get(0);
				if(user.getToken().contentEquals(token)) {
					
					return reqRepo.findByDarkhastdahandeid(pid);
				}else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token!");
				}
			}
	}
	@GetMapping("/app/getOneUser/{pid}/{token}")
	public List<User> findoneuser(@PathVariable String pid,@PathVariable String token) throws Exception{
		List<User> uList = userRepo.findBypersonalId(pid);
			if(uList.isEmpty()) {
				throw new UserNotFoundException("User Not Found!");
			}else {
				User user = uList.get(0);
				if(user.getToken().contentEquals(token)) {
					
					return userRepo.findBypersonalId(pid);
				}else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token!");
				}
			}
	}
	@GetMapping("/app/getAllTAjhiz/{token}")
	public List<Tajhizat> AllTAjhiz(@PathVariable String token){
		List<User> uList = userRepo.findByToken(token);
		if(uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token");
		}else {
			return tRepo.findAll();
		}
	}
	@GetMapping("/app/mainReq/{token}")
	public List<darkhast> darkh(@PathVariable String token){
		List<User> uList = userRepo.findByToken(token);
		if(uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token");
		}else {
			return reqRepo.findByUsers(uList);
		}
	}
	@GetMapping("/app/findOneReq/{rid}/{token}")
	public Optional<darkhast> findonedarkh(@PathVariable Long rid,@PathVariable String token){
		List<User> uList = userRepo.findByToken(token);
		if(uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token");
		}else {
			return reqRepo.findById(rid);
		}
	}
	@GetMapping("/app/getTajhizByReqid/{rid}/{token}")
	public List<Tajhizat> gett(@PathVariable Long rid,@PathVariable String token){
		
		List<User> uList = userRepo.findByToken(token);
		if(uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid Token");
		}else { 
			System.out.println(reqRepo.findById(rid).get().getTajhizats());
			return reqRepo.findById(rid).get().getTajhizats();
		}
	}
	@GetMapping("/app/getLocations/{token}")
	public List<Location> getAllloc(@PathVariable String token){
		
		return locRepo.findAll();
	}
	@GetMapping("/app/findOneUser/{pid}/{token}")
	public List<User> findoneUser(@PathVariable String pid,@PathVariable String token){
		List<User> uList = userRepo.findBypersonalId(pid);
		if(uList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid PersonalId");
		}else {
			if(uList.get(0).getToken().contentEquals(token)) {
				return uList;
			} else {
				throw new UserNotFoundException("Invalid User Token!");
			}
		}
	}
	@PostMapping("/app/saveReq/{pid}/{token}")
	public String sareq(@PathVariable String pid,@PathVariable String token,darkhast req) {
		List<User> userList = userRepo.findBypersonalId(pid);
		System.out.println("req--------->"+req.getLocation().toString());
		System.out.println("user---------->"+userList);
		if(userList.isEmpty()) {
			return "Invalid Personal Id";
		}else {
			User user = userList.get(0);
			System.out.println("token ---->" +token);
			System.out.println("is valid?__________.>"+user.getToken().contentEquals(token));
			if(user.getToken().contentEquals(token)) {
				System.out.println("valid Token");
				if(req.getUsers() == null) {
					System.out.println("set User------>befor");
					 req.setUsers(userRepo.findBypersonalId(user.getPersonalId()));
					 System.out.println("set User------>after");
				}else {
					 System.out.println("foorm add user else ");
				}
				System.out.println("befor save");
				req.setMonghazi(0);  // monghazi = 0 means request is !monghazi :) 
				 req.setNamedarkhastdahande(user.getFullname());
				 req.setDarkhastdahandeid(user.getPersonalId());
				 System.out.println("user saved");
				 reqRepo.save(req);
				 
				 System.out.println("form saved");
				 return "Request saved successfully";
			}else {
				return "Invalid Token!";
			}
		}
	}
	@PostMapping("/app/addTajhiz/{pid}/{token}")
	public String addT(@PathVariable String pid, @PathVariable String token,darkhast f) {
		List<User> uList = userRepo.findBypersonalId(pid);
		if(uList.isEmpty()) {
			return "Invalid PersonalId";
		}else {
			if(uList.get(0).getToken().contentEquals(token)) {
				Optional<darkhast> reqList = reqRepo.findById(f.getId());
				if(reqList.isPresent()) {
					darkhast req = reqList.get();
					boolean found = false;
					if(f.getTajhizats() != null)
				    {
							
				    
					    Iterator<Tajhizat> itr = f.getTajhizats().iterator(); 

					    while(itr.hasNext())
					    {   
					        if(req.getTajhizats().contains(itr.next()))
					        {
					             
					            found = true; 
					        }
					    }

						   if(found) {
							   return "this device added befor";
						   }
					    
						   for(Tajhizat tj: f.getTajhizats()) {
							
							req.getTajhizats().add(tj);
							
							}
				    }
					reqRepo.save(req);
					return "Successfully added";
				}else {
					return "Invalid request id";
				}
				
			} else {
				return "Invalid User Token!";
			}
		}
	}
}
