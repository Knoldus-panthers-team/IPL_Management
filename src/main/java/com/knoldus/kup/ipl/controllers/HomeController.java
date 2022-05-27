package com.knoldus.kup.ipl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class HomeController.
 */
@Controller
public class HomeController {
    /**
     *
     * @return home page.
     */
    @GetMapping
    public String getHomepage() {
        return "home";
    }
}
