package dev.practice.upload.service;

import dev.practice.upload.domain.UploadMetadata;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadStorageService {
    public UploadMetadata store(MultipartFile file, String replacingStorageId) {
        // TODO: re-check size/content, generate a UUID storage key, store outside the web root,
        // and define safe replace/cleanup behavior if metadata persistence fails.
        throw new UnsupportedOperationException("TODO: implement FILE-01 storage flow");
    }
}
