package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class YandexMarket extends DriverBase {
    @Test
    public void tabletSearch() throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();

        driver.get("http://yandex.ru/");

        driver.findElement(By.linkText("Маркет")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Компьютеры")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Планшеты")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Перейти ко всем фильтрам")).click();
        WebElement price_min = driver.findElement(By.id("glf-pricefrom-var"));
        price_min.sendKeys("20000");
        WebElement price_max = driver.findElement(By.id("glf-priceto-var"));
        price_max.sendKeys("25000");

        driver.findElement(By.xpath("//span[text() = 'Ещё']/..")).click();
        driver.findElement(By.xpath("//label[text() = 'Acer']/..")).click();
        WebElement d_category = driver.findElement(By.xpath("//div[text()='D']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", d_category);
        driver.findElement(By.xpath("//label[@for='glf-7893318-153080']")).click();
        driver.findElement(By.xpath("//span[text() = 'Показать подходящие']/..")).click();
        driver.findElement(By.xpath("//div[@class='pager-more__button pager-loader_preload']")).click();
        List<WebElement> search_results = driver.findElements(By.xpath("//div[@class='n-snippet-card2__part n-snippet-card2__part_type_right']"));
        //assertThat(3, equalTo(search_results.size()));

    }
}
