package com.aws.parameterstore.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@SpringBootApplication
public class AwsParameterDemoApplication implements CommandLineRunner {


    @Value("${hello}") // from parameter store
    private String hello;


    @Value("${orgKeyMap}") // from secrets manager
    private String orgKeyMap;

    public static void main(String[] args) {
        SpringApplication.run(AwsParameterDemoApplication.class, args);

    }

    @Override
    public void run(String... args) {

        System.out.println("hello from parameter store = " + hello);
        System.out.println("orgKeyMap from secrets manager = " + orgKeyMap);
//        System.out.println("accessing secrets from parameter = " + getParameter("/aws/reference/secretsmanager/mytesting-secrets"));
    }

    public String getParameter(String parameterName) {
        String secret = "";
        Region region = Region.US_EAST_2;
        SsmClient ssmClient = SsmClient.builder().region(region).build();
        GetParameterRequest parameterRequest = GetParameterRequest.builder().name(parameterName).withDecryption(true).build();
        GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
        secret = parameterResponse.parameter().value();
        return secret;
    }
}
