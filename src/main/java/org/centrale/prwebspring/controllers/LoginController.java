/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Person;
import org.centrale.prwebspring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
public class LoginController {
    // Instanciation : 
    @Autowired
    private PersonRepository myPersonRepository;
    
//    page d'acceuil
    @RequestMapping(value="index.do", method=RequestMethod.GET)
    public ModelAndView handleGet(){
        ModelAndView returned;
        returned = new ModelAndView("index");
        return returned;
    }
    
    // vérifier les identifiants :
    @RequestMapping(value="index.do", method=RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request){
        ModelAndView returned;
        
        // récupérer les champs de la forme
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        
        if ((login != null) && (pass != null) && (login.equals("admin")) && (pass.equals("admin"))){
            // récuperer la liste et l'envoyer à la page users: 
            List<Person> myList = myPersonRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("usersList", myList);
        } else {
            returned = new ModelAndView("index");
        }
        
        return returned;
    }
    
}
