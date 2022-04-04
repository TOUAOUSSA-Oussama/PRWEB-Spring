/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Person;
import org.centrale.prwebspring.repositories.BookRepository;
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
public class PersonController {

    // Instanciation : 
    @Autowired
    private PersonRepository myPersonRepository;
    @Autowired
    private BookRepository myBookRepository;

    // pour editer une personne (editUser)
    @RequestMapping(value = "editUser.do", method=RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request) {
        ModelAndView returned;
        
        String idStr = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }

        if (id > 0) {
            Optional<Person> person = myPersonRepository.findById(id);
            returned = new ModelAndView("user");
            returned.addObject("user", person.get());
            List<Book> booksList = myBookRepository.findAll();
            returned.addObject("booksList", booksList);
        } else {
            List<Person> myList = myPersonRepository.findAll();
            returned = new ModelAndView("users");
            returned.addObject("usersList", myList);
            
        }
        
        return returned;
    }
    
    // Enregistrer un utilisateur : 
    @RequestMapping(value = "saveUser.do", method=RequestMethod.POST)
    public ModelAndView handleSavePost(HttpServletRequest request) {
        ModelAndView returned;
        
        // save the person
        String idStr = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }
        // si l'id est positive, on modifie l'utilisateur
        if (id > 0) {
            Optional<Person> person = myPersonRepository.findById(id);
            
            Person updatedUser = person.get();
            updatedUser.setPersonFirstname(request.getParameter("FirstName"));
            updatedUser.setPersonLastname(request.getParameter("LastName"));
            String birthdateStr = request.getParameter("Birthdate");
            updatedUser.setPersonBirthdate(getDateFromString(birthdateStr, "yyyy-MM-dd"));
            
            // enregistrer l'utilisateur dans la base de données :
            myPersonRepository.save(updatedUser);
        }
        // si l'id est négative, on doit ajouter un nouveau utilisateur :
        else {
            Person newPerson = new Person();
            newPerson.setPersonId(myPersonRepository.findAll().size()+100);
            newPerson.setPersonFirstname(request.getParameter("FirstName"));
            newPerson.setPersonLastname(request.getParameter("LastName"));
            String birthdateStr = request.getParameter("Birthdate");
            newPerson.setPersonBirthdate(getDateFromString(birthdateStr, "yyyy-MM-dd"));
            myPersonRepository.save(newPerson);
        }
        
        // retourner la liste des utilisateurs
        List<Person> myList = myPersonRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("usersList", myList);
        return returned;
    }
    
    // convert STR date to Date format    
    private Date getDateFromString(String aDate, String format) {
        Date returnedValue = null;
        try {
            // try to convert
            SimpleDateFormat aFormater = new SimpleDateFormat(format);
            returnedValue = aFormater.parse(aDate);
        } catch (ParseException ex) {
        }
        
        if (returnedValue != null) {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(returnedValue);
        }
        return returnedValue;
    }
    
    // supprimer un utilisateur : 
    @RequestMapping(value = "deleteUser.do", method=RequestMethod.POST)
    public ModelAndView handleDeletePost(HttpServletRequest request) {
        ModelAndView returned;
        
        // recuperer l'id
        String idStr = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }
        // supprimer cet utilisateur :
        if (id > 0) {
            Optional<Person> person = myPersonRepository.findById(id);
            Person UserToDelete = person.get();
            myPersonRepository.delete(UserToDelete);
        }
        // retourner la liste des utilisateurs
        List<Person> myList = myPersonRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("usersList", myList);
        return returned;
    }
    
    //Ajouter un nouveau utilisateur :
    @RequestMapping(value = "createUser.do", method=RequestMethod.POST)
    public ModelAndView handleDeletePost() {
        Person person = new Person();
        ModelAndView returned = new ModelAndView("user");
        returned.addObject("user", person);
        return returned;
    }
    
    // switchToUsers :
    @RequestMapping(value = "switchToUsers.do", method=RequestMethod.POST)
    public ModelAndView handleswitchToUsersPost() {
        ModelAndView returned = new ModelAndView("users");
        List<Person> myList = myPersonRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("usersList", myList);
        return returned;
    }
}
