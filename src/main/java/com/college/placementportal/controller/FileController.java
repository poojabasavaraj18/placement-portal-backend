package com.college.placementportal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        System.out.println("🔥 FILE REQUEST RECEIVED: " + filename);

        try {
            // ✅ SAME PATH AS SERVICE
            String fullPath = System.getProperty("user.dir") + "/" + uploadDir + "/" + filename;

            System.out.println("👉 FULL PATH: " + fullPath);

            File file = new File(fullPath);

            if (!file.exists()) {
                System.out.println("❌ FILE NOT FOUND");
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(file.toURI());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}