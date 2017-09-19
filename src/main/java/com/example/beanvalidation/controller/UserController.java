package com.example.beanvalidation.controller;

import com.example.beanvalidation.exception.WrapException;
import com.example.beanvalidation.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by sai.luo on 2017-9-18.
 */
@RestController
public class UserController {

    @RequestMapping("/add")
    public Object addUser(@RequestBody @Valid User user, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result.getAllErrors().get(0).getDefaultMessage());
            throw new WrapException(0,result.getAllErrors().get(0).getDefaultMessage());
        }
        System.out.println(user);
        return user  ;
    }
}
