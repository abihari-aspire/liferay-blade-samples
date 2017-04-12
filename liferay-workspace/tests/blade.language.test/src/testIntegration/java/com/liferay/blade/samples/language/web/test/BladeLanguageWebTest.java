/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.samples.language.web.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.net.URL;

import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Liferay
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeLanguageWebTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeSamplesLanguage() throws PortalException {
		_webDriver.get(_portalURL.toExternalForm());

		Assert.assertTrue(isVisible(_bladeSampleLanguagePortlet));
		Assert.assertTrue(_languageKeyFirst.getText().contentEquals("Hello from BLADE LANGUAGE WEB!"));
		Assert.assertTrue(_languageKeySecond.getText().contentEquals("Hello from the BLADE Language Module!"));
		Assert.assertTrue(_languageKeyThird.getText().contentEquals("I have overriden the key fom BLADE Language Module!"));

	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 5);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	@FindBy(xpath = "//div[contains(@id,'_com_liferay_blade_samples_language_web')]")
	private WebElement _bladeSampleLanguagePortlet;

	@FindBy(xpath = "//div[@class='portlet-body']/p[1]")
	private WebElement _languageKeyFirst;

	@FindBy(xpath = "//div[@class='portlet-body']/p[2]")
	private WebElement _languageKeySecond;

	@FindBy(xpath = "//div[@class='portlet-body']/p[3]")
	private WebElement _languageKeyThird;

	@PortalURL("com_liferay_blade_samples_language_web_LanguageWebPortlet")
	private URL _portalURL;

	@Drone
	private WebDriver _webDriver;

}