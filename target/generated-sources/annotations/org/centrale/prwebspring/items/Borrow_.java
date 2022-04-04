package org.centrale.prwebspring.items;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.prwebspring.items.Book;
import org.centrale.prwebspring.items.Person;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-27T09:28:12", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Borrow.class)
public class Borrow_ { 

    public static volatile SingularAttribute<Borrow, Date> borrowReturn;
    public static volatile SingularAttribute<Borrow, Integer> borrowId;
    public static volatile SingularAttribute<Borrow, Person> personId;
    public static volatile SingularAttribute<Borrow, Date> borrowDate;
    public static volatile SingularAttribute<Borrow, Book> bookId;

}