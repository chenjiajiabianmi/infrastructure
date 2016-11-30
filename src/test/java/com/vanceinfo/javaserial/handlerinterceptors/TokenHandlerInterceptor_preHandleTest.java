package com.vanceinfo.javaserial.handlerinterceptors;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

@RunWith(Parameterized.class)
public class TokenHandlerInterceptor_preHandleTest {

	private static final Logger LOGGER = Logger.getLogger(TokenHandlerInterceptor_preHandleTest.class);

	private String testName;
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private boolean returnBoolean;

	public TokenHandlerInterceptor_preHandleTest(String testName, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, Boolean returnBoolean) {
		this.testName = testName;
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
		this.returnBoolean = returnBoolean;
	}

	@Parameters
	public static Collection<Object[]> testData() throws Exception {
		// *******************
		// test case 1
		// *******************
		String testNameTC1 = "Get Method";
		MockHttpServletRequest httpRequest1 = new MockHttpServletRequest("get", "http://testRequestUrl");
		httpRequest1.addParameter("htid", "1001");

		MockHttpServletResponse httpResponse1 = new MockHttpServletResponse();

		// *******************
		// test case 2
		// *******************
		String testNameTC2 = "Post Method unvalid: no param(client) token";
		MockHttpServletRequest httpRequest2 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest2.addParameter("htid", "1001");

		MockHttpServletResponse httpResponse2 = new MockHttpServletResponse();

		// *******************
		// test case 3
		// *******************
		String testNameTC3 = "Post Method unvalid: has param(client) token but is null";
		MockHttpServletRequest httpRequest3 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest3.addParameter("htid", "1001");
		httpRequest3.addParameter("springMVC_token", new String[] {});

		MockHttpServletResponse httpResponse3 = new MockHttpServletResponse();

		// *******************
		// test case 4
		// *******************
		String testNameTC4 = "Post Method unvalid: has param(client) token but no session token";
		MockHttpServletRequest httpRequest4 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest4.addParameter("htid", "1001");
		httpRequest4.addParameter("springMVC_token", new String[] { "abcdef" });

		MockHttpServletResponse httpResponse4 = new MockHttpServletResponse();

		// *******************
		// test case 5
		// *******************
		String testNameTC5 = "Post Method unvalid: has param(client) token and session token,but not the same value";
		MockHttpServletRequest httpRequest5 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest5.addParameter("htid", "1001");
		httpRequest5.addParameter("springMVC_token", new String[] { "abcdef" });
		MockHttpSession session5 = new MockHttpSession();
		Map<String, String> tokenMap5 = new HashMap<String, String>();
		tokenMap5.put("springMVC_token.abcdef", "abcdefg");
		session5.putValue("SPRINGMVC.TOKEN", tokenMap5);
		httpRequest5.setSession(session5);

		MockHttpServletResponse httpResponse5 = new MockHttpServletResponse();

		// *******************
		// test case 6
		// *******************
		String testNameTC6 = "Post Method valid";
		MockHttpServletRequest httpRequest6 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest6.addParameter("htid", "1001");
		httpRequest6.addParameter("springMVC_token", new String[] { "abcdef" });
		MockHttpSession session6 = new MockHttpSession();
		Map<String, String> tokenMap6 = new HashMap<String, String>();
		tokenMap6.put("springMVC_token.abcdef", "abcdef");
		session6.putValue("SPRINGMVC.TOKEN", tokenMap6);
		httpRequest6.setSession(session6);

		MockHttpServletResponse httpResponse6 = new MockHttpServletResponse();

		return Arrays
				.asList(new Object[][] { { testNameTC1, httpRequest1, httpResponse1, true },
						{ testNameTC2, httpRequest2, httpResponse2, false },
						{ testNameTC3, httpRequest3, httpResponse3, false },
						{ testNameTC4, httpRequest4, httpResponse4, false },
						{ testNameTC5, httpRequest5, httpResponse5, false },
						{ testNameTC6, httpRequest6, httpResponse6, true } });
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.debug("Starting test class : " + TokenHandlerInterceptor_preHandleTest.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOGGER.debug("Ending test class : " + TokenHandlerInterceptor_preHandleTest.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("Starting test: " + testName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		LOGGER.debug("Ending test: " + testName);
	}

	/**
	 * Test method for
	 * {@link com.expedia.lux.drr.handlerinterceptors.TokenHandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPreHandle() {
		TokenHandlerInterceptor pre = new TokenHandlerInterceptor();
		try {
			boolean actual = pre.preHandle(httpRequest, httpResponse, new Object());
			assertEquals(this.returnBoolean, actual);

		} catch (Exception e) {
			fail("Should not throw exception!");
		}
	}

}