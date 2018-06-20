package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopNEGATIVE {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@ class='login']")).click();
	}

	@Test
	public void WrongCredentialsTest() {

		driver.findElement(By.id("email")).sendKeys("Ivanna@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("cybertek");
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertTrue(true, "Authentication failed.");

	}

	@Test
	public void InvalidEmailTest() {
		driver.findElement(By.id("email")).sendKeys("Ivanna");
		driver.findElement(By.id("passwd")).sendKeys("cybertek");
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertTrue(true, "Invalid email address.");

	}

	@Test
	public void BlankEmailTest() {
		driver.findElement(By.id("email")).sendKeys(" ");
		driver.findElement(By.id("passwd")).sendKeys("cybertek");
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertTrue(true, "An email address required.");
	}

	@Test
	public void BlankPasswordTest() {
		driver.findElement(By.id("email")).sendKeys("Ivanna@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys(" ");
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertTrue(true, " Password is required. ");
	}

	@AfterMethod
	public void tearDown() {
		//driver.close();
	}
}
