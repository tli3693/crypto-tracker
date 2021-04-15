package com.troy.crypto.tracker.web.rest;

import com.troy.crypto.tracker.service.UserCoinService;
import com.troy.crypto.tracker.service.fileupload.StorageService;
import com.troy.crypto.tracker.service.fileupload.exception.StorageFileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api")
public class FileUploadController {

    private final StorageService storageService;
    private final UserCoinService userCoinService;

    @Autowired
    public FileUploadController(StorageService storageService, UserCoinService userCoinService) {
        this.storageService = storageService;
        this.userCoinService = userCoinService;
    }

    @GetMapping("/uploaded")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute(
            "files",
            storageService
                .loadAll()
                .map(
                    path ->
                        MvcUriComponentsBuilder
                            .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                            .build()
                            .toUri()
                            .toString()
                )
                .collect(Collectors.toList())
        );

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    @PostMapping("/upload/coinbase-pro")
    public ResponseEntity<String> handleFileUploadCBPro(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
        throws IOException, ParseException {
        String fileName = file.getOriginalFilename();
        storageService.store(file);
        userCoinService.uploadCoinbaseProCSV(fileName);
        return new ResponseEntity<>("You successfully uploaded " + fileName + "!", HttpStatus.OK);
    }

    @PostMapping("/upload/coinbase")
    public ResponseEntity<String> handleFileUploadCB(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
        throws IOException, ParseException {
        String fileName = file.getOriginalFilename();
        storageService.store(file);
        userCoinService.uploadCoinbaseCSV(fileName);
        return new ResponseEntity<>("You successfully uploaded " + fileName + "!", HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
