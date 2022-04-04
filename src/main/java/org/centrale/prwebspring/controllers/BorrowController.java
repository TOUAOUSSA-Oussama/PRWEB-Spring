/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Borrow;
import org.centrale.prwebspring.items.Person;
import org.centrale.prwebspring.repositories.BookRepository;
import org.centrale.prwebspring.repositories.BorrowRepository;
import org.centrale.prwebspring.repositories.PersonRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
public class BorrowController {
    @Autowired
    private BorrowRepository myBorrowRepository;
    @Autowired
    private BookRepository myBookRepository;
    @Autowired
    private PersonRepository myPersonRepository;
    
    // gerer borrow return call
    @RequestMapping(value = "returnBorrow.do", method=RequestMethod.POST)
    public ModelAndView handleReturnPost(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject returnedObject = new JSONObject();
        
        String borrowStr = request.getParameter("id");
        int borrowId = -1;
        
        try {
            borrowId = Integer.parseInt(borrowStr);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }
        
        Borrow borrow = myBorrowRepository.returnBook(borrowId);
        if (borrow != null) {
            returnedObject.append("id", borrow.getBookId());
        } else {
            returned.setStatus(HttpStatus.BAD_REQUEST);
        }
        returned.addObject("theResponse", returnedObject.toString());
        return returned;
    }
    
    // gerer borrow return call
    @RequestMapping(value = "addBorrow.do", method=RequestMethod.POST)
    public ModelAndView handleAddPost(HttpServletRequest request) {
        Borrow newBorrow = new Borrow();
        // generation d'id :
        newBorrow.setBorrowId(myBorrowRepository.findAll().size()+100);
        // generation de la date actuelle :
        Calendar aCalendar = Calendar.getInstance();
        Date date = aCalendar.getTime();
        newBorrow.setBorrowDate(date);
        // generation du book a emprunté :
        String bookId = request.getParameter("bookID");
        int bookIdInt = -1;
        try {
            bookIdInt = Integer.parseInt(bookId);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }
        Optional<Book> book = myBookRepository.findById(bookIdInt);
        newBorrow.setBookId(book.get());
        // generation du personne qui va emprunté :
        String personId = request.getParameter("userId");
        int personIdInt = -1;
        try {
            personIdInt = Integer.parseInt(personId);
        } catch (NumberFormatException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, null, ex);
        }
        Optional<Person> person = myPersonRepository.findById(personIdInt);
        newBorrow.setPersonId(person.get());
        // enregistrer ceci dans la base de données :
        myBorrowRepository.save(newBorrow);
        
        // revenir vers la page editUser.do : 
        ModelAndView returned;
        List<Person> myList = myPersonRepository.findAll();
        returned = new ModelAndView("users");
        returned.addObject("usersList", myList);
        return returned;
    }
}
