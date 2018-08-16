package br.project.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project Account
 * @author Ronaldo Torre
 */
@Controller("accountController")
@RequestMapping("/home")
public class AccountController {
    
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String home(){
    	 return "/home/index";
    }
    
}