package com.iispl.jasper;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportRunner {

	public JasperReport compileReport(String jrxmlPath) throws JRException {

		return JasperCompileManager.compileReport(jrxmlPath);

	}

	public JasperPrint fillReport(JasperReport report, Map<String, Object> parameters, Connection connection)
			throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
		return jasperPrint;

	}

	public void exportToPdf(JasperPrint jasperPrint, String outputPath) throws JRException {

		JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
	}

	public void generatePdfReport(String jrxmlPath, String outputPath, Map<String, Object> parameters,
			Connection connection) throws JRException {
//		
//		File file = new File("reports/daily_cheque_report.jrxml");
//
//		System.out.println("Absolute path: " + file.getAbsolutePath());
//		System.out.println("File exists: " + file.exists());

		JasperReport report = compileReport(jrxmlPath);

		JasperPrint jasperPrint = fillReport(report, parameters, connection);

		exportToPdf(jasperPrint, outputPath);
	}

}
