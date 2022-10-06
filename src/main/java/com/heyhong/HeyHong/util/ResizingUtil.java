package com.heyhong.HeyHong.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ResizingUtil {

    public MultipartFile resizeProfileImage(MultipartFile originalFile, String userId, int targetWidth) throws Exception{

        try{
            BufferedImage image = ImageIO.read(originalFile.getInputStream());

            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();

            if(originWidth <= targetWidth){
                return originalFile;
            }

            scale.setAttribute("newWidth", targetWidth);
            scale.setAttribute("newHeight", originHeight * targetWidth / originWidth);
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);

            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(imageNoAlpha, "jpg", baos);
//            baos.flush();
//
//            byte[] bytes = baos.toByteArray();

            UUID uuid = UUID.randomUUID();
            String newImageFileName = userId.concat(uuid.toString());

            File outputFile = new File(newImageFileName + ".jpg");
            ImageIO.write(imageNoAlpha, "jpg", outputFile);

            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(outputFile.toPath()), false, outputFile.getName(), (int) outputFile.length(), outputFile.getParentFile());

            InputStream input = new FileInputStream(outputFile);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);

            outputFile.delete();

            return new CommonsMultipartFile(fileItem);

//            return new MultipartImage(bytes, newImageFileName, newImageFileName+".jpg", "jpg", bytes.length);
        }catch (IOException e){
            throw new Exception("이미지 리사이징 실패 " + e.getMessage());
        }catch (Exception e){
            throw e;
        }



    }
}
