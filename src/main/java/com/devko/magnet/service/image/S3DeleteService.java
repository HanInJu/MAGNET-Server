package com.devko.magnet.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3DeleteService {

	@Autowired
	AmazonS3Client s3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public boolean delete(String filename) {
		try {
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, filename);
			s3Client.deleteObject(deleteObjectRequest);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			return false;
		} catch (SdkClientException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
