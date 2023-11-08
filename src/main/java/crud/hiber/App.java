package crud.hiber;

import crud.hiber.model.Item;
import crud.hiber.model.Person;
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

            Person person1 = new Person("Tommy Lee Jones", 98);
            Item newItem = new Item("Skovoroda", person1);

            person1.setItems(new ArrayList<>(Collections.singletonList(newItem)));

            session.save(person1);
            session.save(newItem);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
