package com.javasampleapproach.jqueryboostraptable.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Base64;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.javasampleapproach.jqueryboostraptable.model.FormJob;
import com.javasampleapproach.jqueryboostraptable.model.Location;
import com.javasampleapproach.jqueryboostraptable.model.Tajhizat;
import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.model.darkhast;
import com.javasampleapproach.jqueryboostraptable.repository.darkhastRepository;
import com.javasampleapproach.jqueryboostraptable.repository.LocationRepository;
import com.javasampleapproach.jqueryboostraptable.repository.RoleRepository;
import com.javasampleapproach.jqueryboostraptable.repository.Roozh;
import com.javasampleapproach.jqueryboostraptable.repository.TajhizatRepository;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;

@Service
@Controller
public class OfficeController {

	
	@Autowired
	private TajhizatRepository tRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private darkhastRepository darRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private LocationRepository locRepo;
	
	
	 @GetMapping("/fa/tajhizats")
	   public String viewTajhizat(Model model){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
		 
	 		model.addAttribute("tajhizats",tRepo.findAll());
	 		
	 		 
	 		 return "Tajhizat";
	     }
	 @GetMapping("/report")
	 public String report(Model model){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
		 return "report";
	 }
	 @GetMapping("/printQr")
	 public String printQR(Model model,int id) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
		 model.addAttribute("tajhizz", tRepo.findById(id).get());
		 return "print";
	 }
	 @GetMapping("/fa/requests")
	   public String viewoffice(Model model ){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findByUsername(auth.getName());
			//	Set<User> us = new HashSet<>(userRepo.findBypersonalId(user.getPersonalId()));	
			List<Tajhizat> t = tRepo.findAll();
			t.removeAll(user.getTajhizats());
			List<darkhast> d = darRepo.findByUsers(userRepo.findBypersonalId(user.getPersonalId()));
	 		model.addAttribute("requests",d);
	 		model.addAttribute("user", user);
	 		model.addAttribute("tajhiz", t);
	 		model.addAttribute("userName", "خوش آمدید" + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
	 		model.addAttribute("loc",locRepo.findAll());
	 		 return "requestForm";
	     }
	 	@GetMapping("/fa/req")
	 	public String viewform(Model model, int id ){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findByUsername(auth.getName());
				darkhast re = darRepo.findById((long) id).get();
				model.addAttribute("req", re);
				model.addAttribute("userName", "خوش آمدید" + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
				model.addAttribute("tajhiz",re.getTajhizats());
				model.addAttribute("user", user);
				return "req";
	 	}
	 	@GetMapping("/fa/locations")
	 	public String viewloc(Model model){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findByUsername(auth.getName());
				
				model.addAttribute("locations",locRepo.findAll());
				model.addAttribute("userName", "خوش آمدید" + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
				return "location";
	 	}
	 	
	 	@GetMapping("/")
	 	public String h() {
	 		
	 		return "redirect:/fa/requests";
	 	}
	 	@GetMapping("/findAllTajhiz")
		@ResponseBody
		public List<Tajhizat> findAllTajhiz() {
			return tRepo.findAll();
		}
	 @GetMapping("/findOneTajhiz")
		@ResponseBody
		public Optional<Tajhizat> findOneTajhiz(Integer id) {
			return tRepo.findById(id);
		}
	 @GetMapping("/findAllrequests")
	 @ResponseBody
	 public List<darkhast> findAllRequests(String pid) {
		 
		 return darRepo.findByUsers(userRepo.findBypersonalId(pid));
	 }
	 @GetMapping("/findAllReq/tajhiz")
	 @ResponseBody
	 public List<darkhast> findAllRs(Integer id) {
		 
		 return darRepo.findByTajhizats(tRepo.findById(id));
	 }
	 @GetMapping("/findAllReq/user")
	 @ResponseBody
	 public List<darkhast> findAllRu(String name) {
		 
		 return darRepo.findByDarkhastdahandeid(name);
	 }
	 @GetMapping("/findOneReq")
		@ResponseBody
		public Optional<darkhast> findOneDarkhast(Integer id) {
			return darRepo.findById((long) id);
		}
	 @GetMapping("/fa/deleteTajhiz")
		public String deleteTajhiz(Integer id) {
		tRepo.deleteById(id);
		return "redirect:/fa/tajhizats";
		}
	 
	@GetMapping("fa/deleteLocation") 
	public String deleteLoc(Integer id) {
		locRepo.deleteById(id);
		return "redirect:/fa/locations";
		}
	
//	 @GetMapping(value = "/fa/saveTajhiz")
//	   	public String gQRCode(
//	   			 String codeText)
//	   	
//	   		    throws Exception {
//	   		byte[] data = QRCodeGenerator.getQRCodeImage(codeText, 350, 350);
//	   		String encodedString = Base64.getEncoder().encodeToString(data);
//	   		Tajhizat t = tRepo.findById(Integer.parseInt(codeText)).get();
//	   		t.setQrcode(encodedString);
//	   		tRepo.save(t);
//	   		        return "redirect:/fa/tajhizats";
//	   		    }
	 @PostMapping("/fa/saveLocation")
	 public String saveloc(Location loc) {
		 locRepo.save(loc);
		 return "redirect:/fa/locations";
	 }
	 @GetMapping("/findOneLocation")
	 @ResponseBody
	 public Optional<Location> findoneloc(Integer id){
		 return locRepo.findById(id);
	 }
	 
	 @PostMapping("/fa/saveTajhiz")
	public String Esave(Tajhizat t, MultipartFile file) {
		   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("not a valid file");
			}
	try {
		t.setImg(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException er) {
		System.out.println("file ");
		er.printStackTrace();
	}
	
		  tRepo.save(t);
		  System.out.println("after save-------->"+tRepo.findById(t.getId()));
			return "redirect:/fa/genrateQRCode/?codeText="+t.getId();
		}

	 @PostMapping("/fa/saveRequest")
	 public String saveForm(darkhast req) {
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User u = userRepo.findBypersonalId(auth.getName()).get(0);
		 System.out.println("nameeeeeeeeeeeeeeeeeeee"+auth.getName());
		 System.out.println("-------------------->"+req.toString());
		 
		 System.out.println("---------formgetuser----------->"+(req.getUsers() == null));
		 
		 if(req.getUsers() == null) {
			 req.setUsers(userRepo.findBypersonalId(u.getPersonalId()));
		 System.out.println("foorm if is empty");
		// form.setUsers(new HashSet<>(userRepo.findBypersonalId(u.getPersonalId())));
		 }
		 else {
		 
		 System.out.println("foorm add user else ");
		 }
		 System.out.println("foorrrmmm"+req);
		 System.out.println("ussserrrrr"+u);
		 req.setMonghazi(5);  // monghazi = 5 means request has not been approved yet  :) 
		 req.setNamedarkhastdahande(u.getFullname());
		 req.setDarkhastdahandeid(u.getPersonalId());
		 req.setLaghv(0); // 0 means Request don't send for enybody yet !   
		 System.out.println("user saved");
		 darRepo.save(req);
		 System.out.println("form saved");
		 return "redirect:/fa/requests";
		 
	 }
	 @PostMapping("/fa/saveEmza")
	 public String saveEmza(FormJob fj) {		 
		 String redirect = "redirect:/fa/req/?id="+fj.getFid();
		 darkhast of = darRepo.findById(fj.getFid()).get();
		 User u = userRepo.findById(fj.getUid()).get();
		 System.out.println("userrr ---"+ of.getNamedarkhastdahande().contentEquals(u.getFullname()));
		 Roozh jCal = new Roozh();
			jCal.gregorianToPersian(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
			String cal = jCal.getYear()+"/"+jCal.getMonth()+"/"+jCal.getDay();
			Integer hour = LocalTime.now().getHour();
			Integer minute = LocalTime.now().getMinute();
			String now = hour+":"+ minute;
		  if(of.getNamedarkhastdahande().contentEquals(u.getFullname())) {
			  
			  if(of.getSaatd() == null) {
				  
			    
			of.setLaghv(1); // 1 means request send to MODIR , or signed by user 
			of.setMonghazi(4); // 4 means don't verify by ANBARDAR	
			of.getUsers().addAll(userRepo.findByRoles(roleRepo.findByname("MODIR"))); //ADD MODIRS TO REQUEST FORM
			of.setTarikh(cal);
			of.setEmza(u.getEmza());
			of.setSaatd(now); 
			  }
		  }else {
			
			  if(u.getRoles().get(0).getName().contentEquals("ANBARDAR")) {
				  of.setLaghv(3); // 3 means request signed by ANBARDAR  
				  for(Tajhizat t : of.getTajhizats()) {
					  if(fj.getTid().equals(1)) {
						  t.setState("در دسترس");
						 
					  }else if(fj.getTid().equals(3)) {
						  t.setState("خراب");
						 
					  }else {
						  t.setState("در حال استفاده");
						  List<Tajhizat> tj = new ArrayList<Tajhizat>();
						  tj.add(t);
						  // remove this tajhiz from other requests that don't sign by ANBARDAR
						  for(darkhast r : darRepo.findByTajhizatsAndMonghazi(tj, 4)) {  // return requests that this tajhiz is added and ANBARDAR dont verify yet.
							if(!r.getId().equals(of.getId())) {
								r.getTajhizats().remove(t); 
								r.setLaghv(33); // 33 means some tajhiz deleted from yor request
								darRepo.save(r);
							}  
						  }
						  
					  }
					  tRepo.save(t);
				  }
				  of.setAnbaremza(u.getEmza());
				  if(fj.getTid().equals(0)) {
				of.setTarikhtahvil(fj.getJob());
				of.setSaatt(fj.getKhodro());
				of.setLaghv(11); // 11 means that user got devices from ANBARDAR
				  }else {
					  
					  of.setTarikhtahvil(cal);
					  of.setSaatt(now);
				  }
				if(fj.getJob().contentEquals(cal)) { 
					of.setMonghazi(2);  // monghazi = 2 means devices most back today
				}else {
				
				of.setMonghazi(fj.getTid());
				}
			  }else { // all user with role MODIR
				  of.getUsers().addAll(userRepo.findByRoles(roleRepo.findByname("ANBARDAR")));
				  of.setTaeedemza(u.getEmza());
				  of.setLaghv(2); // 2 means MODIR sign request
			  }
		  }

	
		  darRepo.save(of);

		 return redirect;
	 }
	 
	 @PostMapping("/fa/addTajhiz")
	 public String addttou(User u) {
		 User user = userRepo.findById(u.getId()).get();
		 	
	
		    boolean found = false;
			if(u.getTajhizats() != null)
		    {
					
		    
			    Iterator<Tajhizat> itr = u.getTajhizats().iterator(); 

			    while(itr.hasNext())
			    {   
			        if(user.getTajhizats().contains(itr.next()))
			        {
			             
			            found = true; 
			        }
			    }

				   if(found) {
					   return "redirect:/fa/adminprofile/?id="+u.getId();
				   }
			    
				   for(Tajhizat tj: u.getTajhizats()) {
					
					user.getTajhizats().add(tj);
					
					}
		    }

		 
			
		userRepo.save(user);
			
		 return "redirect:/fa/adminprofile/?id="+u.getId();
	 }
	 @PostMapping("/fa/addForm")
	 public String addutoform(darkhast f) {
		 
		 darkhast form = darRepo.findById(f.getId()).get();

    boolean found = false;
	if(f.getUsers() != null)
    {
			
    
	    Iterator<User> itr = f.getUsers().iterator(); 

	    while(itr.hasNext())
	    {   
	        if(form.getUsers().contains(itr.next()))
	        {
	             
	            found = true; 
	        }
	    }

		   if(found) {
			   return "redirect:/office";
		   }
	    
		   for(User us: f.getUsers()) {
			
			form.getUsers().add(us);
			
			}
    }

	    
	
	if(f.getTajhizats() != null)
    {
			
    
	    Iterator<Tajhizat> itr = f.getTajhizats().iterator(); 

	    while(itr.hasNext())
	    {   
	        if(form.getTajhizats().contains(itr.next()))
	        {
	             
	            found = true; 
	        }
	    }

		   if(found) {
			   return "redirect:/fa/requests";
		   }
	    
		   for(Tajhizat tj: f.getTajhizats()) {
			
			form.getTajhizats().add(tj);
			
			}
    }

		
		 darRepo.save(form);
		 
		 return "redirect:/fa/requests";
	 }
	 
	 @GetMapping("/findbyjob/{job}")
	 @ResponseBody
	 public List<User> findU(@PathVariable String job){
		 
		 return userRepo.findByJob(job);
	 }
	 
	 @GetMapping("/search/tajhiz/{keyword}")
	 @ResponseBody
	 public List<Tajhizat> findt(@PathVariable String keyword){
		 
		 return tRepo.search(keyword);
	 }
	 
	 @GetMapping("/search/user/{keyword}")
	 @ResponseBody
	 public List<User> findus(@PathVariable String keyword){
		 
		 return userRepo.search(keyword);
	 }
	  
	  @GetMapping("/deleteUserTajhiz")
	  public String deleteusertajhiz(int tid,int uid) {
		  Optional<User> userList = userRepo.findById(uid);
		  if(userList.isPresent()) {
			  Optional<Tajhizat> tList = tRepo.findById(tid);
			  if(tList.isPresent()) {
				  User user = userList.get();
				  user.getTajhizats().remove(tList.get());
				  userRepo.save(user);
				  return "redirect:/fa/adminprofile/?id="+uid;
			  }else {
				  throw new UserNotFoundException("tajhiz doesn't exist!");
			  }
		  }else {
			  throw new UserNotFoundException("user not exist!");
		  }
	  }
	  
}
