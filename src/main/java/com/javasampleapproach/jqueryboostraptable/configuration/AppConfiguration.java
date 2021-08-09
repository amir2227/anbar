package com.javasampleapproach.jqueryboostraptable.configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.model.darkhast;

import com.javasampleapproach.jqueryboostraptable.repository.Roozh;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;
import com.javasampleapproach.jqueryboostraptable.repository.darkhastRepository;

@Configuration
@EnableScheduling
public class AppConfiguration{
	
	
	@Autowired
	private darkhastRepository darRepo;
	
	@Autowired
	private UserRepository userRepo;
		

	
	List<Integer> flag = new ArrayList<Integer>();
	
	// second , minute , hour , day , month , weekday
	@Scheduled(cron ="0 15 7 * * ?")  //every day at 7:15
	public void message() {
		System.out.println("heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy this is 3 sec the delay");
		
		Roozh jCal = new Roozh();
		jCal.gregorianToPersian(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		String cal = jCal.getYear()+"/"+jCal.getMonth()+"/"+jCal.getDay();
		System.out.println("date------->" +cal);
		
		for(darkhast d :  darRepo.findByMonghazi(0)) {	// darkhast hay monghazi shode meghdar monghazi = 1
			System.out.println("-------------->:::::::"+d);
		
			if(d.getTarikhtahvil().contentEquals(cal)) {
			// send notify to user darkhast dahandeh va modir va anbardar
				System.out.println("after for and if"+ d.getUsers());
				List<User> lu = d.getUsers();
				d.setMonghazi(2);
				darRepo.save(d);
				System.out.println("lu---->"+lu);
				for(int i=0; i <lu.size();i++) { // send notify for all of user that sign request
					System.out.println("lu.get(i)--------->"+lu.get(i));
			lu.get(i).setNotify("با سلام"+d.getNamedarkhastdahande()+" امروز در ساعت"+ d.getSaatt()+ "باید اجناس را به انبار تحویل دهد");
			System.out.println("after set");
			userRepo.save(lu.get(i));
			System.out.println("after save");
			flag.add(lu.get(i).getId());
			System.out.println("flag------>"+flag);
				}
			}
			
		}
		}
	
	
	@Scheduled(cron = "0 0 0/1 * * ?") // every hour
	public void chek() {
	
		System.out.println("local-------->"+LocalTime.now().toString().substring(0,2));
		for(darkhast d :  darRepo.findByMonghazi(2)) { // request dead line for today
			System.out.println("database saatt----->"+d.getSaatt().substring(0, 2));
			System.out.println("compare---->"+d.getSaatt().substring(0, 2).contentEquals(LocalTime.now().toString().substring(0, 2)));
			if(d.getSaatt().substring(0, 2).contentEquals(LocalTime.now().toString().substring(0, 2)) ) {
			List<User> us = d.getUsers();
			
			for(int j = 0; j<us.size();j++) {
				
				us.get(j).setNotify(" با سلام "+us.get(j).getFullname()+" ساعت تحویل اجناس انبار "+d.getSaatt()+ " لطفا سریعا اقدام فرمایید ");
				userRepo.save(us.get(j));
			}
		}
		}	
	}
	
	@Scheduled(cron ="1 0 0 * * ?")  //every day at 00:00:01
	public void cc() {
		for(darkhast d : darRepo.findByMonghazi(2)) {
			d.setMonghazi(99);
			darRepo.save(d);
		}
	}
	
	
}
