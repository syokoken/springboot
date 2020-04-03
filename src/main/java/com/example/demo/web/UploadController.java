package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "upload")
public class UploadController {
    @Value("${spring.servlet.multipart.location}")
    private String uploaded_folder;

    @GetMapping("/index")
    public String index(){
        return "upload/index";
    }
    @GetMapping("/mulUpload")
    public String mulUpload(){
        return "upload/mulUpload";
    }
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload/uploadStatus";
    }
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/upload/uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            //文件本地存储地址
            Path path = Paths.get(uploaded_folder + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/upload/uploadStatus";
    }

    @PostMapping("/uploadMore")
    public String moreFileUpload(@RequestParam("file") MultipartFile[] files,
                                 RedirectAttributes redirectAttributes) {
        if (files.length==0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/upload/uploadStatus";
        }
        for(MultipartFile file:files){
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(this.uploaded_folder + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded all");
        return "redirect:/upload/uploadStatus";
    }
}
