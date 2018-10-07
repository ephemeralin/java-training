package com.ephemeralin.carplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Login controller.
 */
@Controller
public class LoginController {

    /**
     * Show custom login page.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String showCustomLoginPage() {
        return "login";
    }

    /**
     * Show access denied page.
     *
     * @return the string
     */
    @GetMapping("/denied")
    public String showAccessDeniedPage() {
        return "denied";
    }
}
