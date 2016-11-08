package group1.reports;

import group1.commons.Money;
import group1.invoice.Invoice;
import group1.menu.FoodItem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

/**
 * Main class concerning revenue report
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */
public class Reports implements Serializable {
	private HashMap<LocalDate, CompiledReport> dailyReports;

	/**
	 * Constructor solely for MockData generation
	 * @param dailyReports hash map of data
	 */
	public Reports(HashMap<LocalDate, CompiledReport> dailyReports) {
		this.dailyReports = dailyReports;
	}

	/**
	 * adds the invoice to reports
	 * @param invoice invoice generated
	 */
	public void addInvoice(Invoice invoice) {
		LocalDate invoiceDate = invoice.getDateTime().toLocalDate();
		Set<LocalDate> dateSet = dailyReports.keySet();
		for (LocalDate date: dateSet) {
			if (invoiceDate.isEqual(date)) {
				dailyReports.get(date).addOrder(invoice.getOrder());
				return;
			}
		}
		CompiledReport newReport = new CompiledReport();
		newReport.addOrder(invoice.getOrder());
		dailyReports.put(invoiceDate, newReport);
	}

	/**
	 * generates monthly report
	 * @param year year of report
	 * @param month month of report
	 * @return monthly report
	 */
	private CompiledReport generateMonthlyReport(int year, int month) {
		YearMonth ym = YearMonth.of(year, month);
		CompiledReport report = new CompiledReport();
		for (LocalDate date : dailyReports.keySet()) {
			if (YearMonth.from(date).equals(ym)) {
				report.collapse(dailyReports.get(date));
			}
		}
		return report;

	}

	/**
	 * prints report by month
	 * @param year year of report
	 * @param month month of report
	 */
	public void printReport(int year, int month) {
		String s = "Report for " + YearMonth.of(year, month).toString();
		s += reportPrintString(generateMonthlyReport(year, month));
		System.out.println(s);
	}

	/**
	 * creates print string from compiled reports
	 * @param compiledReport report to print
	 * @return printString of report
	 */
	private String reportPrintString(CompiledReport compiledReport) {
		String s = "\n----------------------------------\n" +
				"Food Items Sold\tUnitPrice\tQuantity\tTotal Price\n";
		HashMap<FoodItem, FoodReport> itemQuantities = compiledReport.getItemQuantities();
		for (FoodItem i : itemQuantities.keySet()) {
			s += i.getName() + "\t";
			s += Money.toString(i.getPrice()) + "\t";
			s += itemQuantities.get(i).getQuantity() + "\t";
			s += Money.toString(itemQuantities.get(i).getSales()) + "\n";
		}
		s += "\nTotal Sale: " + Money.toString(compiledReport.getTotalSales());
		return s;
	}

	/**
	 * prints report by day
	 * @param date date of report
	 */
	public void printReport(LocalDate date) {
		String s  = "Report for the day " + date.format(DateTimeFormatter.ISO_DATE);
		for (LocalDate savedDate : dailyReports.keySet()) {
			if (savedDate.isEqual(date)) {
				s += reportPrintString(dailyReports.get(savedDate));
				System.out.println(s);
			}
		}
		System.out.println("No report available on this day");
	}
}
