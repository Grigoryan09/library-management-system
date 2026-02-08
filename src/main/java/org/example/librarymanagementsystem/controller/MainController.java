package org.example.librarymanagementsystem.controller;

import org.apache.commons.io.FileUtils;
import org.example.librarymanagementsystem.model.User;
import org.example.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

    private final UserService userService;
    @Value("${library.management.upload.image.directory.path}")
    private String imageDirectoryPath;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return  "loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public  String register(@ModelAttribute User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/registerPage?msg=Username already exists";
        }
        userService.save(user);
        return "redirect:/loginPage?msg=Registration successful, pls login!";
    }

    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("pic") String picName ) {
        File file = new File(imageDirectoryPath + picName);
        if (file.exists()) {
            try {
                return  FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }


}
