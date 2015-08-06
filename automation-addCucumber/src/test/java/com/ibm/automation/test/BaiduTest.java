package com.ibm.automation.test;

import com.thoughtworks.selenium.DefaultSelenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by patrick on 8/5/2015.
 */
public class BaiduTest {
        @Test
        public void test(){
            //System.setProperty("webdriver.ie.driver","E:\\seleniumIDE\\IEDriverServer.exe");
//            WebDriver driver=new ChromeDriver();
//            driver.get("http://www.baidu.com");
            System.setProperty("webdriver.chrome.driver", "D:\\devel\\workspace\\workspace\\github\\automation_testing\\automation-addCucumber\\src\\main\\resources\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("http://www.baidu.com");
            List<WebElement> links=driver.findElements(By.cssSelector("#nv a"));
            WebElement user_login=driver.findElement(By.linkText("登录"));
            assertEquals("https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F", user_login.getAttribute("href"));
            System.out.println(links.size());
            assertEquals(7,links.size());
            for(int i=0;i<links.size();i++){
                System.out.println(links.get(i).getAttribute("href"));
            }
            driver.close();
        }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String host="localhost";
        int port=4444;
        String browserType="*firefox";
        String url="http://www.360buy.com";
        //实例化Selenium1对象
        DefaultSelenium selenium = new DefaultSelenium(host, port,browserType, url);
        selenium.start();
        //打开京东登录页面
        selenium.open("https://passport.360buy.com/new/login.aspx");
        //填写符合xpath的用户名文本框、密码文本框，单击登录
        selenium.type("id=loginname", "UserName1");
        selenium.type("id=nloginpwd", "Password");
        selenium.click("id=loginsubmit");
        System.out.println(selenium.getTitle());
    }
}
