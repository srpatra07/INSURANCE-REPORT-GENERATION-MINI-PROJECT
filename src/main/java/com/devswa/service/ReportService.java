package com.devswa.service;

import java.util.List;

import com.devswa.entity.CitizenPlan;
import com.devswa.request.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel();
	
	public boolean exportPdf();
}
