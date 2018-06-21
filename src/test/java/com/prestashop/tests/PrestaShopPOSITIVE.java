package com.prestashop.tests;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopPOSITIVE {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
		driver.findElement(By.xpath("//a[@class='login']")).click();
	}

	@Test

	public void LoginTest() throws InterruptedException {
		
		Faker fake = new Faker();
		String name = fake.name().firstName();
		String lastName = fake.name().lastName();
		String email = fake.name().username() + "@gmail.com";
		Address adress = fake.address();
		String passcode=name+lastName;
		
		String winHandleBefore = driver.getWindowHandle();
        //creating account
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		//popup window
		Thread.sleep(2000);
		for(String winHandle : driver.getWindowHandles()){
			   driver.switchTo().window(winHandle);
			}
		
		//first name
		driver.findElement(By.id("customer_firstname")).sendKeys(name);
		Thread.sleep(2000);
		//last name
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		//password
		driver.findElement(By.id("passwd")).sendKeys(passcode);
		//address
		driver.findElement(By.id("address1")).sendKeys(adress.streetAddress());
		//city
		driver.findElement(By.id("city")).sendKeys(adress.cityName());
		//state
		Select dropdown=new Select (driver.findElement(By.id("id_state")));
		dropdown.selectByIndex(fake.number().numberBetween(0,49));
		//postcode
		driver.findElement(By.id("postcode")).sendKeys("22209");
		//phone number
		driver.findElement(By.id("phone_mobile")).sendKeys(fake.phoneNumber().cellPhone());
		//required field of emergency address
		driver.findElement(By.id("alias")).sendKeys(adress.streetAddress());
		//submit
		driver.findElement(By.id("submitAccount")).click();
		//log out
		driver.findElement(By.xpath("//a[@class='logout']")).click();
        //log in 
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(passcode);
		driver.findElement(By.id("SubmitLogin")).click();
		
		String text = driver.findElement(By.xpath("//a[@class='account']/span")).getText();
		Assert.assertEquals(text, name + " " + lastName);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
