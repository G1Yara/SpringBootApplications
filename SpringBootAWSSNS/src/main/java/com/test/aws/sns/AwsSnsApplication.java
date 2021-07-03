package com.test.aws.sns;

import com.test.aws.sns.util.SNSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsSnsApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(AwsSnsApplication.class);

	@Autowired
	private SNSUtil snsUtil;

	public static void main(String[] args) {
		SpringApplication.run(AwsSnsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String message = "Hello AWS SNS notification";
		logger.info(message);
		snsUtil.publishSNSMessage(message);

	}
}
