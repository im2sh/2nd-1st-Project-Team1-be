package com.gdsc.canigraduate.config;

import com.gdsc.canigraduate.util.BaseEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new BaseEnumConverterFactory());
    }
}
