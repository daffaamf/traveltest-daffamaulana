package com.juaracoding.traveltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppTest{
    private WebDriver driver;

    @BeforeMethod
    public void BeforeMethod() {
        //setup driver
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--incognito");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/newtours/register.php");
    }

    @Test
    public void registerUserTest() throws InterruptedException{

        //code isi form registrasi
        driver.findElement(By.name("firstName")).sendKeys("Daffa");
        Thread.sleep(500);
        driver.findElement(By.name("lastName")).sendKeys("Maulana");
        Thread.sleep(500);
        driver.findElement(By.name("phone")).sendKeys("082188882011");
        Thread.sleep(500);
        driver.findElement(By.name("userName")).sendKeys("daffa@gmail.com");
        Thread.sleep(500);

        driver.findElement(By.name("address1")).sendKeys("Jl. Anapuri No. 17");
        Thread.sleep(500);
        driver.findElement(By.name("city")).sendKeys("Bandung");
        Thread.sleep(500);
        driver.findElement(By.name("state")).sendKeys("Jawa Barat");
        Thread.sleep(500);
        driver.findElement(By.name("postalCode")).sendKeys("40123");
        Thread.sleep(500);
        Select select = new Select(driver.findElement(By.name("country")));
        select.selectByVisibleText("INDONESIA");

        driver.findElement(By.name("email")).sendKeys("daffa");
        Thread.sleep(500);
        driver.findElement(By.name("password")).sendKeys("pass123");
        Thread.sleep(500);
        driver.findElement(By.name("confirmPassword")).sendKeys("pass123");
        Thread.sleep(500);

        // scroll ke element karena ada popup iklan
        WebElement submitBtn = driver.findElement(By.name("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        Thread.sleep(1000);
        // saya pakai JS karna selenium tidak bisa menggunakan method scroll//

        // klik tombol pakai JavaScript agar tidak gagal meski ketutupan element lain
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        // dan penggunaan JS untuk click tombol submit karna terhalang oleh iklan bawaan web//

         //  validate apakah register berhasil
        String actual1 = driver.getCurrentUrl();
        String expected1 = "https://demo.guru99.com/test/newtours/register_sucess.php";
        Assert.assertEquals(actual1, expected1);

        //  validate pesan greetings
        String actual2 = driver.findElement(By.xpath("//b[contains(text(),' Dear ')]")).getText();
        String expected2 = "Dear Daffa Maulana,";
        Assert.assertEquals(actual2, expected2);

        //  validate pesan username
        String actual3 = driver.findElement(By.xpath("//b[contains(text(),' Note: Your user name is ')]")).getText();
        String expected3 = "Note: Your user name is daffa.";
        Assert.assertEquals(actual3, expected3);

        Thread.sleep(8000);
    }

    @AfterMethod
    public void AfterMethod(){
        driver.quit();
    }
}
