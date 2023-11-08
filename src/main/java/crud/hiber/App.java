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

            Person person = new Person("Cascading test222", 30);

            Item item = new Item("Test cascading item2222", person);

            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            session.save(person);

//            session.save(item);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
