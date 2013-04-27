/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.jdon.controller.AppUtil;
import com.jdon.sample.test.component.BInterface;
import com.jdon.sample.test.domain.onecase.service.IServiceSample;
import com.jdon.sample.test.domain.simplecase.service.IServiceSampleTwo;
import com.jdon.sample.test.event.AI;
import com.jdon.sample.test.xml.BBI;

public class SampleAppTest extends TestCase {

	AppUtil appUtil;

	protected void setUp() throws Exception {
		appUtil = new AppUtil("com.jdon.jdonframework.xml");
	}

	public void testXml() {
		BBI bb = (BBI) appUtil.getService("bb");
		Assert.assertEquals(bb.myDo(), 99);
	}

	public void testGetService() {
		BInterface b = (BInterface) appUtil.getService("b");
		Assert.assertEquals(b.bMethod(1), 10);
	}

	public void testDomainEvent() {

		IServiceSample serviceSample = (IServiceSample) appUtil.getService("serviceSample");
		Assert.assertEquals("hello", serviceSample.eventPointEntry("hello"));

	}

	public void testDomainEventSimple() {

		IServiceSampleTwo serviceSample = (IServiceSampleTwo) appUtil.getService("serviceSampleTwo");
		String res = (String) serviceSample.eventPointEntry();
		System.out.print(res);
		Assert.assertEquals(res, "Synchronous sayHello and Asynchronous eventMessage=100");

	}

	public void testDCIDomainEvent() {

		IServiceSampleTwo serviceSample = (IServiceSampleTwo) appUtil.getService("serviceSampleTwo");
		String res = (String) serviceSample.nameFinderContext();
		System.out.print(res);
		Assert.assertEquals(res, "Asynchronous eventMessage=100");

	}

	public void testOnEvent() {

		IServiceSampleTwo serviceSample = (IServiceSampleTwo) appUtil.getService("serviceSampleTwo");
		serviceSample.onEventTest();
		Assert.assertTrue(true);

	}

	public void testComponentsEvent() {

		AI a = (AI) appUtil.getService("producer");
		a.ma();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AppUtil appUtil = new AppUtil();
		AI a = (AI) appUtil.getService("producer");
		a.ma();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		appUtil.clear();
	}
}
