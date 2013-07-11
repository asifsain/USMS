package com.usms.pdfgeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.usms.db.model.EmpInfo;
import com.usms.db.model.EmpSalTrx;
import com.usms.db.model.EmployerDetail;

public class USMSPDFDocumentGeneration {

	static ServletContext context = (ServletContext) FacesContext
			.getCurrentInstance().getExternalContext().getContext();
	private static String amount = "";
	private static String[] units = { "", " One", " Two", " Three", " Four",
			" Five", " Six", " Seven", " Eight", " Nine" };
	private static String[] teen = { " Ten", " Eleven", " Twelve", " Thirteen",
			" Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen",
			" Nineteen" };
	private static String[] tens = { " Twenty", " Thirty", " Fourty", " Fifty",
			" Sixty", " Seventy", " Eighty", " Ninety" };
	private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh",
			" Crore" };

	public static void Create_SalarySlip_Pdf(EmpSalTrx empSalTrx,
			String filepath) {
       
		try {
			Document doc = new Document(PageSize.A4);
			File newfile = new File(filepath);
			newfile.mkdirs();
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ empSalTrx.getEmpName() + "_" + empSalTrx.getSalTrxMonth()
					+ "_" + "SalarySlip.pdf"));
			PdfWriter.getInstance(doc, file);
			BaseFont bfTimesFoot = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, false);
			Font FooterFont = new Font(bfTimesFoot, 12, Font.BOLDITALIC);
			Font FooterFont1 = new Font(bfTimesFoot, 12, Font.BOLD);
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD,
					new BaseColor(0, 0, 0));
			Font FooterFont2 = new Font(bfTimesFoot, 14, Font.BOLD);
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10);
			doc.open();
			PdfPTable header = new PdfPTable(3);
			header.getDefaultCell().setBorder(0);
			header.addCell("");
			header.addCell("");
			String headerImageFile = context
					.getRealPath("/resources/images/uitslogo.jpg");
			Image img = Image.getInstance(headerImageFile);
			img.setAbsolutePosition(300, 300);
			header.addCell(img);

			Paragraph headerempty = new Paragraph();
			addEmptyLine(headerempty, 1);

			Paragraph empty0 = new Paragraph();
			addEmptyLine(empty0, 1);

			Paragraph empty1 = new Paragraph("");
			addEmptyLine(empty1, 1);
			LineSeparator UNDERLINE = new LineSeparator(0, 95, null,
					Element.ALIGN_CENTER, 3);
			Paragraph pline = new Paragraph(new Chunk(UNDERLINE));
			Paragraph heading = new Paragraph("Pay Slip", FooterFont2);
			heading.setAlignment(Element.ALIGN_CENTER);

			LineSeparator UNDERLINE1 = new LineSeparator(0, 95, null,
					Element.ALIGN_CENTER, 3);
			Paragraph pline2 = new Paragraph(new Chunk(UNDERLINE1));

			// PdfPTable table = new PdfPTable(1);
			// table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
			// // table.addCell(new PdfPCell(new Phrase("Pay Slip  ",
			// FooterFont1)));
			// insertCell(table, "PaySlip", Element.ALIGN_CENTER, 1,
			// bfBold12,BaseColor.WHITE);
			Paragraph empty2 = new Paragraph("");
			addEmptyLine(empty2, 1);
			PdfPTable tablex = new PdfPTable(2);

			PdfPCell cell = new PdfPCell(new Phrase("Employee Details",
					bfBold12));

			Format yearformate = new SimpleDateFormat("YYYY");
			String month = yearformate.format(empSalTrx.getFromDt());

			cell.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell1 = new PdfPCell(new Phrase("Salary Account  Details",
					bfBold12));
			cell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell1.setBorder(Rectangle.NO_BORDER);
			Paragraph emptyLine = new Paragraph("");
			addEmptyLine(emptyLine, 1);
			PdfPTable tablex1 = new PdfPTable(2);
			PdfPCell cell2 = new PdfPCell(new Phrase("Name       :"
					+ empSalTrx.getEmpInfo().getFirstName()+" "+empSalTrx.getEmpInfo().getLastName(), bf12));
			cell2.setIndent(0);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell3 = new PdfPCell(new Phrase("BankName             :"
					+ empSalTrx.getEmpInfo().getEmpBankInfos().getBankName(),
					bf12));

			cell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell3.setBorder(Rectangle.NO_BORDER);
			PdfPTable tablex2 = new PdfPTable(2);
			PdfPCell cell4 = new PdfPCell(new Phrase("ID            :"
					+ empSalTrx.getEmpInfo().getEmpNo(), bf12));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setBorder(Rectangle.NO_BORDER);

			PdfPCell cell5 = new PdfPCell(new Phrase(
					"Location                 :"
							+ empSalTrx.getEmpInfo().getEmpBankInfos()
									.getBranch(), bf12));

			cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell5.setBorder(Rectangle.NO_BORDER);
			PdfPTable tablex3 = new PdfPTable(2);
			Format formatter = new SimpleDateFormat("dd-MM-YYYY");
			//

			// String For_year =
			// yearformate.format(empSalTrx.getEmpInfo().getEmpEmploymentInfos().getLstWrkDt());
			String Date_of_join = formatter.format(empSalTrx.getEmpInfo()
					.getEmpEmploymentInfos().getJoiningDt());
			// String Date_year=Date_of_join+year;
			PdfPCell cell6 = new PdfPCell(new Phrase("D.O.J       :"
					+ Date_of_join, bf12));
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell6.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell7 = new PdfPCell(new Phrase("AccountNumber"
					+ empSalTrx.getEmpInfo().getEmpBankInfos().getIbanNo(),
					bf12));
			cell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell7.setBorder(Rectangle.NO_BORDER);

			tablex.addCell(cell);

			tablex.addCell(cell1);

			tablex1.addCell(cell2);
			tablex1.addCell(cell3);
			tablex2.addCell(cell4);
			tablex2.addCell(cell5);
			tablex3.addCell(cell6);
			tablex3.addCell(cell7);

			Paragraph empty3 = new Paragraph("");
			addEmptyLine(empty3, 1);
			Paragraph p1 = new Paragraph("");
			p1.setAlignment(Element.ALIGN_CENTER);
			PdfPTable table_1 = new PdfPTable(3);
			table_1.setWidthPercentage(95);
			PdfPCell salcel = new PdfPCell();
			insertCell(table_1, "Attendence Details", Element.ALIGN_CENTER, 1,
					bfBold12, BaseColor.LIGHT_GRAY);
			insertCell(table_1, "Values", Element.ALIGN_CENTER, 1, bfBold12,
					BaseColor.LIGHT_GRAY);
			insertCell(table_1, "Salary For the Month Of",
					Element.ALIGN_CENTER, 3, bfBold12, BaseColor.LIGHT_GRAY);

			table_1.addCell(new PdfPCell(new Phrase("Total Days", bf12)));
			table_1.addCell(new PdfPCell(new Phrase(Integer.toString((int)((empSalTrx.getToDate().getTime()-empSalTrx.getFromDt().getTime())/(1000 * 60 * 60 * 24))), bf12)));

			salcel.setRowspan(4);
			// String value="";
			// table_1.add(empSalTrx.getSalTrxMonth());

			salcel.setPhrase(new Phrase(Element.ALIGN_CENTER));
			salcel.setPhrase(new Phrase("          "
					+ empSalTrx.getSalTrxMonth() + "," + month, bfBold12));
			table_1.addCell(salcel);

			table_1.addCell(new PdfPCell(new Phrase("Absent", bf12)));
			table_1.addCell(new PdfPCell(new Phrase("00 Days", bf12)));

			table_1.addCell(new PdfPCell(new Phrase("Sick Leave", bf12)));
			table_1.addCell(new PdfPCell(new Phrase("00 Days", bf12)));

			table_1.addCell(new PdfPCell(new Phrase("Annual leave", bf12)));
			table_1.addCell(new PdfPCell(new Phrase("00 Days", bf12)));

			PdfPTable table_2 = new PdfPTable(4);
			table_2.setWidthPercentage(95);

			Paragraph empty4 = new Paragraph("");
			addEmptyLine(empty4, 1);

			insertCell(table_2, "Earning", Element.ALIGN_CENTER, 1, bfBold12,
					BaseColor.LIGHT_GRAY);
			insertCell(table_2, "Amount", Element.ALIGN_CENTER, 1, bfBold12,
					BaseColor.LIGHT_GRAY);
			insertCell(table_2, "Deduction", Element.ALIGN_CENTER, 1, bfBold12,
					BaseColor.LIGHT_GRAY);
			insertCell(table_2, "Amount", Element.ALIGN_CENTER, 1, bfBold12,
					BaseColor.LIGHT_GRAY);

			Float SAL_AMT = empSalTrx.getSalAmt();
			Float ADJ_AMT = empSalTrx.getAdjAmt();
			Float netSalary = SAL_AMT - ADJ_AMT;
			table_2.setHeaderRows(1);

			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			float basic = empSalTrx.getEmpInfo().getEmpSalaryInfos().getBasic();

			table_2.addCell(new PdfPCell(new Phrase(" Salary", bf12)));
			table_2.addCell(new PdfPCell(new Phrase((Float.toString(basic)),
					bf12)));
			table_2.addCell(new PdfPCell(new Phrase("Salary Advance", bf12)));
			table_2.addCell(new PdfPCell(new Phrase((Float.toString(ADJ_AMT)),
					bf12)));
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos());

			table_2.addCell(new PdfPCell(new Phrase("Telephone Allowences",
					bf12)));
			float Transport = empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getTransport();
			table_2.addCell(new Phrase((Float.toString(Transport)), bf12));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			float housing = empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getHousing();
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getTransport());

			table_2.addCell(new PdfPCell(new Phrase("AirTicket", bf12)));
			table_2.addCell(new Phrase((Float.toString(housing)), bf12));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getHousing());

			table_2.addCell(new PdfPCell(new Phrase("Special Allowance", bf12)));
			float Other = empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getOtherAllow();
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getOtherAllow());
			table_2.addCell(new Phrase((Float.toString(Other)), bf12));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));

			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			table_2.addCell(new PdfPCell(new Phrase("Expenses Allowance", bf12)));
			float Other1 = empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getOtherAllow();
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getOtherAllow());
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			table_2.addCell(new PdfPCell(new Phrase("", FooterFont1)));
			table_2.addCell(new Phrase((new Phrase("", FooterFont1))));

			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);

			table_2.addCell(new PdfPCell(new Phrase("Total Earning", bf12)));

			float total = empSalTrx.getEmpInfo().getEmpSalaryInfos().getTotal();
			System.out.println(empSalTrx.getEmpInfo().getEmpSalaryInfos()
					.getTotal());
			table_2.addCell(new Phrase((Float.toString(SAL_AMT)), bf12));
			table_2.addCell(new PdfPCell(new Phrase("Total Deduction", bf12)));
			table_2.addCell(new PdfPCell(new Phrase(Float.toString(ADJ_AMT),
					bf12)));
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			insertCell(table_2, "", Element.ALIGN_LEFT, 1, bfBold12,
					BaseColor.WHITE);
			table_2.addCell(new PdfPCell(new Phrase("Net Salary", bf12)));

			table_2.addCell(new PdfPCell(new Phrase(Float.toString(netSalary),
					bf12)));
			Paragraph empty5 = new Paragraph("");
			addEmptyLine(empty5, 1);
			Paragraph par1 = new Paragraph("Special Communication", bfBold12);
			par1.setAlignment(Element.ALIGN_JUSTIFIED);

			par1.setIndentationLeft(10);
			Paragraph empty6 = new Paragraph("");
			addEmptyLine(empty6, 2);
			Paragraph par2 = new Paragraph(
					"• Any expense claim submitted after 25th of the month will be credited in next month salary.",
					bf12);

			par2.setIndentationLeft(40);
			Paragraph par3 = new Paragraph(
					"• Annual Leave cannot be encashed or carry forward to next year.",
					bf12);

			par3.setIndentationLeft(40);

			Paragraph empty7 = new Paragraph("");
			addEmptyLine(empty7, 3);

			// Paragraph empty8 = new Paragraph("");
			// addEmptyLine(empty8,10);
			PdfPTable table_3 = new PdfPTable(1);
			table_3.setWidthPercentage(95);
			insertCell(
					table_3,
					"This Pay Slip is computer generated one and doesn't require any signature",
					Element.ALIGN_CENTER, 1, bfBold12, BaseColor.WHITE);

			Paragraph empty9 = new Paragraph("");
			addEmptyLine(empty9, 2);

			PdfPTable footer = new PdfPTable(1);
			footer.getDefaultCell().setBorder(0);
			String footerfile = context
					.getRealPath("/resources/images/footer.JPG");
			Image footerimage = Image.getInstance(footerfile);
			img.setAbsolutePosition(300, 300);
			footer.addCell(footerimage);

			doc.add(empty1);

			doc.add(header);
			doc.add(headerempty);
			doc.add(pline);
			doc.add(heading);
			doc.add(pline2);
			// doc.add(table);
			doc.add(empty2);
			doc.add(tablex);
			doc.add(emptyLine);
			doc.add(tablex1);
			doc.add(tablex2);
			doc.add(tablex3);

			doc.add(empty3);
			doc.add(p1);
			doc.add(table_1);
			doc.add(empty4);
			doc.add(table_2);
			doc.add(empty4);
			doc.add(empty5);
			doc.add(par1);
			doc.add(par2);
			doc.add(par3);
			doc.add(empty6);

			doc.add(empty7);

			doc.add(table_3);
			doc.add(empty9);
			doc.add(footer);
			doc.close();
			System.out.println("Generated");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void create_ENDSERVICE_Exp_Letter_Pdf(EmpInfo empInfo,
			String filepath) {
		try {
			System.out.println("in pdf");
			Format formater = new SimpleDateFormat("dd-MM-YYYY");
			File newfile = new File(filepath);
			newfile.mkdirs();
			Document doc = new Document(PageSize.A4);
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ empInfo.getFirstName() + "_" + "ServiceEndExpLetter.pdf"));
			PdfWriter.getInstance(doc, file);
			BaseFont bfTimesFoot = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, false);
			Font FooterFont = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont1 = new Font(bfTimesFoot, 8, Font.BOLD);
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
			doc.open();
			PdfPTable header = new PdfPTable(3);
			header.getDefaultCell().setBorder(0);
			header.addCell("");
			header.addCell("");
			String headerImageFile = context
					.getRealPath("/resources/images/uitslogo.jpg");
			// String
			// filename="D:\\HRPAYROL\\NEWUSMS\\WebContent\\resources\\images\\uitslogo.jpg";
			Image img = Image.getInstance(headerImageFile);
			img.setAbsolutePosition(300, 300);
			header.addCell(img);
			Paragraph empty0 = new Paragraph();
			addEmptyLine(empty0, 1);
			PdfPTable tableheader = new PdfPTable(3);
			tableheader.getDefaultCell().setBorder(0);
			tableheader.addCell("");
			Paragraph empty1 = new Paragraph("");
			addEmptyLine(empty1, 3);
			Paragraph p = new Paragraph("TO WHOMSOVER IT MAY CONCERN",
					FooterFont);
			p.setAlignment(Element.ALIGN_CENTER);
			Paragraph p1 = new Paragraph("(Experience Letter)", FooterFont1);
			p1.setAlignment(Element.ALIGN_CENTER);
			Paragraph empty2 = new Paragraph("");
			addEmptyLine(empty2, 2);
			Paragraph p2 = new Paragraph("Date:" + ""
					+ formater.format(new Date()));
			Paragraph p3 = new Paragraph("Ref:" + "");
			p3.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty3 = new Paragraph("");
			addEmptyLine(empty3, 1);
			Paragraph p4;
			if (empInfo.getEmpEmploymentInfos().getLstWrkDt() == null) {
				// p4 = new
				// Paragraph("This is to confirm that  Mr/Mis/Miss."+empInfo.getFirstName()+
				// ", holder of Indian Passport No: "
				// +empInfo.getPassportInfos().getPpNo() +
				// " was employed as '"+empInfo.getEmpEmploymentInfos().getEmpDesignation()+
				// "‘under our sponsorship since "+
				// formater.format(empInfo.getEmpEmploymentInfos().getJoiningDt())+"and working with us till "
				// +"null"+".");

				p4 = new Paragraph("This is to confirm that  Mr./Miss./Mrs. ",
						bf12);
				Chunk ck = new Chunk(empInfo.getFirstName()+" "+empInfo.getLastName(), FooterFont);
				Chunk ck1 = new Chunk(", holder of Indian Passport No: ", bf12);
				Chunk ck2 = new Chunk(empInfo.getPassportInfos().getPpNo(),
						FooterFont);
				Chunk ck3 = new Chunk(" was employed as '"+ empInfo.getEmpEmploymentInfos().getEmpDesignation()+"'" , bf12);
				// Chunk ck4 = new
				// Chunk(empInfo.getEmpEmploymentInfos().getEmpDesignation(),bf12);
				Chunk ck5 = new Chunk("under our sponsorship since "
						+ formater.format(empInfo.getEmpEmploymentInfos()
								.getJoiningDt()) + " and working with us till "
						+ "null " + ".");
				p4.add(ck);
				p4.add(ck1);
				p4.add(ck2);
				p4.add(ck3);
				p4.add(ck5);
				p4.setAlignment(Element.ALIGN_JUSTIFIED);
				p4.setAlignment(Element.ALIGN_LEFT);
			} else {
				// p4 = new
				// Paragraph("This is to confirm that  Mr/Miss/Mrs."+empInfo.getFirstName()+
				// ", holder of Indian Passport No: "
				// +empInfo.getPassportInfos().getPpNo() +
				// "was employed as '"+empInfo.getEmpEmploymentInfos().getEmpDesignation()+
				// "‘under our sponsorship since "+
				// formater.format(empInfo.getEmpEmploymentInfos().getJoiningDt())+"and working with us till "
				// +formater.format(empInfo.getEmpEmploymentInfos().getLstWrkDt())+".");
				p4 = new Paragraph("This is to confirm that  Mr./Mrs./Miss.",
						bf12);
				Chunk ck = new Chunk(empInfo.getFirstName(), FooterFont);
				Chunk ck1 = new Chunk(", holder of Indian Passport No", bf12);
				Chunk ck2 = new Chunk(empInfo.getPassportInfos().getPpNo(),
						FooterFont);
				Chunk ck3 = new Chunk(" was employed as ", bf12);
				// Chunk ck4 = new
				// Chunk(empInfo.getEmpEmploymentInfos().getEmpDesignation(),bf12);
				Chunk ck5 = new Chunk("‘under our sponsorship since "
						+ formater.format(empInfo.getEmpEmploymentInfos()
								.getJoiningDt()) + "and working with us till "
						+ "null" + ".");
				p4.add(ck);
				p4.add(ck1);
				p4.add(ck2);
				p4.add(ck3);
				// p4.add(ck4);
				p4.add(ck5);

				p4.setAlignment(Element.ALIGN_JUSTIFIED);
				p4.setAlignment(Element.ALIGN_LEFT);
			}

			Paragraph empty4 = new Paragraph("");
			addEmptyLine(empty4, 1);
			Paragraph p5 = new Paragraph(
					"He was assigned to our Client to develop and enhance the software.",bf12);
			p5.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty5 = new Paragraph("");
			addEmptyLine(empty5, 1);

			Paragraph p6 = new Paragraph(
					"  Mr./Miss./Mrs. "
							+ empInfo.getFirstName()+" "+empInfo.getLastName()
							+ "'s duties have always a vital role of responsibility in achieving the objective of our clients.",bf12);
			Paragraph empty6 = new Paragraph("");
			addEmptyLine(empty6, 1);

			Paragraph p7 = new Paragraph("His duties during this time include:",bf12);
			p7.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty7 = new Paragraph("");
			addEmptyLine(empty7, 1);
			Paragraph p8 = new Paragraph(
					"Analysis, design, programming and support maintenance of application software systems.",bf12);
			p8.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty8 = new Paragraph("");
			addEmptyLine(empty8, 1);
			Paragraph p9 = new Paragraph(
					"He demonstrated reliable responsibilities and we found his performance to be entirely satisfactory and he has been a hard working and dedicated programmer.",bf12);
			p9.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty9 = new Paragraph("");
			addEmptyLine(empty9, 3);
			Paragraph p10 = new Paragraph("A.R Chandra Shekar");
			p10.setAlignment(Element.ALIGN_LEFT);
			Paragraph p11 = new Paragraph("Sr. Vice President");
			p11.setAlignment(Element.ALIGN_LEFT);  

			Paragraph p12 = new Paragraph("UNIQUE SOLUTIONS IT SERVICES");
			p12.setAlignment(Element.ALIGN_LEFT);
			Paragraph p13 = new Paragraph("DUBAI.");
			p13.setAlignment(Element.ALIGN_LEFT);
			Paragraph empty10 = new Paragraph("");
			addEmptyLine(empty10, 3);

			tableheader.addCell("");
			doc.add(empty0);

			doc.add(header);
			doc.add(empty0);
			doc.add(p);
			doc.add(p1);
			doc.add(tableheader);
			doc.add(empty1);

			doc.add(empty2);
			doc.add(p2);
			doc.add(p3);
			doc.add(empty3);
			doc.add(p4);
			doc.add(empty4);
			doc.add(p5);
			doc.add(empty5);
			doc.add(p6);
			doc.add(empty6);
			doc.add(p7);
			doc.add(empty7);
			doc.add(p8);
			doc.add(empty8);
			doc.add(p9);
			doc.add(empty9);
			doc.add(p10);
			doc.add(p11);
			doc.add(p12);
			doc.add(p13);
			doc.add(empty10);
			doc.close();
			System.out.println("hello");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void create_GeneralEXP_Letter_Pdf(EmpInfo empInfo,
			String filepath) {
		try {
			Document doc = new Document(PageSize.A4);
			Format formater = new SimpleDateFormat("dd-MM-YYYY");
			File newfile = new File(filepath);
			newfile.mkdirs();
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ empInfo.getFirstName() + "_" + "GeneralExpLetter.pdf"));
			PdfWriter.getInstance(doc, file);
			BaseFont bfTimesFoot = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, false);
			Font FooterFont = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont1 = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont2 = new Font(bfTimesFoot, 12, Font.UNDERLINE);
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD,
					new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
			doc.open();
			PdfPTable header = new PdfPTable(3);
			header.getDefaultCell().setBorder(0);
			header.addCell("");
			header.addCell("");
			String headerImageFile = context
					.getRealPath("/resources/images/uitslogo.jpg");
			// String
			// filename="D:\\HRPAYROL\\NEWUSMS\\WebContent\\resources\\images\\uitslogo.jpg";
			Image img = Image.getInstance(headerImageFile);
			img.setAbsolutePosition(300, 300);
			header.addCell(img);
			Paragraph empty0 = new Paragraph();
			addEmptyLine(empty0, 1);
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			PdfPCell cell = new PdfPCell(new Phrase("Ref:HR/2012/01 ",
					FooterFont));

			cell.setVerticalAlignment(2);
			cell.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell1 = new PdfPCell(new Phrase("Dated :"
					+ formater.format(new Date()) + "", bfBold12));
			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell1.setBorder(Rectangle.NO_BORDER);

			table1.addCell(cell);
			table1.addCell(cell1);

			Paragraph empty1 = new Paragraph("");
			addEmptyLine(empty1, 4);

			Paragraph p1 = new Paragraph("TO WHOMSOVER IT MAY CONCERN",
					FooterFont2);
			p1.setAlignment(Element.ALIGN_CENTER);
			// p1.setAlignment(Font.UNDERLINE);
			Paragraph empty2 = new Paragraph("");
			addEmptyLine(empty2, 2);
			Paragraph p2 = new Paragraph("This is to certify that Mr/Miss/Mrs ",
					bf12);
			Chunk ck = new Chunk(empInfo.getFirstName()+" "+empInfo.getLastName(), FooterFont1);
			Chunk ck1 = new Chunk(", holder of  Indian Passport No.", bf12);
			Chunk ck2 = new Chunk(" " + empInfo.getPassportInfos().getPpNo(),
					FooterFont1);
			Chunk ck3 = new Chunk(" has worked at our organization as a "
					+ empInfo.getEmpEmploymentInfos().getEmpDesignation()
					+ " from "
					+ formater.format(empInfo.getEmpEmploymentInfos()
							.getJoiningDt())
					+ " till "
					+ formater.format(empInfo.getEmpEmploymentInfos()
							.getJoiningDt()), bf12);
			p2.add(ck);
			p2.add(ck1);
			p2.add(ck2);
			p2.add(ck3);

			// p2.add("holder of  Indian Passport No.");
			// String ppno=
			// p2.add(,FooterFont1);
			// p2.add(arg0);
			// p2.setAlignment(Element.ALIGN_JUSTIFIED);
			// p2.setAlignment(Element.ALIGN_LEFT);
			// Paragraph pcahnge= new
			// Paragraph("holder of Indian Passport holder No.");
			// Chunk ck1 = new
			// Chunk(empInfo.getPassportInfos().getPpNo(),FooterFont1);
			// pcahnge.add(ck1);
			// Paragraph pchange2= new
			// Paragraph("was employed "+"Software engineer"
			// +"under our sponsorship since"+
			// formater.format(empInfo.getEmpEmploymentInfos().getJoiningDt())+"and working with us till"+formater.format(empInfo.getEmpEmploymentInfos().getJoiningDt()));
			Paragraph empty3 = new Paragraph();
			addEmptyLine(empty3, 1);
			Paragraph p3 = new Paragraph(
					"During the tenure of his service, we found him very hard working and sincere towards his work.",
					bf12);
			Paragraph empty4 = new Paragraph("");
			addEmptyLine(empty4, 3);
			Paragraph p4 = new Paragraph(
					"We wish him very best in his future endeavors.", bf12);
			Paragraph empty5 = new Paragraph("");
			addEmptyLine(empty5, 4);
			Paragraph p5 = new Paragraph("Narender Singh Dahiya ", bfBold12);
			Paragraph empty6 = new Paragraph("");
			addEmptyLine(empty6, 1);
			Paragraph p6 = new Paragraph("Regional Manager ", bfBold12);
			Paragraph p7 = new Paragraph(
					"UNIQUE SOLUTIONS INFORMATION TECHNOLOGY SERVICES ",
					bfBold12);
			Paragraph p8 = new Paragraph("DUBAI ", bfBold12);
			doc.add(header);
			// doc.add(p);
			doc.add(empty0);
			doc.add(table1);
			doc.add(empty1);
			doc.add(p1);
			doc.add(empty2);
			doc.add(p2);
			// doc.add(pcahnge);
			// doc.add(pchange2);
			doc.add(empty3);
			doc.add(p3);
			doc.add(empty4);
			doc.add(p4);
			doc.add(empty5);
			// doc.add(table2);
			doc.add(p5);
			doc.add(empty6);
			doc.add(p6);
			doc.add(p7);
			doc.add(p8);
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void insertCell(PdfPTable table, String text, int align,
			int colspan, Font font, BaseColor color) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		// set the cell alignment
		cell.setHorizontalAlignment(align);
		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setBackgroundColor(color);
		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}

	public static void Create_SalaryCertificate_Pdf(EmpInfo empInfo,
			String filepath) {

		try {
			String Join_Date = null;
			String Last_Date = null;
			Document doc = new Document(PageSize.A4);
			File newfile = new File(filepath);
			newfile.mkdirs();
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ empInfo.getFirstName() + "_" + "SalaryCertificate.pdf"));
			PdfWriter.getInstance(doc, file);
			BaseFont bfTimesFoot = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, false);
			Font FooterFont = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont1 = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont2 = new Font(bfTimesFoot, 12, Font.UNDERLINE);
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD,
					new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
			doc.open();
			PdfPTable header = new PdfPTable(3);
			header.getDefaultCell().setBorder(0);
			header.addCell("");
			header.addCell("");
			String headerImageFile = context
					.getRealPath("/resources/images/uitslogo.jpg");
			// String
			// filename="D:\\HRPAYROL\\NEWUSMS\\WebContent\\resources\\images\\uitslogo.jpg";
			Image img = Image.getInstance(headerImageFile);
			img.setAbsolutePosition(300, 300);
			header.addCell(img);
			Paragraph empty0 = new Paragraph();
			addEmptyLine(empty0, 6);
			Format formatter = new SimpleDateFormat("dd-MM-YYYY");
			if (empInfo.getEmpEmploymentInfos().getJoiningDt() != null)
				Join_Date = formatter.format(empInfo.getEmpEmploymentInfos()
						.getJoiningDt());
			if (empInfo.getEmpEmploymentInfos().getLstWrkDt() != null)
				Last_Date = formatter.format(empInfo.getEmpEmploymentInfos()
						.getLstWrkDt());
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			PdfPCell cell = new PdfPCell(new Phrase("Ref:HR/2012/01 ",
					FooterFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell1 = new PdfPCell(new Phrase("Dated :"
					+ formatter.format(empInfo.getEmpEmploymentInfos()
							.getJoiningDt()), bfBold12));
			cell1.setBorder(Rectangle.NO_BORDER);
			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table1.addCell(cell);
			table1.addCell(cell1);
			Paragraph empty1 = new Paragraph("");
			addEmptyLine(empty1, 4);
			Paragraph p1 = new Paragraph("TO WHOMSOVER IT MAY CONCERN",
					FooterFont2);
			p1.setIndentationLeft(45);
			p1.setAlignment(Element.ALIGN_CENTER);
			Paragraph empty2 = new Paragraph("");
			addEmptyLine(empty2, 6);
			Chunk c = new Chunk("This is to certify that Mr./Miss./Mrs.  ", bf12);
			Chunk c1 = new Chunk("" + empInfo.getFirstName()+" "+empInfo.getLastName(),
					FooterFont);
			Chunk c2 = new Chunk(", of Indian Passport holder No: ", bf12);
			Chunk c3 = new Chunk(empInfo.getPassportInfos().getPpNo(),
					FooterFont);
			// Chunk c4 = new
			// Chunk(",  has worked at our organization  as  a  ‘Computer Engineer’  from"
			// +formatter.format(empSalTrx.getEmpInfo().getEmpEmploymentInfos().getJoiningDt())
			// +"  till "
			// +formatter.format(empSalTrx.getEmpInfo().getEmpEmploymentInfos().getJoiningDt())
			// ,FooterFont1);
			Chunk c4 = new Chunk(" is working with us from ", bf12);
			Chunk c5 = new Chunk(formatter.format(empInfo
					.getEmpEmploymentInfos().getJoiningDt()), FooterFont);
			Chunk c6 = new Chunk(" as ," +empInfo.getEmpEmploymentInfos().getEmpDesignation()+"." ,bf12);

			Paragraph p2 = new Paragraph();
			p2.add(c);
			p2.add(c1);
			p2.add(c2);
			p2.add(c3);
			p2.add(c4);
			p2.add(c5);
			p2.add(c6);
			// p2.add(c7);
			Chunk c11 = new Chunk(
					"He is drawing a consolidated monthly salary of", bf12);
			int netvalue = (int) empInfo.getEmpSalaryInfos().getBasic();
			Chunk c22 = new Chunk(" " + netvalue, bf12);
			String inWords = convertToWords(netvalue);
			Chunk c33 = new Chunk("(" + inWords + " only.)");
			Paragraph p3 = new Paragraph();
			p3.add(c11);
			p3.add(c22);
			p3.add(c33);
			Paragraph empty3 = new Paragraph("");
			addEmptyLine(empty3, 1);
			Paragraph p4 = new Paragraph(
					"This letter has been issued upon request of the employee without any liability on part of company.",
					bf12);
			p4.setAlignment(Element.ALIGN_JUSTIFIED);
			Paragraph empty4 = new Paragraph("");
			addEmptyLine(empty4, 1);
			int netvalue1 = (int) empInfo.getEmpSalaryInfos().getBasic();
			String Inword = convertToWords(netvalue1);
			// Paragraph p5 = new
			// Paragraph("This letter has been issued upon request of the employee without any liability on part of company.",bf12);
			// Paragraph p3 = new Paragraph(
			// "He is drawing a consolidated monthly salary of"+empInfo.getEmpSalaryInfos().getBasic()+" "+Inword
			// );
			Paragraph empty5 = new Paragraph("");
			addEmptyLine(empty5, 5);

			PdfPTable table3 = new PdfPTable(2);
			table3.setWidthPercentage(100);
			PdfPCell cell2 = new PdfPCell(new Phrase("Name: A. R. Chandra Shekar", bfBold12));
			cell2.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
			cell2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell3 = new PdfPCell(new Phrase("CompanySeal ", bfBold12));
			cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell3.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell4 = new PdfPCell(new Phrase("Designation :", bfBold12));
			cell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell4.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell5 = new PdfPCell(new Phrase("", bfBold12));
			cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell5.setBorder(Rectangle.NO_BORDER);
			table3.addCell(cell2);
			table3.addCell(cell3);
			table3.addCell(cell4);
			table3.addCell(cell5);
			Paragraph empty6 = new Paragraph("");
			addEmptyLine(empty6, 1);
			Paragraph p6 = new Paragraph("Signature : ", bfBold12);
			p6.setAlignment(Element.ALIGN_JUSTIFIED);
			doc.add(header);
			doc.add(empty0);
			doc.add(table1);
			doc.add(empty1);
			doc.add(p1);
			doc.add(empty2);
			doc.add(p2);
			doc.add(empty3);
			doc.add(p3);
			doc.add(empty4);
			doc.add(p4);
			doc.add(empty5);
			doc.add(table3);
			doc.add(empty6);
			doc.add(p6);
			// doc.add(p7);
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void create_Final_Settlement_Pdf(EmpInfo empInfo, String path) {
		Document doc = new Document(PageSize.A4);
		File newfile = new File(path);
		newfile.mkdirs();
		try {
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ empInfo.getFirstName() + "_" + "FinalSettlement.pdf"));
			PdfWriter.getInstance(doc, file);
			BaseFont bfTimesFoot = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, false);
			Font FooterFont = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont1 = new Font(bfTimesFoot, 12, Font.BOLD);
			Font FooterFont2 = new Font(bfTimesFoot, 12, Font.UNDERLINE);
			Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD,
					new BaseColor(0, 0, 0));
			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);
			Font bf22 = new Font(FontFamily.TIMES_ROMAN, 9);
			doc.open();
			Paragraph empty = new Paragraph("");
			addEmptyLine(empty, 5);
			PdfPTable main_table = new PdfPTable(1);

			Paragraph p0 = new Paragraph(new Phrase(
					"Final Settlement Of Service", FooterFont1));
			p0.setAlignment(Element.ALIGN_CENTER);
			Paragraph empty0 = new Paragraph("");
			addEmptyLine(empty0, 1);
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(95);
			PdfPTable netsted0 = new PdfPTable(1);
			Date System_date = new Date();

			Format formatter = new SimpleDateFormat("dd-MM-YYYY");
			String current_date = formatter.format(System_date);
			String Join_Date = formatter.format(empInfo.getEmpEmploymentInfos()
					.getJoiningDt());

			netsted0.getDefaultCell().setBorder(0);
			netsted0.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
			netsted0.addCell(new Phrase("   Date:" + current_date, FooterFont1));

			netsted0.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
			netsted0.addCell(new Phrase("To:Mr." + empInfo.getFirstName()+" "+empInfo.getLastName(),
					FooterFont1));

			PdfPCell nesthousing0 = new PdfPCell(netsted0);
			nesthousing0.setColspan(2);

			nesthousing0.setBackgroundColor(BaseColor.GRAY);
			table1.addCell(nesthousing0);
			insertCell(table1, "Start Date of work", Element.ALIGN_LEFT, 1,
					bf12, BaseColor.LIGHT_GRAY);
			PdfPCell salcel2 = new PdfPCell();
			salcel2.setPhrase(new Phrase(Join_Date, bf12));
			salcel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table1.addCell(salcel2);
			insertCell(table1, "Last Date of work", Element.ALIGN_LEFT, 1,
					bf12, BaseColor.LIGHT_GRAY);
			PdfPCell salcel3 = new PdfPCell();
			salcel3.setPhrase(new Phrase(Join_Date, bf12));
			salcel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table1.addCell(salcel3);
			insertCell(table1, "Last Salary", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell salcel4 = new PdfPCell();
			salcel4.setPhrase(new Phrase(empInfo.getEmpSalaryInfos().getBasic()));
			salcel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table1.addCell(salcel4);
			PdfPTable nested = new PdfPTable(1);
			PdfPTable nested2 = new PdfPTable(1);
			nested.getDefaultCell().setBorder(0);
			nested2.getDefaultCell().setBorder(0);
			nested.addCell(new Phrase("Number of working days in 2011", bf12));
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
			Date startDate = dateFormat.parse(Join_Date);
			String last = "12-12-2013";
			Date endDate = dateFormat.parse(last);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(dateFormat.parse(last));
			cal1.set(cal1.YEAR, 2013);
			int test = cal1.get(cal1.YEAR);
			int date = cal1.get(cal1.DATE);
			Calendar cal2 = Calendar.getInstance();
			int tested = cal2.get(cal2.YEAR);
			int value1 = (int) (endDate.getTime() / (24 * 60 * 60 * 1000));
			int value2 = (int) (startDate.getTime() / (24 * 60 * 60 * 1000));
			int Totaldays = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));

			nested2.addCell(new Phrase(empInfo.getFirstName() +" "+empInfo.getLastName(), bf12));
			nested.addCell(new Phrase("Number of working days in 2012", bf12));

			nested2.addCell(new Phrase(Integer.toString(value2), bf12));
			nested.addCell(new Phrase("Number of working days in 2013", bf12));
			nested2.addCell(new Phrase(Integer.toString(value1), bf12));
			PdfPCell nesthousing = new PdfPCell(nested);
			nesthousing.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table1.addCell(nesthousing);
			PdfPCell bottom = new PdfPCell(nested2);
			bottom.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table1.addCell(bottom);
			insertCell(table1, "Total Days in Service", Element.ALIGN_LEFT, 1,
					bf12, BaseColor.LIGHT_GRAY);
			PdfPCell nesthousing1 = new PdfPCell();
			nesthousing1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			nesthousing1.setPhrase(new Phrase(Integer.toString(Totaldays)));
			System.out.println("hello test");
			table1.addCell(nesthousing1);
			Paragraph empty1 = new Paragraph("");
			addEmptyLine(empty1, 1);
			PdfPTable Tbale2 = new PdfPTable(2);
			Tbale2.setWidthPercentage(95);
			PdfPTable test2 = new PdfPTable(1);
			test2.getDefaultCell().setBorder(0);
			test2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			test2.addCell(new Phrase("Gratuity Period", FooterFont1));
			test2.addCell(new Phrase("Eligible Days  ", bf12));
			test2.addCell(new Phrase(
					"(14 days per year based on service completion of 2 years)",
					bf12));
			PdfPCell test3 = new PdfPCell(test2);
			test3.setBackgroundColor(BaseColor.GRAY);
			test3.setColspan(2);
			Tbale2.addCell(test3);
			insertCell(Tbale2, "For 2011", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell Table2cell = new PdfPCell();
			Table2cell.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Tbale2.addCell(Table2cell);
			insertCell(Tbale2, "For 2012", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell Table2cel2 = new PdfPCell();
			Table2cel2.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Tbale2.addCell(Table2cel2);
			insertCell(Tbale2, "For 2013", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell Table2cel3 = new PdfPCell();
			Table2cel3.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Tbale2.addCell(Table2cel3);
			insertCell(Tbale2, "Total Days", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell Table2cel4 = new PdfPCell();
			Table2cel4.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Tbale2.addCell(Table2cel4);
			Paragraph empty2 = new Paragraph("");
			addEmptyLine(empty2, 1);
			PdfPTable table3 = new PdfPTable(2);
			table3.setWidthPercentage(95);
			PdfPTable test4 = new PdfPTable(1);
			test4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			test4.addCell(new Phrase("Payment", FooterFont1));
			PdfPCell test5 = new PdfPCell(test4);
			test5.setBackgroundColor(BaseColor.GRAY);
			test5.setColspan(2);
			table3.addCell(test5);
			PdfPTable test6 = new PdfPTable(1);
			test6.getDefaultCell().setBorder(0);
			test6.addCell(new Phrase("Gratuity for 33 Days", bf12));
			test6.addCell(new Phrase("(Basic Dhs. 7,200/- per month)", bf12));
			PdfPCell table3cel1 = new PdfPCell(test6);
			table3cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			PdfPCell table3cel2 = new PdfPCell();
			table3cel2.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			table3cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table3.addCell(table3cel1);
			table3.addCell(table3cel2);
			insertCell(table3, "Salary May 2013  for 9 days   ",
					Element.ALIGN_LEFT, 1, bf12, BaseColor.LIGHT_GRAY);
			PdfPCell table2cel3 = new PdfPCell();
			table2cel3.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			table2cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table3.addCell(table2cel3);
			insertCell(table3, "AirTicket", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell table2cel4 = new PdfPCell();
			Table2cel4.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table3.addCell(Table2cel4);
			insertCell(table3, "Total Amount", Element.ALIGN_LEFT, 1, bf12,
					BaseColor.LIGHT_GRAY);
			PdfPCell Table2cel5 = new PdfPCell();
			Table2cel5.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table3.addCell(Table2cel5);
			Table2cel4.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			Table2cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			Tbale2.addCell(Table2cel4);
			insertCell(table3, "Admin and visa expenses (2050+330+270+480) ",
					Element.ALIGN_LEFT, 1, bf12, BaseColor.LIGHT_GRAY);
			PdfPCell table4cell = new PdfPCell();

			table4cell.setBackgroundColor(BaseColor.LIGHT_GRAY);

			table4cell.setPhrase(new Phrase(empInfo.getEmpSalTrxs().get(0)
					.getSalTrxMonth(), bf12));
			table3.addCell(table4cell);
			PdfPTable nest = new PdfPTable(1);

			PdfPTable nest1 = new PdfPTable(1);
			nest.getDefaultCell().setBorder(0);
			nest1.getDefaultCell().setBorder(0);
			nest.addCell(new Phrase("Net Payable", bf12));
			int netvalue = (int) empInfo.getEmpSalaryInfos().getBasic();
			nest1.addCell(new Phrase(Integer.toString(netvalue), bf12));
			// ------------Test-------

			String inWords = convertToWords(netvalue);
			// -----------------test ----------------------
			nest.addCell(new Phrase("In words", bf12));
			nest1.addCell(new Phrase(inWords, bf12));
			PdfPCell nesthousing3 = new PdfPCell(nest);
			nesthousing3.setBackgroundColor(BaseColor.LIGHT_GRAY);

			PdfPCell nesthousing4 = new PdfPCell(nest1);

			nesthousing4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table3.addCell(nesthousing3);
			table3.addCell(nesthousing4);
			table3.addCell(nest1);

			Paragraph empty3 = new Paragraph("");
			addEmptyLine(empty3, 1);
			Paragraph P = new Paragraph(
					new Chunk(
							"I declare that I have received Full and Final Settlement of my Services with Unique Solutions IT Services,Dubai.",
							bf22));
			P.setAlignment(Element.ALIGN_CENTER);
			Paragraph empty4 = new Paragraph("");
			addEmptyLine(empty4, 2);
			PdfPTable table4 = new PdfPTable(2);
			table4.getDefaultCell().setBorder(0);
			PdfPCell cell = new PdfPCell(new Phrase("Mr."
					+ empInfo.getFirstName() +" "+empInfo.getLastName(), bf12));
			cell.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell1 = new PdfPCell(new Phrase("   ", bf12));
			cell1.setBorder(Rectangle.NO_BORDER);

			PdfPCell cell2 = new PdfPCell(new Phrase("-----------", bf12));
			cell2.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell3 = new PdfPCell(new Phrase(" -----------", bf12));
			cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell3.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell4 = new PdfPCell(new Phrase("(Name)", bf12));

			cell4.setBorder(Rectangle.NO_BORDER);
			PdfPCell cell5 = new PdfPCell(new Phrase("(Signature)", bf12));
			cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell5.setBorder(Rectangle.NO_BORDER);
			table4.addCell(cell);
			table4.addCell(cell1);
			table4.addCell(cell2);
			table4.addCell(cell3);
			table4.addCell(cell4);
			table4.addCell(cell5);
			Paragraph p1 = new Paragraph(
					new Phrase(
							"                .........................................................................................................................",
							FooterFont));
			main_table.setWidthPercentage(100);

			PdfPCell maincell = new PdfPCell(main_table);

			maincell.addElement(p0);
			maincell.addElement(empty0);
			maincell.addElement(table1);
			maincell.addElement(empty1);
			maincell.addElement(Tbale2);
			maincell.addElement(empty2);
			maincell.addElement(table3);
			maincell.addElement(empty3);

			main_table.addCell(maincell);

			PdfPTable lasttable = new PdfPTable(1);
			PdfPCell lastcell = new PdfPCell(main_table);

			lasttable.addCell(lastcell);

			doc.add(empty0);
			doc.add(lasttable);

			doc.add(P);
			doc.add(empty4);
			doc.add(table4);
			doc.add(p1);
			doc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void create_WPS_Pdf(List<EmpSalTrx> empSalList, String path,
			int month, int year, EmployerDetail employerDetail, String sMonth) {
		File newfile = new File(path);
		newfile.mkdirs();
		float total = 0;
		try {
			PrintWriter writer = new PrintWriter(new File(newfile + "/"
					+ sMonth + "_" + "WPS.sif"));
			Format formatter = new SimpleDateFormat("dd-MM-YYYY");
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

			Calendar cal = GregorianCalendar.getInstance(Locale.US);
			cal.getTime();
			for (EmpSalTrx salInfo : empSalList) {
				writer.print("EDR,");
				writer.write(String.format("%014d",
						(salInfo.getEmpInfo().getEmpNo()))
						+ ",");
				writer.write(salInfo.getEmpInfo().getEmpBankInfos()
						.getCbrCode()
						+ ",");
				writer.write(salInfo.getEmpInfo().getEmpBankInfos().getIbanNo()
						+ ",");
				writer.write(formatter.format(salInfo.getFromDt()) + ",");
				writer.write(formatter.format(salInfo.getToDate()) + ",");
				writer.write(Integer
						.toString((int) ((salInfo.getToDate().getTime() - salInfo
								.getFromDt().getTime()) / (1000 * 60 * 60 * 24)))
						+ ",");
				writer.write(Integer.toString((int) (salInfo.getTotalAmt()))
						+ ",");
				writer.write(Integer.toString(0) + ",");
				writer.write(Integer.toString(0));
				writer.println();
				total = total + salInfo.getTotalAmt();
			}
			writer.print("SCR,");
			writer.print(employerDetail.getEmployerId() + ",");
			writer.print(employerDetail.getCbrCode() + ",");
			writer.print(formatter.format(new Date()) + ",");
			writer.print(sdf.format(cal.getTime()) + ",");
			writer.print(String.format("%02d", month + 1)
					+ Integer.toString(year) + ",");
			writer.print(Integer.toString(empSalList.size()) + ",");
			writer.print(Integer.toString((int) total) + ",");
			writer.print("AED,");
			writer.print("UNIQUE SOLUTIONS INFORMATION TECH");

			writer.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

			e.printStackTrace();

		}
	}

	public static void beneficiary_Detail_Pdf(List<EmpInfo> empList, String path) {
		File newfile = new File(path);
		newfile.mkdirs();
		Document document = new Document(PageSize.A4);
		Format formatter = new SimpleDateFormat("dd-MM-YYYY");
		Date now = new Date();
		float total = 0;
		int i = 1;
		try {
			OutputStream file = new FileOutputStream(new File(newfile + "/"
					+ "BeneficiaryDetail.pdf"));
			PdfWriter.getInstance(document, file);
			document.open();
			Paragraph pDate = new Paragraph("Date:  " + formatter.format(now),
					FontFactory.getFont(FontFactory.HELVETICA, 8));
			Paragraph rDate = new Paragraph("Ref:   HR/2013/296",
					FontFactory.getFont(FontFactory.HELVETICA, 8));
			pDate.setIndentationLeft(430);
			rDate.setIndentationLeft(430);
			document.add(pDate);

			document.add(Chunk.NEWLINE);
			document.add(rDate);

			document.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(new float[] { 1, 2, 2, 1, 1, 1 });
			table.setWidthPercentage(100f);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			Font f = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
			f.setColor(BaseColor.WHITE);
			PdfPCell cell = new PdfPCell(
					new Phrase(
							"UNIQUE SOLUTION INFORMATION TECHNOLOGY SERVICES-Beneficiary Detail",
							f));
			cell.setBackgroundColor(BaseColor.BLACK);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(6);
			table.addCell(cell);
			table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(new Phrase("SI", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Beneficiary Name", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Beneficiary Account Number", FontFactory
					.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Beneficiary Bank", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("MOL ID", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Salary", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));

			table.getDefaultCell().setBackgroundColor(null);

			table.setHeaderRows(2);

			for (EmpInfo empInfo : empList) {

				table.addCell(new Phrase(Integer.toString(i), FontFactory
						.getFont(FontFactory.HELVETICA, 10)));

				table.addCell(new Phrase(empInfo.getFirstName(), FontFactory
						.getFont(FontFactory.HELVETICA, 10)));
				table.addCell(new Phrase(
						empInfo.getEmpBankInfos().getCbrCode(), FontFactory
								.getFont(FontFactory.HELVETICA, 10)));
				table.addCell(new Phrase(empInfo.getEmpBankInfos()
						.getBankName(), FontFactory.getFont(
						FontFactory.HELVETICA, 10)));
				table.addCell(new Phrase(empInfo.getEIdInfos().geteIdNo(),
						FontFactory.getFont(FontFactory.HELVETICA, 10)));
				table.addCell(new Phrase(Float.toString(empInfo
						.getEmpSalaryInfos().getTotal()), FontFactory.getFont(
						FontFactory.HELVETICA, 10)));
				total = total + empInfo.getEmpSalaryInfos().getTotal();
				i = i + 1;
			}
			PdfPCell blankcell = new PdfPCell();
			blankcell.setColspan(4);
			table.addCell(blankcell);
			table.addCell(new Phrase("Total", FontFactory.getFont(
					FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase(Float.toString(total), FontFactory
					.getFont(FontFactory.HELVETICA, 10)));

			document.add(table);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph p1 = new Paragraph("Authorized Signature",
					FontFactory.getFont(FontFactory.HELVETICA, 10));
			Paragraph p2 = new Paragraph("Name:", FontFactory.getFont(
					FontFactory.HELVETICA, 10));
			// p1.setIndentationLeft(5);
			// p2.setIndentationLeft(5);
			document.add(p1);
			document.add(Chunk.NEWLINE);
			document.add(p2);
			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
	}

	private static String convertToWords(int n) {
		amount = numToString(n);
		String converted = "";
		int pos = 1;
		boolean hun = false;
		while (amount.length() > 0) {
			if (pos == 1) // TENS AND UNIT POSITION
			{
				if (amount.length() >= 2) // 2DIGIT NUMBERS
				{
					String C = amount.substring(amount.length() - 2,
							amount.length());
					amount = amount.substring(0, amount.length() - 2);
					converted += digits(C);
				} else if (amount.length() == 1) // 1 DIGIT NUMBER
				{
					converted += digits(amount);
					amount = "";
				}
				pos++; // INCREASING POSITION COUNTER
			} else if (pos == 2) // HUNDRED POSITION
			{
				String C = amount.substring(amount.length() - 1,
						amount.length());
				amount = amount.substring(0, amount.length() - 1);
				if (converted.length() > 0 && digits(C) != "") {
					converted = (digits(C) + maxs[pos] + " and") + converted;
					hun = true;
				} else {
					if (digits(C) == "")
						;
					else
						converted = (digits(C) + maxs[pos]) + converted;
					hun = true;
				}
				pos++; // INCREASING POSITION COUNTER
			} else if (pos > 2) // REMAINING NUMBERS PAIRED BY TWO
			{
				if (amount.length() >= 2) // EXTRACT 2 DIGITS
				{
					String C = amount.substring(amount.length() - 2,
							amount.length());
					amount = amount.substring(0, amount.length() - 2);
					if (!hun && converted.length() > 0)
						converted = digits(C) + maxs[pos] + " and" + converted;
					else {
						if (digits(C) == "")
							;
						else
							converted = digits(C) + maxs[pos] + converted;
					}
				} else if (amount.length() == 1) // EXTRACT 1 DIGIT
				{
					if (!hun && converted.length() > 0)
						converted = digits(amount) + maxs[pos] + " and"
								+ converted;
					else {
						if (digits(amount) == "")
							;
						else
							converted = digits(amount) + maxs[pos] + converted;
						amount = "";
					}
				}
				pos++; // INCREASING POSITION COUNTER
			}
		}
		return converted;

	}

	private static String digits(String C) // TO RETURN SELECTED NUMBERS IN
											// WORDS
	{
		String converted = "";
		for (int i = C.length() - 1; i >= 0; i--) {
			int ch = C.charAt(i) - 48;
			if (i == 0 && ch > 1 && C.length() > 1)
				converted = tens[ch - 2] + converted; // IF TENS DIGIT STARTS
														// WITH 2 OR MORE IT
														// FALLS UNDER TENS
			else if (i == 0 && ch == 1 && C.length() == 2) // IF TENS DIGIT
															// STARTS WITH 1 IT
															// FALLS UNDER TEENS
			{
				int sum = 0;
				for (int j = 0; j < 2; j++)
					sum = (sum * 10) + (C.charAt(j) - 48);
				return teen[sum - 10];
			} else {
				if (ch > 0)
					converted = units[ch] + converted;
			} // IF SINGLE DIGIT PROVIDED
		}
		return converted;
	}

	private static String numToString(int n) // CONVERT THE NUMBER TO STRING
	{
		String num = "";
		while (n != 0) {
			num = ((char) ((n % 10) + 48)) + num;
			n /= 10;
		}
		return num;
	}

}
