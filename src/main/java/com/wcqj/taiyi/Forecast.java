package com.wcqj.taiyi;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;


public class Forecast extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Forecast().run(new String[] { "server" });
    }
 
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new ForecastResource());
    }

}