package com.example.jubileebackendeindopdracht.controllers;

import com.example.jubileebackendeindopdracht.dtos.UploadDto;
import com.example.jubileebackendeindopdracht.services.UploadService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.net.URI;


@RestController
@RequestMapping("/uploads")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    // upload
    @PostMapping
    public ResponseEntity<UploadDto> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("accountId") Long accountId){

        UploadDto uploadedFileDto = uploadService.uploadFile(file, accountId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uploadedFileDto.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(uploadedFileDto);
    }

    // download
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId) throws FileNotFoundException {
        Resource fileResource = uploadService.downloadFile(fileId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileResource.getFilename());

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileResource);
    }


    // delete file
    @DeleteMapping("/{fileId}")
    public ResponseEntity<UploadDto> deleteFile(@PathVariable("fileId") Long fileId) throws FileNotFoundException {
        UploadDto deletedFileDto = uploadService.deleteFile(fileId);
        return ResponseEntity.ok(deletedFileDto);
    }

}
