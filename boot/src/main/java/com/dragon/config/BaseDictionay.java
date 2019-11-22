package com.dragon.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@SpringBootConfiguration
@ConfigurationProperties( prefix = "base")
public class BaseDictionay {

    public List<String> dictionary;

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
}
