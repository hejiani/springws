package no.nki.springws.server.service;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import no.nki.springws.server.domain.Admin;
import no.nki.springws.server.domain.impl.AdminImpl;
import no.nki.springws.server.service.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceTest extends AbstractWebServiceTest {

	private static Logger logger = LoggerFactory.getLogger(HelloServiceTest.class);

	@Autowired
	private HelloService helloService;

	private Admin admin0;

	protected void setAdminService(HelloService adminService) {
		this.helloService = adminService;
	}

	public HelloServiceTest() {
	}

	@Before
	public void beforeAnyTest() throws Exception {
		admin0 = new AdminImpl("ste@nki.no", "Stephane", "Eybert", "stephane", "mypass");
	}

	@Test
	public void testSendText() {
		String text = "A little web service";
		assertEquals(text, helloService.sendText(text));
	}

	@Test
	public void testSendTextAdmin() {
		String text = "Hello ";
		assertEquals(text + admin0.getFirstname() + " " + admin0.getLastname(), helloService.sendTextToAdmin(text, admin0));
	}

	@Test
	public void testgetAdmin() {
		String email = "mittiprovence@yhoo.se";
		Admin retrievedAdmin = helloService.getAdmin(email);
		assertEquals(email, retrievedAdmin.getEmail());
	}
	
	@Test
	public void testMakeBusinessException() {
		try {
			helloService.makeBusinessException();
		} catch (BusinessException e) {
			logger.debug("==============>>> Business exception: " + e.getMessage() + " " + e.getCause());
			return;
		}
		fail("The expected business exception was not thrown.");
	}
	
}