package com.ibm.automation.test;
//
///**
// * Created by patrick on 8/5/2015.
// */
//
//import java.util.Random;
//import com.thoughtworks.selenium.*;
///**
// * @author Ò¶ºÕ»ª  2010Äê5ÔÂ
// */
public class VoteQQ{}
//public class VoteQQ extends SeleneseTestCase {
//
//    @Test
//    public void testVote() throws Exception {
//        SeleniumServer seleniumserver = new SeleniumServer();
//        seleniumserver.start();
//        System.out.println(seleniumserver.getPort());
//        Selenium selenium = new DefaultSelenium("localhost",4444,"*firefox", "http://tsingtao.qq.com");
//
//        selenium.start();
//        selenium.setTimeout("60000");
//        selenium.open("/user/list?order=1&page=1");
//
//        selenium.click("link=µÇÂ¼");
//        Thread.sleep(2000);
//        selenium.selectFrame("login_frame");
//        selenium.type("u", "1234567890");
//        selenium.type("p", "********");
//        //selenium.type("verifycode", "udfz");
//        selenium.click("login_button");
//        selenium.click("close");
//        Thread.sleep(2000);
//        selenium.selectFrame("relative=up");
//
//        int i, intIndividualCount, intRandNumber, intVoteCount = 10;
//        intIndividualCount = selenium.getXpathCount("//html/body/div[@id='warp']/div[@class='umain']/div/div[2]/div[@class='for_div']").intValue();
//        System.out.println("intIndividualCount is:" + intIndividualCount);
//        Random rand = new Random();
//
//        for (i = 0; i < intVoteCount; i ++){
//            intRandNumber = rand.nextInt(intIndividualCount - 1);
//            System.out.println("intRandNumber is:" + intRandNumber);
//            String strIndividualName = selenium.getText("//html/body/div[@id='warp']/div[@class='umain']/div/div[2]/div[@class='for_div'][" + String.valueOf(intRandNumber + 1) + "]/dl/dd/p[1]");
//            selenium.click("//html/body/div[@id='warp']/div[@class='umain']/div/div[2]/div[@class='for_div'][" + String.valueOf(intRandNumber + 1) + "]/dl/dd/p[3]/a[1]/img");
//            selenium.selectFrame("login_frame");
//            Thread.sleep(2000);
//            selenium.click("close");
//            System.out.println("strIndividualName is:" + strIndividualName);
//            Thread.sleep(2000);
//            selenium.selectFrame("relative=up");
//        }
//        selenium.stop();
//        seleniumserver.stop();
//    }
//
//}


