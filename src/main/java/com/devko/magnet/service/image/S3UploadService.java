package com.devko.magnet.service.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3UploadService {

	@Autowired
	AmazonS3Client s3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	public String bucket;
	
	public ResponseEntity upload(MultipartFile uploadFile, String dir) throws IllegalStateException, IOException {
		String fileName = dir + "/" + UUID.randomUUID() + "-" + uploadFile.getOriginalFilename(); // 파일이름 커스텀
		ObjectMetadata metadata = new ObjectMetadata();
		return new ResponseEntity(putS3(fileName, uploadFile.getInputStream(), metadata), HttpStatus.OK);
	}
	
	private String putS3(String fileName, InputStream inputStream, ObjectMetadata metadata) {
		s3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata));
		return s3Client.getUrl(bucket, fileName).toString();
	}
	
}
