package com.wealth.demo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CaptchaConfig {

    @Bean
    public DefaultKaptcha captchaProducer() {

        // 創建一個 DefaultKaptcha 實例
        DefaultKaptcha kaptcha = new DefaultKaptcha();

        // 創建一個 Properties 對象用於設置驗證碼的配置屬性
        Properties properties = new Properties();

        // 設置驗證碼是否顯示邊框，這裡設置為不顯示
        properties.setProperty("kaptcha.border", "no");

        // 設置驗證碼文字的顏色，這裡設置為黑色
        properties.setProperty("kaptcha.textproducer.font.color", "black");

        // 設置驗證碼文字之間的間距，這裡設置為 5 像素
        properties.setProperty("kaptcha.textproducer.char.space", "5");

        // 設置驗證碼圖片的寬度，這裡設置為 150 像素
        properties.setProperty("kaptcha.image.width", "150");

        // 設置驗證碼圖片的高度，這裡設置為 50 像素
        properties.setProperty("kaptcha.image.height", "45");

        // 設置驗證碼文字的字體大小，這裡設置為 40 像素
        properties.setProperty("kaptcha.textproducer.font.size", "40");

        // 設置驗證碼文字的長度，這裡設置為 5 個字符
        properties.setProperty("kaptcha.textproducer.char.length", "4");

        // 設置驗證碼文字的字符集，這裡設置為字母和數字
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");

        // 設置驗證碼噪點實現類，這裡設置為無噪點
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        // 使用設置的屬性配置 DefaultKaptcha 實例
        kaptcha.setConfig(new Config(properties));

        // 返回配置好的 DefaultKaptcha 實例
        return kaptcha;
    }

}
