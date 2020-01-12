package com.fakturowanko;

import com.fakturowanko.db.HibernateUtil;
import com.fakturowanko.db.ProduktEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * main
 * @author dszyd
 *
 */
public class Main {

    public static void main(String[] args) {
        MainFrame f = new MainFrame();

        /*Transaction transaction = null;

        ProduktEntity pe = new ProduktEntity("ogorek", 20.0, true);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction

            transaction = session.beginTransaction();

            // save the student object

            session.save(pe);

            // commit transaction

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {

                //transaction.rollback();

            }

            e.printStackTrace();

        }*/
    }

}
