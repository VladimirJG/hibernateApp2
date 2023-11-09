package crud.hiber;

import crud.hiber.model.Item;
import crud.hiber.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

          /*  Person person = new Person("Cascading test222", 30);
            person.addItem(new Item("Test cascading item5555"));
            person.addItem(new Item("Test cascading item8888"));
            person.addItem(new Item("Test cascading item7777"));


            session.save(person);*/

//            222

            Person person = session.get(Person.class, 1);
            System.out.println(person.getItems());


            session.getTransaction().commit();

            System.out.println("without session");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();
//session.close();

            System.out.println(person.getItems());

        } finally {
            sessionFactory.close();
        }
    }
}
