package com.example.management_data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="env")
public class TestConfig {

    //getter, setter
    private List<String> servers = new ArrayList<String>();

    public List<String> getServers() {
        return this.servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
