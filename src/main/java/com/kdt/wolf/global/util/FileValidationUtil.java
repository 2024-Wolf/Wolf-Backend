package com.kdt.wolf.global.util;

import org.springframework.web.multipart.MultipartFile;

public class FileValidationUtil {

    // 프로필 이미지 검증
    public static void validateImageFile(MultipartFile profileImage) {
        // 파일이 비어 있는지 확인
        if (profileImage.isEmpty()) {
            throw new IllegalArgumentException("프로필 이미지 파일이 비어 있습니다.");
        }

        // 파일 크기 제한 (예: 10MB 이하)
        long maxFileSize = 10 * 1024 * 1024; // 10MB
        if (profileImage.getSize() > maxFileSize) {
            throw new IllegalArgumentException("프로필 이미지 파일 크기는 10MB 이하로 제한됩니다.");
        }

        // 지원하는 확장자 확인 (예: JPG, PNG)
        String fileExtension = getFileExtension(profileImage.getOriginalFilename());
        if (!isSupportedFileExtension(fileExtension)) {
            throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다. JPG 또는 PNG 파일을 업로드하세요.");
        }

        // MIME 타입 확인 (예: image/jpeg, image/png)
        String contentType = profileImage.getContentType();
        if (!isSupportedContentType(contentType)) {
            throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다. JPG 또는 PNG 파일을 업로드하세요.");
        }
    }
    // 파일 확장자 추출
    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    // 지원하는 파일 확장자 검사
    private static boolean isSupportedFileExtension(String fileExtension) {
        return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png");
    }

    // 지원하는 MIME 타입 검사
    private static boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/jpeg") || contentType.equals("image/png");
    }
}