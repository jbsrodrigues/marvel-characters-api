package com.mydemoprojects.marvelcharactersapi.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "Service unavailable. Please try again later.";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
