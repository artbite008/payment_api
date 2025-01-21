package com.siupay.openapi.constant;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class WhiteListConstants {

    @Getter
    public static Set<String> set = new HashSet<>();

    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    void initWhiteList() {
        Resource resource = resourceLoader.getResource("classpath:text/whitelist");
        try (InputStream is = resource.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String data = null;
            while ((data = br.readLine()) != null) {
                set.add(data);
            }
        } catch (Exception e) {
            log.error("初始化出金白名单异常", e);
            throw new RuntimeException("初始化出金白名单失败");
        }
        log.info("初始化出金白名单成功，数量为:{}", set.size());
    }

}
