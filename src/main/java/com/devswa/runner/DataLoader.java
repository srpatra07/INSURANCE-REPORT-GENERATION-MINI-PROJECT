package com.devswa.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.devswa.entity.CitizenPlan;
import com.devswa.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		repo.deleteAll();
//
//		// Cash plan Record
//		CitizenPlan c1 = new CitizenPlan();
//		c1.setCitizenName("John");
//		c1.setGender("Male");
//		c1.setPlanName("Cash");
//		c1.setPlanStatus("Approved");
//		c1.setStartDate(LocalDate.now());
//		c1.setEndDate(LocalDate.now().plusMonths(6));
//		c1.setBenifitAmt(5000.00);
//
//		CitizenPlan c2 = new CitizenPlan();
//		c2.setCitizenName("Smith");
//		c2.setGender("Male");
//		c2.setPlanName("Cash");
//		c2.setPlanStatus("Denied");
//		c2.setDenialReson("Rental Income");
//
//		CitizenPlan c3 = new CitizenPlan();
//		c3.setCitizenName("Cathy");
//		c3.setGender("Fe-Male");
//		c3.setPlanName("Cash");
//		c3.setPlanStatus("Terminated");
//		c3.setStartDate(LocalDate.now().minusMonths(4));
//		c3.setEndDate(LocalDate.now().plusMonths(2));
//		c3.setBenifitAmt(5000.00);
//		c3.setTerminationDate(LocalDate.now());
//		c3.setTerminationReason("Employed");
//
//		// Food Plan data
//
//		CitizenPlan c4 = new CitizenPlan();
//		c4.setCitizenName("David");
//		c4.setGender("Male");
//		c4.setPlanName("Food");
//		c4.setPlanStatus("Approved");
//		c4.setStartDate(LocalDate.now());
//		c4.setEndDate(LocalDate.now().plusMonths(6));
//		c4.setBenifitAmt(4000.00);
//
//		CitizenPlan c5 = new CitizenPlan();
//		c5.setCitizenName("Robert");
//		c5.setGender("Male");
//		c5.setPlanName("Food");
//		c5.setPlanStatus("Denied");
//		c5.setDenialReson("Property Income");
//
//		CitizenPlan c6 = new CitizenPlan();
//		c6.setCitizenName("Orlen");
//		c6.setGender("Fe-Male");
//		c6.setPlanName("Food");
//		c6.setPlanStatus("Terminated");
//		c6.setStartDate(LocalDate.now().minusMonths(4));
//		c6.setEndDate(LocalDate.now().plusMonths(2));
//		c6.setBenifitAmt(5000.00);
//		c6.setTerminationDate(LocalDate.now());
//		c6.setTerminationReason("Employed");
//
//		// Medical Plan data
//
//		CitizenPlan c7 = new CitizenPlan();
//		c7.setCitizenName("Charles");
//		c7.setGender("Male");
//		c7.setPlanName("Medical");
//		c7.setPlanStatus("Approved");
//		c7.setStartDate(LocalDate.now());
//		c7.setEndDate(LocalDate.now().plusMonths(6));
//		c7.setBenifitAmt(4000.00);
//
//		CitizenPlan c8 = new CitizenPlan();
//		c8.setCitizenName("Buttler");
//		c8.setGender("Male");
//		c8.setPlanName("Medical");
//		c8.setPlanStatus("Denied");
//		c8.setDenialReson("Property Income");
//
//		CitizenPlan c9 = new CitizenPlan();
//		c9.setCitizenName("Neel");
//		c9.setGender("Fe-Male");
//		c9.setPlanName("Medical");
//		c9.setPlanStatus("Terminated");
//		c9.setStartDate(LocalDate.now().minusMonths(4));
//		c9.setEndDate(LocalDate.now().plusMonths(2));
//		c9.setBenifitAmt(5000.00);
//		c9.setTerminationDate(LocalDate.now());
//		c9.setTerminationReason("Govt Job");
//
//		// Employment Plan data
//
//		CitizenPlan c10 = new CitizenPlan();
//		c10.setCitizenName("Steve");
//		c10.setGender("Male");
//		c10.setPlanName("Employment");
//		c10.setPlanStatus("Approved");
//		c10.setStartDate(LocalDate.now());
//		c10.setEndDate(LocalDate.now().plusMonths(6));
//		c10.setBenifitAmt(4000.00);
//
//		CitizenPlan c11 = new CitizenPlan();
//		c11.setCitizenName("Morris");
//		c11.setGender("Male");
//		c11.setPlanName("Employment");
//		c11.setPlanStatus("Denied");
//		c11.setDenialReson("Property Income");
//
//		CitizenPlan c12 = new CitizenPlan();
//		c12.setCitizenName("Gibbs");
//		c12.setGender("Fe-Male");
//		c12.setPlanName("Employment");
//		c12.setPlanStatus("Terminated");
//		c12.setStartDate(LocalDate.now().minusMonths(4));
//		c12.setEndDate(LocalDate.now().plusMonths(2));
//		c12.setBenifitAmt(5000.00);
//		c12.setTerminationDate(LocalDate.now());
//		c12.setTerminationReason("Govt Job");
//		
//		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
//		
//		repo.saveAll(list);

	}

}
