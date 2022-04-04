package org.centrale.prwebspring.items;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.prwebspring.items.Borrow;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-27T09:28:12", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> bookAuthors;
    public static volatile CollectionAttribute<Book, Borrow> borrowCollection;
    public static volatile SingularAttribute<Book, Integer> bookId;
    public static volatile SingularAttribute<Book, String> bookTitle;

}