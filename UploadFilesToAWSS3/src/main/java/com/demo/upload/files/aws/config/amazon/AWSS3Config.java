package com.demo.upload.files.aws.config.amazon;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

	@Value("${s3.access.name}")
	private String accessKey;

	@Value("${s3.access.secret}")
	private String secretAccess;

	@Bean
	public AmazonS3 getAmazonS3Client() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretAccess);
		return AmazonS3ClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
				.build();
	}
}
