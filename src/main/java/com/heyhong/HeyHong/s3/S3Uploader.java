package com.heyhong.HeyHong.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.heyhong.HeyHong.config.s3.AmazonS3Config;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    public String upload(MultipartFile multipartFile, String dirName) throws IOException{
//
//        File uploadFile = convert(multipartFile).orElseThrow(()-> new IllegalArgumentException("error : MultipartFile -> File convert Fail"));
//
//        return upload(uploadFile, dirName);
//    }
//
//    private String upload(File uploadFile, String dirName) {
//        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
//
//        String uploadImageUrl = putS3(uploadFile, fileName);
//        // 로컬에 생성한 사진 파일 삭제
//        removeNewFile(uploadFile);
//        return uploadImageUrl;
//    }

    /**
     * 단일 파일 업로드
     * @param multipartFile
     * @param dirName
     * @return uploadImageUrl
     * @throws IOException
     */
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {

        String fileName = dirName + "/" + UUID.randomUUID() + multipartFile.getName();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()){

            return putS3(inputStream, fileName, objectMetadata);

        }catch (IOException e){
            throw e;
        }
    }

    /**
     * 복수 파일 업로드
     * @param multipartFileList
     * @param dirName
     * @return Map<fileName, uploadImageUrl>
     * @throws IOException
     */
    public Map<String, String> upload(List<MultipartFile> multipartFileList, String dirName) throws IOException{

        Map<String, String> result = new HashMap<>();

        for(MultipartFile file : multipartFileList){
            String fileName = dirName + "/" + UUID.randomUUID() + file.getName();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()){

                String uploadUrl = putS3(inputStream, fileName, objectMetadata);
                result.put(fileName, uploadUrl);

            }catch (IOException e){
                throw e;
            }
        }

        return result;

    }


    /**
     * S3에 파일 업로드
     * @param inputStream
     * @param fileName
     * @param objectMetadata
     * @return
     */
    private String putS3(InputStream inputStream, String fileName, ObjectMetadata objectMetadata){

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    private void removeNewFile(File targetFile){
        if(targetFile.delete()){
            log.info("파일이 삭제되었습니다.");
        }else{
            log.info("파일이 삭제되지 않았습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException{
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if(convertFile.createNewFile()){
            // 위에 지정한 경로에 File이 생성됨
            try(FileOutputStream fos = new FileOutputStream(convertFile)){
                // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
