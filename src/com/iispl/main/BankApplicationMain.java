package com.iispl.main;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.iispl.connectionpool.ConnectionPool;
import com.iispl.jasper.JasperReportRunner;

public class BankApplicationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Connection connection = null;

        try {

            // 1. Get database connection
        	 connection = ConnectionPool.getDataSource().getConnection();

            // 2. Create JasperReportRunner object
            JasperReportRunner runner = new JasperReportRunner();

            // 3. Create parameter map
            Map<String, Object> parameters = new HashMap<>();

            System.out.println("======================================");
            System.out.println("       CTS JASPER REPORT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Daily Cheque Report");
            System.out.println("2. Rejected Cheque Report");
            System.out.println("3. MICR Repair Report");
            System.out.println("4. High Value Cheque Report");
            System.out.println("5. Batch Summary Report");
            System.out.println("6. Bank Summary Report");
            System.out.println("7. CTS Daily Operations Dashboard");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                	
                	parameters.put("P_PROCESSING_DATE", Date.valueOf("2026-08-13"));
                    runner.generatePdfReport(
                            "reports/daily_cheque_report.jrxml",
                            "output/daily_cheque_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "Daily Cheque Report generated successfully."
                    );

                    break;

                case 2:

                    System.out.print("Enter status: ");

                    scanner.nextLine(); // consume leftover newline

                    String status = scanner.nextLine();

                    parameters.put("P_STATUS", status);

                    runner.generatePdfReport(
                            "reports/rejected_cheque_report.jrxml",
                            "output/rejected_cheque_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "Rejected Cheque Report generated successfully."
                    );

                    break;

                case 3:
                	System.out.print("Enter MICR status: ");
                	
                	scanner.nextLine();
                	
                	String micr_status = scanner.nextLine();
                	
                	parameters.put("P_MICR_STATUS", micr_status);
                	
                    runner.generatePdfReport(
                            "reports/micr_repair_report.jrxml",
                            "output/micr_repair_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "MICR Repair Report generated successfully."
                    );

                    break;

    

                case 4:

                    System.out.print("Enter minimum amount: ");

                    BigDecimal minAmount = scanner.nextBigDecimal();

                    parameters.put("P_MIN_AMOUNT", minAmount);

                    runner.generatePdfReport(
                            "reports/high_value_report.jrxml",
                            "output/high_value_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "High Value Cheque Report generated successfully."
                    );

                    break;


                case 5:

                    runner.generatePdfReport(
                            "reports/batch_summary_report.jrxml",
                            "output/batch_summary_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "Batch Summary Report generated successfully."
                    );

                    break;

                case 6:

                    runner.generatePdfReport(
                            "reports/bank_summary_report.jrxml",
                            "output/bank_summary_report.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "Bank Summary Report generated successfully."
                    );

                    break;

                case 7:
                	System.out.print("Enter processing date (yyyy-MM-dd): ");

                    String date = scanner.nextLine();

                    LocalDate processingDate = LocalDate.parse(date);
                	
                	parameters.put("P_PROCESSING__DAILY_DATE", date);
                	
                    runner.generatePdfReport(
                            "reports/cts_daily_operations_dashboard.jrxml",
                            "output/CTS_Daily_Operations_Dashboard.pdf",
                            parameters,
                            connection
                    );

                    System.out.println(
                            "CTS Daily Operations Dashboard generated successfully."
                    );

                    break;

                case 8:

                    System.out.println("Exiting application...");
                    break;

                default:

                    System.out.println("Invalid choice.");

            }

        } catch (Exception e) {

            System.out.println("Error while generating report.");
            e.printStackTrace();

        } finally {

            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            scanner.close();
        }
    }
}