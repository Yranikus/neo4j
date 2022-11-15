package com.example.neo4j.controllers;


import com.example.neo4j.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class UploadsControllers {

    @Autowired
    private StudentService studentService;

    @PostMapping("/uploadecxel")
    public String uplodFile(@RequestParam("file") MultipartFile file){
        try {
            studentService.saveListOfUsers(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:createTeams";
    }

    @GetMapping("/createTeams")
    public String createTeams(){
        studentService.createTeams();
        return "redirect:listofstudents";
    }


    @PostMapping("/activateordeactivate")
    public String students(@RequestParam String name, @RequestParam boolean status){
        studentService.updateStatus(name,status);
        return "redirect:listofstudents";
    }





}
