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

            Person person = session.get(Person.class, 3);
            List<Item> items = person.getItems();

            System.out.println(items);

            for (Item item : items) {
                session.remove(item);
            }
            System.out.println(items);

            person.getItems().clear();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
