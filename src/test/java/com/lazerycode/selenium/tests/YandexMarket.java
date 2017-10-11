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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://yandex.ru/");
        //Переходим в нужный раздел
        driver.findElement(By.linkText("Маркет")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Компьютеры")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Планшеты")).click();
        System.out.println("Page title is: " + driver.getTitle());
        driver.findElement(By.linkText("Перейти ко всем фильтрам")).click();
        //Задаем ценовой диапазон
        WebElement price_min = driver.findElement(By.id("glf-pricefrom-var"));
        price_min.sendKeys("20000");
        //Ожидаем пока обработается значение, иначе нижняя граница цены не будет установлена
        Thread.sleep(1000);
        WebElement price_max = driver.findElement(By.id("glf-priceto-var"));
        price_max.sendKeys("25000");
        //Выбираем производителей из развернутого списка
        driver.findElement(By.xpath("//span[text() = 'Ещё']/..")).click();
        driver.findElement(By.xpath("//div[@class='n-filter-block__list-items i-bem']//label[text() = 'Acer']")).click();
        WebElement d_category = driver.findElement(By.xpath("//div[text()='D']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", d_category);
        driver.findElement(By.xpath("//label[@for='glf-7893318-153080']")).click();
        driver.findElement(By.xpath("//span[text() = 'Показать подходящие']/..")).click();
        //Создаем список всех найденных товаров
        List<WebElement> search_results = driver.findElements(By.xpath("//div[@class='n-snippet-card2__part n-snippet-card2__part_type_center']"));
        //Убеждаемся что товара 4
        assertThat(4, equalTo(search_results.size()));
        //Сохраняем название товара и ищем такой товар через поиск
        WebElement tablet = search_results.get(1);
        String find_tablet = tablet.findElement(By.xpath(".//descendant::a")).getText();
        WebElement search = driver.findElement(By.xpath("//input[@aria-labelledby='header-search header-search-label']"));
        search.sendKeys(find_tablet);
        search.submit();
        String concrete_tablet_title =  driver.findElement(By.xpath("//h1[contains(@class,'title title_size_22')]")).getText();
        find_tablet.equals(concrete_tablet_title);
    }
}
