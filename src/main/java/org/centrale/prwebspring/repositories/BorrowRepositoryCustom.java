/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.prwebspring.repositories;

import java.util.Date;
import org.centrale.prwebspring.items.Borrow;

/**
 *
 * @author admin
 */
public interface BorrowRepositoryCustom {
    public Borrow returnBook(Borrow item, Date date);
    
    public Borrow returnBook(Borrow item);
    
    public Borrow returnBook(int borrowId);
}
