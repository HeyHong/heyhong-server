package com.heyhong.HeyHong.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ResizingUtil {

    public MultipartFile resizeProfileImage(MultipartFile originalFile, String userId, int targetWidth, int targetHeight) throws Exception{

        try{
            BufferedImage image = ImageIO.read(originalFile.getInputStream());

            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();
            scale.setAttribute("newWidth", targetWidth);
            scale.setAttribute("newHeight", targetHeight);
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);

            BufferedImage imageNoApha = imageMarvin.getBufferedImageNoAlpha();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageNoApha, "jpg", baos);
            baos.flush();

            byte[] bytes = baos.toByteArray();
            UUID uuid = UUID.randomUUID();
            String newImageFileName = userId.concat(uuid.toString());
            return new MultipartImage(bytes, newImageFileName, newImageFileName+".jpg", "jpg", bytes.length);
        }catch (IOException e){
            throw new Exception("이미지 리사이징 실패 " + e.getMessage());
        }



    }
}
