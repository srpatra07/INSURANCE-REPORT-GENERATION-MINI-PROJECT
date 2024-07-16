package com.devswa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devswa.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
	@Autowired
	EmailUtils email;

	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception {
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
		
		FileOutputStream fos = new FileOutputStream(f);
		workbook.write(fos);

		ServletOutputStream os = response.getOutputStream();
		workbook.write(os);
		
		workbook.close();
	}
}
