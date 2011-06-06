package com.capgemini.test.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.capgemini.test.dao.CustomerDAO;
import com.capgemini.test.dao.ICustomerDAO;
import com.capgemini.test.domain.BuildType;
import com.capgemini.test.domain.ConsumptionRecord;
import com.capgemini.test.domain.Customer;
import com.capgemini.test.domain.Provider;
import com.capgemini.test.util.Util;

public class ServletContextListener implements
		javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ICustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		
		Customer customer = new Customer();
		customer.setBuildType(BuildType.APPARTMENT);
		customer.setUserid("jan");
		
		customer.setFirstName("Jan");
		customer.setName("Verhulst");
		customer.setEmail("verhulstjan@gmail.com");
		customer.setPassword(Util.encrypt("test"));
		customer.setCook(Provider.ELEC);
		customer.setEndDateElec(new Date());
		
		List<ConsumptionRecord> records = new ArrayList<ConsumptionRecord>();
		addRecord(records, 2011, Calendar.JANUARY, 2434f, 1222f);
		addRecord(records, 2011, Calendar.FEBRUARY, 2525f, 1322f);
		addRecord(records, 2011, Calendar.MARCH, 2788f, 1422f);
		addRecord(records, 2011, Calendar.APRIL, 2998f, 1522f);
		addRecord(records, 2011, Calendar.MAY, 3170f, 1688f);
		addRecord(records, 2011, Calendar.JUNE, 3300f, 1756f);
		addRecord(records, 2010, Calendar.JULY, 1162f, 619f);
		addRecord(records, 2010, Calendar.AUGUST, 1223f, 712f);
		addRecord(records, 2010, Calendar.SEPTEMBER, 1428f, 805f);
		addRecord(records, 2010, Calendar.OCTOBER, 1740f, 989f);
		addRecord(records, 2010, Calendar.NOVEMBER, 2078f, 1023f);
		addRecord(records, 2010, Calendar.DECEMBER, 2290f, 1187f);
		customer.setExpectedConsumptions(records);
		
		
		records = new ArrayList<ConsumptionRecord>();
		addRecord(records, 2010, Calendar.DECEMBER, 2590f, 1587f);
		addRecord(records, 2010, Calendar.NOVEMBER, 2378f, 1420f);
		addRecord(records, 2010, Calendar.OCTOBER, 2090f, 1310f);
		addRecord(records, 2010, Calendar.SEPTEMBER, 1928f, 1175f);
		addRecord(records, 2010, Calendar.AUGUST, 1623f, 922f);
		addRecord(records, 2010, Calendar.JULY, 1362f, 719f);
		addRecord(records, 2010, Calendar.JUNE, 1200f, 616f);
		addRecord(records, 2010, Calendar.MAY, 1170f, 588f);
		addRecord(records, 2010, Calendar.APRIL, 998f, 422f);
		addRecord(records, 2010, Calendar.MARCH, 788f, 322f);
		addRecord(records, 2010, Calendar.FEBRUARY, 525f, 222f);
		addRecord(records, 2010, Calendar.JANUARY, 234f, 122f);
		customer.setHistoricalConsumptions(records);
		
		customerDAO.save(customer);

	}
	
	
	private void addRecord(List<ConsumptionRecord> records, int year, int month, float peak, float offpeak){
		ConsumptionRecord record = new ConsumptionRecord();
		record.setPeak(peak);
		record.setPeak(offpeak);
		record.setDate(new Date(year - 1900, month, 1));
		records.add(record);
	}
	
	

}
