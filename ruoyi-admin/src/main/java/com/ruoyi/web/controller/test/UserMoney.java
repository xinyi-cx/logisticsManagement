package com.ruoyi.web.controller.test;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMoney {


//    https://blog.csdn.net/ke2602060221/article/details/124171001?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-124171001-blog-127007853.235%5Ev38%5Epc_relevant_anti_vip_base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-124171001-blog-127007853.235%5Ev38%5Epc_relevant_anti_vip_base&utm_relevant_index=2

    public void taoBao() throws Exception {
//        http://chromedriver.storage.googleapis.com/index.html
        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver", "D:\\JDK\\chromedriver.exe");

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
        while (true) {
            //当前时间
            Date now = new Date();
            System.out.println(now);
            if (now.after(date)) {
                if (browser.findElement(By.linkText("结 算")).isEnabled()) {
                    browser.findElement(By.linkText("结 算")).click();
                    System.out.println("结算成功");
                    break;
                }

            }
        }

        Thread.sleep(5000);
    }

//    https://blog.csdn.net/m0_72717197/article/details/127007853
    public static void main(String[] args) throws ParseException, InterruptedException {
        //将下载的浏览器驱动 路径拷贝到这里
        System.setProperty("webdriver.chrome.driver", "D:\\gugequdong\\chromedriver.exe");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        //设置将要秒杀商品的时间
        Date date = sdf.parse("2022-09-23 11:18:30 000");
        ChromeDriver browser = new ChromeDriver();
        //这里以淘宝为例
        browser.get("https://www.taobao.com");
        browser.findElement(By.linkText("亲，请登录")).click();
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(15000);
        browser.get("https://cart.taobao.com/cart.htm");
        //店铺的xpath
        browser.findElement(By.xpath("//*[@id=\"J_Order_s_2256022143_1\"]/div[1]/div/div/label")).click();
        while (true) {
//            大于等于10秒 线程等待
            if (date.getTime() > System.currentTimeMillis()) {
                long a = date.getTime() - System.currentTimeMillis();
                if (a > 101000) {
                    System.out.println("当前时间：" + sdf.format(new Date()) + "; 秒杀时间为：" + sdf.format(date) + "; 距离轮循模式还有：" + a / 1000 + "秒；");
                    Thread.sleep(100000);
                }
                if (a > 10000) {
                    System.out.println("当前时间：" + sdf.format(new Date()) + "; 秒杀时间为：" + sdf.format(date) + "; 距离秒杀还有：" + a / 1000 + "秒");
                    Thread.sleep(5000);
                }
            }
            //小于等于10秒 进入轮询模式
            Date now = new Date();
            if ((date.getTime() - now.getTime()) <= 10000) {
                System.out.println("进入轮询模式：距离秒杀还有:" + (date.getTime() - now.getTime()) / 1000 + "秒");
            }
            //当前时间 >= 秒杀时间
            if (now.getTime() >= date.getTime()) {
                if (browser.findElement(By.linkText("结 算")).isEnabled()) {
                    browser.findElement(By.linkText("结 算")).click();
                    System.out.println("已结算");
                    browser.findElement(By.className("go-btn")).click();
                    System.out.println("订单已提交");
                    break;
                }
            }
        }
        Thread.sleep(10000);
        System.out.println("请尽快付款...");
    }


}
