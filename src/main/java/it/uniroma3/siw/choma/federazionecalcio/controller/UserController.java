package it.uniroma3.siw.choma.federazionecalcio.controller;

import it.uniroma3.siw.choma.federazionecalcio.model.User;
import it.uniroma3.siw.choma.federazionecalcio.service.UserService;
import it.uniroma3.siw.choma.federazionecalcio.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registered/profile")
    public String showProfilePage(Model model){
        return "registered/profile";
    }
    @PostMapping("/registered/saveProfileImage")
    public String saveProfileImage(@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        User user = userService.getCurrentUser();
        user.setPicFilename(fileName);
        userService.save(user);
        String uploadDir = "src/main/upload/images/user_pics/" + user.getId();

        FileUpload.saveFile(uploadDir, fileName, multipartFile);
        return showProfilePage(model);
    }
}
