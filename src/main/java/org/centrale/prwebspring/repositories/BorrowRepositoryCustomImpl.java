/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.centrale.prwebspring.items.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public class BorrowRepositoryCustomImpl implements BorrowRepositoryCustom {
    @Autowired
    @Lazy
    BorrowRepository repository;
    
    @Override
    public Borrow returnBook(Borrow item, Date date){
        if((item != null) && (date != null)) {
            item.setBorrowReturn(date);
            repository.save(item);
            return item;
        }
        return null;
    }
    
    @Override
    public Borrow returnBook(Borrow item) {
        Calendar aCalendar = Calendar.getInstance();
        Date date = aCalendar.getTime();
        return returnBook(item, date);
    }
    
    @Override
    public Borrow returnBook(int borrowId) {
        if( borrowId > 0) {
            Optional<Borrow> item = repository.findById(borrowId);
            if (item.get() != null){
                return returnBook(item.get());
            }
        }
        return null;
    }
}
