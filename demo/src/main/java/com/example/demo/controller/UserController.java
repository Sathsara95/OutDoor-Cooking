package com.example.demo.controller;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @GetMapping()
    public String getMessage(){
        return"Springboot user controller work!";
    }

    @PostMapping()
    public String setMessage(@RequestBody String message){
        return "Entered message: "+message;
    }
}
