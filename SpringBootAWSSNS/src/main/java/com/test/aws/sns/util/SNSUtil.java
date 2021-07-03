package com.test.aws.sns.util;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SNSUtil {
    private static final Logger logger = LoggerFactory.getLogger(SNSUtil.class);

    @Value("${accessKey}")
    private String s3AccessKey;

    @Value("${accessSecret}")
    private String s3AccessSecret;

    @Value("${snsRegion}")
    private String snsRegion;

    @Value("${snsTopicARN}")
    private String snsTopicARN;

    @Value("${snsSubject}")
    private String snsSubject;

    private AmazonSNS amazonSNS;

    @PostConstruct
    private void initializeSNS() {
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3AccessKey, s3AccessSecret));
        this.amazonSNS = AmazonSNSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(snsRegion)
                .build();
    }

    public void publishSNSMessage(String message) {
        PublishRequest request = new PublishRequest(snsTopicARN, message, snsSubject);
        PublishResult result = this.amazonSNS.publish(request);
        logger.info("SNS Message ID: " + result.getMessageId());
    }
}
