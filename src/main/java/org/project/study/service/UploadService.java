package org.project.study.service;

import org.project.study.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    public String saveTempImage(MultipartFile multipartFile) {

        try {
            if (multipartFile != null) {
                String filePath = "C:/Users/KimChanHee/IdeaProjects/study/frontend/src/assets/temp";
                String OriginFileName = multipartFile.getOriginalFilename();
                String randomFileName = RandomUtil.randomFileName();
                String replaceFileName = randomFileName + "_" + OriginFileName;
                String fullFilePath = filePath + replaceFileName;

                multipartFile.transferTo(new File(fullFilePath));

                return replaceFileName;
            }
        } catch (IOException e) {
            System.out.println("error FileName" + multipartFile.getName());
            e.printStackTrace();
        }

        return null;
    }

    public Integer deleteTempImage(String fileName) {
        if (fileName == null) {
            return 0;
        }
        String filePath = "C:/Users/KimChanHee/IdeaProjects/study/frontend/src/assets/temp";
        String deleteFileName = fileName;
        String fullFilePath = filePath + deleteFileName;
        System.out.println(fileName);
        File file = new File(fullFilePath);
        if (file.exists()) {
            if (file.delete()) {
                return 1;
            }
        } else {
            System.out.println("file이 존재 하지않습니다.");
        }

        return 0;
    }


}
