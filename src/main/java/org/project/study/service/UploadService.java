package org.project.study.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.collect.ImmutableMap;
import org.project.study.config.AwsProperties;
import org.project.study.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

@Service
public class UploadService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AwsProperties awsProperties;

    public Map<String, String> saveS3Image(MultipartFile multipartFile) {

        try {
            if (multipartFile != null) {
                String originFileName = multipartFile.getOriginalFilename();
                if (originFileName.length() > 50) {
                    originFileName = originFileName.substring(originFileName.length() - 50);
                }
                String randomFileName = RandomUtil.randomFileName();
                String replaceFileName = this.createFolder() + randomFileName + "_" + originFileName;

                this.uploadS3(replaceFileName, multipartFile.getBytes(), multipartFile.getContentType());

                return ImmutableMap.of("url", awsProperties.getUrl() + replaceFileName, "key", replaceFileName);
            }
        } catch (IOException e) {
            System.out.println("error FileName" + multipartFile.getName());
            e.printStackTrace();
        }

        return null;
    }

    public void deleteS3Image(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(awsProperties.getBucket(), fileName));
    }

    private void uploadS3(String key, byte[] fileBytes, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        if (contentType != null) {
            metadata.addUserMetadata("Content-Type", contentType);
        }
        metadata.setContentLength(fileBytes.length);

        PutObjectRequest putObjectRequest = new PutObjectRequest(awsProperties.getBucket(), key, new ByteArrayInputStream(fileBytes), metadata);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
    }

    private String createFolder() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return year + "/" + month + "/" + day + "/";
    }

}
