package com.javasampleapproach.jqueryboostraptable.controller;

import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.repository.RoleRepository;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;
import com.javasampleapproach.jqueryboostraptable.controller.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
   // @Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private WebConfiguration webconfiguration;
    
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    

    
    private static byte[] digest(byte[] input) {
    	MessageDigest md;
    	try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
    	byte[] result = md.digest(input);
    	return result;
    }
    private static String byteToHex(byte[] bytes) {
    	StringBuilder sb = new StringBuilder();
    	for(byte b : bytes) {
    		sb.append(String.format("%02x", b));
    	}
    	return sb.toString();
    }
    
    
    @Override
    public void save(User user , Integer e) {
    	String t = user.getPersonalId() + user.getPass();
    byte[] md5InByte = digest(t.getBytes(UTF_8));
    System.out.println(byteToHex(md5InByte));
    user.setToken(byteToHex(md5InByte));
    	if(!user.getPass().isEmpty()) {
        user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
    }
        user.setFullname(user.getFName() + " "+ user.getLname());
        if(e==0) {
        user.setRoles(roleRepository.findByname("ADMIN"));
        }else if(e==1) {
        	 user.setRoles(roleRepository.findByname("USER"));
        }else if(e == 2) {
        	 user.setRoles(roleRepository.findByname("MODIR"));
        }else {
        	user.setRoles(roleRepository.findByname("ANBARDAR"));
        }
        userRepository.save(user);
    }
    @Override
    public void saveuemza(User user , Integer e, MultipartFile file) {
    	String t = user.getPersonalId() + user.getPass();
        byte[] md5InByte = digest(t.getBytes(UTF_8));
        System.out.println(byteToHex(md5InByte));
        user.setToken(byteToHex(md5InByte));
        if(!user.getPass().isEmpty())
        user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
        
        user.setFullname(user.getFName() + " "+ user.getLname());
        if(e==0) {
        user.setRoles(roleRepository.findByname("ADMIN"));
        }else if(e==1) {
       	 user.setRoles(roleRepository.findByname("USER"));
       }else if(e == 2) {
       	 user.setRoles(roleRepository.findByname("MODIR"));
        }else {
        	 user.setRoles(roleRepository.findByname("ANBARDAR"));
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
try {
	user.setEmza(Base64.getEncoder().encodeToString(file.getBytes()));
} catch (IOException er) {
	System.out.println("file ");
	er.printStackTrace();
}
        userRepository.save(user);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findBypersonalId(username).get(0);
    }
	@Override
	public void saveprofile(User user, Integer e, MultipartFile file) {
		String t = user.getPersonalId() + user.getPass();
	    byte[] md5InByte = digest(t.getBytes(UTF_8));
	    System.out.println(byteToHex(md5InByte));
	    user.setToken(byteToHex(md5InByte));
	    if(!user.getPass().isEmpty())
		 user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
	       
	        if(e==0) {
	        user.setRoles(roleRepository.findByname("ADMIN"));
	        }else if(e==1) {
	        	 user.setRoles(roleRepository.findByname("USER"));
	        }else if(e == 2) {
	        	 user.setRoles(roleRepository.findByname("MODIR"));
	        }else {
	        	 user.setRoles(roleRepository.findByname("ANBARDAR"));
	        }
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("not a valid file");
			}
	try {
		user.setProfile(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException er) {
		System.out.println("file ");
		er.printStackTrace();
	}
	        userRepository.save(user);
		
	}
	@Override
	public void savepro(User user, MultipartFile file) {
		String t = user.getPersonalId() + user.getPass();
	    byte[] md5InByte = digest(t.getBytes(UTF_8));
	    System.out.println(byteToHex(md5InByte));
	    user.setToken(byteToHex(md5InByte));
		
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("not a valid file");
			}
	try {
		user.setProfile(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException er) {
		System.out.println("file ");
		er.printStackTrace();
	}
	        userRepository.save(user);
		
	}
	
}