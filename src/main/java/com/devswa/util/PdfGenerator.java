package com.devswa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.devswa.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception
	{
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));

		document.open();

		Paragraph p = new Paragraph("Citizen Plans Info");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.5f, 4.0f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		table.addCell("Sl No.");
		table.addCell("Holder Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");

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
	}
	
}
