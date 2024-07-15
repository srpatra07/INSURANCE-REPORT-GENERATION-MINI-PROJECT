package com.devswa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.devswa.entity.CitizenPlan;
import com.devswa.repo.CitizenPlanRepository;
import com.devswa.request.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public List<String> getPlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}

		if (request.getStartDate() != null && !request.getStartDate().equals("")) {
			String start = request.getStartDate();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate startDate = LocalDate.parse(start, formatter);
			entity.setStartDate(startDate);
		}
		
		if (request.getEndDate() != null && !request.getEndDate().equals("")) {
			String end = request.getEndDate();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate endDate = LocalDate.parse(end, formatter);
			entity.setStartDate(endDate);
		}

		return repo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
