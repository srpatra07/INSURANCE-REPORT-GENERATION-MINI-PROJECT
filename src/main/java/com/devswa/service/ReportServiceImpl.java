package com.devswa.service;

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

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Sl No.");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");

		int dataRow = 1;

		List<CitizenPlan> plans = repo.findAll();

		for (CitizenPlan plan : plans) {
			Row data = sheet.createRow(dataRow);
			data.createCell(0).setCellValue(plan.getCitizenId());
			data.createCell(1).setCellValue(plan.getCitizenName());
			data.createCell(2).setCellValue(plan.getGender());
			data.createCell(3).setCellValue(plan.getPlanName());
			data.createCell(4).setCellValue(plan.getPlanStatus());

			if (null != plan.getStartDate()) {
				data.createCell(5).setCellValue(plan.getStartDate() + "");
			} else {
				data.createCell(5).setCellValue("N/A");
			}
			if (null != plan.getEndDate()) {
				data.createCell(6).setCellValue(plan.getEndDate() + "");
			} else {
				data.createCell(6).setCellValue("N/A");
			}
			if (null != plan.getBenifitAmt()) {
				data.createCell(7).setCellValue(plan.getBenifitAmt());
			} else {
				data.createCell(7).setCellValue("N/A");
			}

			dataRow++;

		}

		ServletOutputStream os = response.getOutputStream();

		workbook.write(os);
		workbook.close();

		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Paragraph p = new Paragraph("Citizen Plans Info");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		table.addCell("Sl No.");
		table.addCell("Holder Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");

		List<CitizenPlan> plans = repo.findAll();

		for (CitizenPlan plan : plans) {
			table.addCell(plan.getCitizenId() + "");
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());

			if (null != plan.getStartDate()) {
				table.addCell(plan.getStartDate() + "");
			} else {
				table.addCell("N/A");
			}

			if (null != plan.getEndDate()) {
				table.addCell(plan.getEndDate() + "");
			} else {
				table.addCell("N/A");
			}

			if (null != plan.getBenifitAmt()) {
				table.addCell(plan.getBenifitAmt() + "");
			} else {
				table.addCell("N/A");
			}

		}
		
		document.add(table);
		document.close();

		return true;
	}

}
