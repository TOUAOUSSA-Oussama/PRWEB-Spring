package org.centrale.prwebspring.items;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.prwebspring.items.Borrow;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-27T09:28:12", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, Date> personBirthdate;
    public static volatile CollectionAttribute<Person, Borrow> borrowCollection;
    public static volatile SingularAttribute<Person, Integer> personId;
    public static volatile SingularAttribute<Person, String> personLastname;
    public static volatile SingularAttribute<Person, String> personFirstname;

}