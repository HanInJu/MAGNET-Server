package com.devko.magnet.util.s3;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.Data;

@Data
@Component
public class S3Access {
	private static AmazonS3 s3client;

	public static void main(String[] args) throws IOException, URISyntaxException {
		s3client = createConnectionWithCredentials();
	}
	
	private static AmazonS3 createConnectionWithCredentials() {
		   return AmazonS3ClientBuilder
		         .standard()
		         .withCredentials(new DefaultAWSCredentialsProviderChain()) // env 환경변수나 ~/.aws에 저장된 값을 읽어들인다.
		         .withRegion(Regions.AP_NORTHEAST_2)
		         .build();
		}

	public static AmazonS3 getS3client() {
		return s3client;
	}

}
