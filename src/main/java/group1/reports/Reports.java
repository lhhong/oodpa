package group1.reports;

import group1.invoice.Invoice;
import group1.menu.FoodItem;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by low on 8/11/16 5:37 PM.
 */
public class Reports implements Serializable {
	private HashMap<LocalDate, DailyReport> dailyReports;

	public Reports() {
	}

	/**
	 * Constructor solely for MockData generation
	 * @param dailyReports
	 */
	public Reports(HashMap<LocalDate, DailyReport> dailyReports) {
		this.dailyReports = dailyReports;
	}

	public void addInvoice(Invoice invoice) {
		LocalDate invoiceDate = invoice.getDateTime().toLocalDate();
		Set<LocalDate> dateSet = dailyReports.keySet();
		for (LocalDate date: dateSet) {
			if (invoiceDate.isEqual(date)) {
				dailyReports.get(date).addOrder(invoice.getOrder());
				return;
			}
		}
		DailyReport newReport = new DailyReport();
		newReport.addOrder(invoice.getOrder());
		dailyReports.put(invoiceDate, newReport);
	}

	private DailyReport generateMonthlyReport(int year, int month) {
		YearMonth ym = YearMonth.of(year, month);
		DailyReport report = new DailyReport();
		for (LocalDate date : dailyReports.keySet()) {
			if (YearMonth.from(date).equals(ym)) {
				report.collapse(dailyReports.get(date));
			}
		}
		return report;

	}

	public String reportPrintString(int year, int month) {
		String s = "Report for " + YearMonth.of(year, month).toString();
		s += reportPrintString(generateMonthlyReport(year, month));
		return s;
	}

	private String reportPrintString(DailyReport dailyReport) {
		String s = "\n----------------------------------\n" +
				"Food Items Sold\tUnitPrice\tQuantity\tTotal Price\n";
		HashMap<FoodItem, FoodReport> itemQuantities = dailyReport.getItemQuantities();
		for (FoodItem i : itemQuantities.keySet()) {
			s += i.getName() + "\t$";
			s += i.getPrice() + "\t";
			s += itemQuantities.get(i).getQuantity() + "\t$";
			s += itemQuantities.get(i).getSales() + "\n";
		}
		s += "\nTotal Sale: $" + dailyReport.getTotalSales();
		return s;
	}

	public String reportPrintString(LocalDate date) {
		String s  = "Report for the day " + date.format(DateTimeFormatter.ISO_DATE);
		for (LocalDate savedDate : dailyReports.keySet()) {
			if (savedDate.isEqual(date)) {
				s += reportPrintString(dailyReports.get(savedDate));
				return s;
			}
		}
		return null;
	}
}
