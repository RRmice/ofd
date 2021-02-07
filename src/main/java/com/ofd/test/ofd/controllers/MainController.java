package com.ofd.test.ofd.controllers;

import com.ofd.test.ofd.entites.dto.UserDto;
import com.ofd.test.ofd.exceptions.UserNameBusyException;
import com.ofd.test.ofd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = { "/" , "/home" })
    public String homePage(Model model, @RequestParam(required=false) String error) {
        setErrorCode(model, error);
        return "homePage";
    }

    @RequestMapping("/personal")
    public String getPersonalPage(Model model, Principal principal, @RequestParam(required=false) String error) throws SQLException {
        model.addAttribute("balance", userService.getUserBalance(principal.getName()));
        return "personalPage";
    }

    @RequestMapping("/loginPage")
    public String getLoginPage(Model model, @RequestParam(required=false) String error){
        return "loginPage";
    }

    @RequestMapping(value = "/registration")
    public String getRegistrationPage(Model model){
        return "registrationPage";
    }

    @RequestMapping(value = "/registration/user")
    public String userRegistration(Model model, @ModelAttribute UserDto userDto){

        try {
            userService.createNewUser(userDto);
        } catch (UserNameBusyException e){
            setErrorCode(model, "1");
            return getRegistrationPage(model);
        } catch (Exception e) {
            setErrorCode(model, "2");
            return getRegistrationPage(model);
        }

        return "redirect:/";
    }

    private void setErrorCode(Model model, String error) {

        if(error == null){
            model.addAttribute("errorCode", 0);
        } else {
            model.addAttribute("errorCode", error);
        }




    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void handle(UsernameNotFoundException exception) {
        System.out.println("alarm");
    }

}