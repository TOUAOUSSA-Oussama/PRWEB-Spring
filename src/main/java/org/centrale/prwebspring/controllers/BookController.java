/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.repositories.BookRepository;
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
public class BookController {

    // Instanciation : 
    @Autowired
    private BookRepository myBookRepository;

    // pour editer une personne (editUser)
    @RequestMapping(value = "editBook.do", method=RequestMethod.POST)
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
            Optional<Book> book = myBookRepository.findById(id);
            returned = new ModelAndView("book");
            returned.addObject("book", book.get());
        } else {
            List<Book> myList = myBookRepository.findAll();
            returned = new ModelAndView("books");
            returned.addObject("booksList", myList);
        }
        
        return returned;
    }
    
    // Enregistrer un utilisateur : 
    @RequestMapping(value = "saveBook.do", method=RequestMethod.POST)
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
            Optional<Book> book = myBookRepository.findById(id);
            
            Book updatedBook = book.get();
            updatedBook.setBookTitle(request.getParameter("BookTitle"));
            updatedBook.setBookAuthors(request.getParameter("BookAuthors"));
            
            // enregistrer l'utilisateur dans la base de données :
            myBookRepository.save(updatedBook);
        }
        // si l'id est négative, on doit ajouter un nouveau utilisateur :
        else {
            Book newBook = new Book();
            newBook.setBookId(myBookRepository.findAll().size()+100);
            newBook.setBookTitle(request.getParameter("BookTitle"));
            newBook.setBookAuthors(request.getParameter("BookAuthors"));
            myBookRepository.save(newBook);
        }
        
        // retourner la liste des utilisateurs
        List<Book> myList = myBookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("booksList", myList);
        return returned;
    }
    
    // supprimer un utilisateur : 
    @RequestMapping(value = "deleteBook.do", method=RequestMethod.POST)
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
            Optional<Book> book = myBookRepository.findById(id);
            Book BookToDelete = book.get();
            myBookRepository.delete(BookToDelete);
        }
        // retourner la liste des utilisateurs
        List<Book> myList = myBookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("booksList", myList);
        return returned;
    }
    
    //Ajouter un nouveau utilisateur :
    @RequestMapping(value = "createBook.do", method=RequestMethod.POST)
    public ModelAndView handleDeletePost() {
        Book book = new Book();
        ModelAndView returned = new ModelAndView("book");
        returned.addObject("book", book);
        return returned;
    }
    
    // switchToBooks :
    @RequestMapping(value = "switchToBooks.do", method=RequestMethod.POST)
    public ModelAndView handleswitchToBooksPost() {
        ModelAndView returned = new ModelAndView("books");
        List<Book> myList = myBookRepository.findAll();
        returned = new ModelAndView("books");
        returned.addObject("booksList", myList);
        return returned;
    }
}
