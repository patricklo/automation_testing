package com.ibm.automation.demoautomation.pages.uims.portal;



import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.automation.demoautomation.core.AbstractPage;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;

@Component
public class PaymentsandTransfersPage extends AbstractPage {
	@Autowired
	private WebDriverWrapper webDriver;
	
	
	public boolean createPaymentInstruction() throws InterruptedException{
		
		webDriver.getObjectById("Payment").click();
		//<li id="Lnk_2198075">
		//<a href="/uims/portal/HSBCnet/Payment;jsessionid=0000qVgrqzBaYgWnqVOzbd1KvrP:14ak8djk8#H_2198075">Create Payment Instruction</a>
		webDriver.getObjectById("Lnk_2198075").click();
		
		return true;
	}
	

}
