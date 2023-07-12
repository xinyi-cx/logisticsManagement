package com.ruoyi.web.controller.test;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMoney {


//    https://blog.csdn.net/ke2602060221/article/details/124171001?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-124171001-blog-127007853.235%5Ev38%5Epc_relevant_anti_vip_base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-124171001-blog-127007853.235%5Ev38%5Epc_relevant_anti_vip_base&utm_relevant_index=2

    public void taoBao() throws Exception {
//        http://chromedriver.storage.googleapis.com/index.html
        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver","D:\\JDK\\chromedriver.exe");

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse("2022-04-14 14:07:00 000000000");

        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver();
        Actions actions = new Actions(browser);
        //2、输入网址
        browser.get("https://www.taobao.com");
        Thread.sleep(3000);

        //3、点击登录
        browser.findElement(By.linkText("亲，请登录")).click();

        Thread.sleep(2000);

        //4、扫码登录
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(4000);

        //5、进入购物车页面
        browser.get("https://cart.taobao.com/cart.htm");
        Thread.sleep(3000);

        //6、点击选择第一个按钮
        browser.findElement(By.xpath("//*[@id=\"J_Order_s_2207407355826_1\"]/div[1]/div/div/label")).click();

        Thread.sleep(2000);
        while (true){
            //当前时间
            Date now = new Date();
            System.out.println(now);
            if(now.after(date)){
                if(browser.findElement(By.linkText("结 算")).isEnabled()){
                    browser.findElement(By.linkText("结 算")).click();
                    System.out.println("结算成功");
                    break;
                }

            }
        }

        Thread.sleep(5000);
    }



}
