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
import org.springframework.web.servlet.ModelAndView;

@RunWith(Parameterized.class)
public class TokenHandlerInterceptorTest_postHandleTest {

	private static final Logger LOGGER = Logger.getLogger(TokenHandlerInterceptorTest_postHandleTest.class);

	private String testName;
	private ModelAndView mnv;
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;

	public TokenHandlerInterceptorTest_postHandleTest(String testName, ModelAndView mnv,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		this.testName = testName;
		this.mnv = mnv;
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
	}

	@Parameters
	public static Collection<Object[]> testData() throws Exception {
		// *******************
		// test case1
		// *******************
		String testNameTC1 = "Get Method";
		MockHttpServletRequest httpRequest1 = new MockHttpServletRequest("get", "http://testRequestUrl");
		httpRequest1.addParameter("htid", "1001");

		MockHttpSession session1 = new MockHttpSession();
		Map<String, String> tokenMap1 = new HashMap<String, String>();
		tokenMap1.put("springMVC_token.abcdef", "abcdef");
		session1.putValue("SPRINGMVC.TOKEN", tokenMap1);
		httpRequest1.setSession(session1);

		MockHttpServletResponse httpResponse1 = new MockHttpServletResponse();

		Map<String, Object> testModel1 = new HashMap<String, Object>();
		testModel1.put("TOKEN_VALUE", "abcde");

		ModelAndView mvn1 = new ModelAndView("ViewName", testModel1);

		// *******************
		// test case2
		// *******************
		String testNameTC2 = "Post Method";
		MockHttpServletRequest httpRequest2 = new MockHttpServletRequest("post", "http://testRequestUrl");
		httpRequest1.addParameter("htid", "1001");

		MockHttpServletResponse httpResponse2 = new MockHttpServletResponse();

		Map<String, Object> testModel2 = new HashMap<String, Object>();
		testModel2.put("TOKEN_VALUE", "abcde");

		ModelAndView mvn2 = new ModelAndView("ViewName", testModel2);

		return Arrays.asList(new Object[][] { { testNameTC1, mvn1, httpRequest1, httpResponse1 },
				{ testNameTC2, mvn2, httpRequest2, httpResponse2 }

		});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOGGER.debug("Starting test class : " + TokenHandlerInterceptorTest_postHandleTest.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOGGER.debug("Ending test class : " + TokenHandlerInterceptorTest_postHandleTest.class.getName());
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
	 * {@link com.expedia.lux.drr.handlerinterceptors.TokenHandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)}
	 * .
	 */
	@Test
	public void testPostHandle() {
		TokenHandlerInterceptor post = new TokenHandlerInterceptor();
		try {
			post.postHandle(httpRequest, httpResponse, new Object(), mnv);
		} catch (Exception e) {
			fail("Should not throw exception!");
		}
	}

}
