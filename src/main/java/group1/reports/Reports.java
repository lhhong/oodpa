package group1.reports;

import group1.invoice.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by low on 8/11/16 5:37 PM.
 */
public class Reports {
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
}
