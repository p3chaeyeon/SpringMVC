// Spring/src/main/java/user/service/impl/NCPObjectStorageService.java
package user.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import spring.conf.NaverConfiguration;
import user.service.ObjectStorageService;

@Service
public class NCPObjectStorageService implements ObjectStorageService {
	final AmazonS3 s3;
	
	public NCPObjectStorageService(NaverConfiguration naverConfiguration) {
		s3 = AmazonS3ClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
						naverConfiguration.getEndPoint(), 
						naverConfiguration.getRegionName())
				)
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(
								naverConfiguration.getAccessKey(),
								naverConfiguration.getSecretKey())
						)
				)
				.build();		
	}
	
	   @Override
	   public String uploadFile(String bucketName, String directoryPath, MultipartFile img) {
	      try(InputStream inputStream = img.getInputStream()){         
	         String imageFileName = UUID.randomUUID().toString();
	         
	         ObjectMetadata objectMetadata = new ObjectMetadata();
	         objectMetadata.setContentType(img.getContentType());
	         
	         PutObjectRequest putObjectRequest =
	               new PutObjectRequest(bucketName,
	                               directoryPath + imageFileName,
	                               inputStream,
	                               objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
	         
	         s3.putObject(putObjectRequest);
	         
	         return imageFileName;
	      }catch (Exception e) {
	         throw new RuntimeException("파일 업로드 에러" + e);
	      }

	   }

	// Spring/src/main/java/user/service/impl/NCPObjectStorageService.java	   
	@Override
	public void deleteFile(String bucketName, String directoryPath, String imageFileName) {
//		s3.deleteObject(bucketName, 
//				directoryPath + imageFileName);
	    try {
	        s3.deleteObject(bucketName, directoryPath + imageFileName);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}

	@Override
	public void deleteFile(String bucketName, String directoryPath, List<String> list) {
		for (String imageFileName: list) {
			s3.deleteObject(bucketName, directoryPath + imageFileName);
		}
	}

}
