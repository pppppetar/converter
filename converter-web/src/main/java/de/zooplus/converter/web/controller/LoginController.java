package de.zooplus.converter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by dragan on 19-Nov-16.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping
    public View redirectToLogin(){
        return new RedirectView("login");
    }

    @RequestMapping("login")
    public String getLogin(){
        return "login";
    }
}
