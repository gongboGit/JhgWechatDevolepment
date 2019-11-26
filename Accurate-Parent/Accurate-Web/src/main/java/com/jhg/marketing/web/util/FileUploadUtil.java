package com.jhg.marketing.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileUploadUtil {
    @Value("${user.default.uploadpath}")
    private String uploadfilepath;
    @Value("${user.default.visitpath}")
    private String visitpath;
    @Value("${user.domain}")
    private String domain;

    public String upload(MultipartFile file, String dir) {
        String basePathString = uploadfilepath + "/" + dir;
        try {
            File uploadDir = new File(basePathString + "/");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String filename = file.getOriginalFilename();
            filename = UUID.randomUUID().toString().replaceAll("-", "") + filename.substring(filename.lastIndexOf("."));

            File uploadPath = new File(uploadDir.getAbsoluteFile(), filename);
            file.transferTo(uploadPath);
            return "/" + dir + "/" + filename;

        } catch (IllegalStateException | IOException e) {
            log.info(e.getMessage());
        }
        return null;

    }

    public String getVisitPath(MultipartFile file, String dir) {
        return domain + visitpath + upload(file, dir);
    }

    public String getLocalPath(MultipartFile file, String dir) {
        return uploadfilepath + upload(file, dir);
    }
}
