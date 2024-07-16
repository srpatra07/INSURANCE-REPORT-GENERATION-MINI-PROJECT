package com.devswa.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.devswa.entity.CitizenPlan;
import com.devswa.repo.CitizenPlanRepository;
import com.devswa.request.SearchRequest;
import com.devswa.util.EmailUtils;
import com.devswa.util.ExcelGenerator;
import com.devswa.util.PdfGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils email;

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
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("plans.xls");

		excelGenerator.generate(response, repo.findAll(),f);
		
		String subject = "Citizen Plans Info";
		String body = "<h2>Excel Report of Citizens</h2>";
		String to = "swarupranjan2424@gmail.com";
		
		email.sendEmail(subject, body, to, f);
		
		f.delete();

		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		File f = new File("plans.pdf");
		
		pdfGenerator.generate(response, repo.findAll(), f);
		
		String subject = "Citizen Plans Info";
		String body = "<h2>PDF Report of Citizens</h2>";
		String to = "swarupranjan2424@gmail.com";
		
		email.sendEmail(subject, body, to, f);
		
		f.delete();

		return true;
	}

}
