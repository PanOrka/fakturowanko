package com.fakturowanko;

import com.fakturowanko.db.*;
import org.hibernate.SQLQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * klasa obslugujaca baze danych
 * @author dszyd
 *
 */
public class DataExpert {

    protected int addClient(JFrame frame, String name, String adress, String city, String postalC, String nip) {
        KlientEntity client;
        if (name.equals("") || adress.equals("") || city.equals("") || postalC.equals("")) {
            client = new KlientEntity(null, null, null, null, null);
        } else if (nip.equals("")){
            client = new KlientEntity(name, null, city, postalC,adress);
        } else {
            client = new KlientEntity(name, nip, city, postalC,adress);
        }

        int actualId = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            actualId = (int)session.save(client);
            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            JOptionPane.showMessageDialog(frame, "Bad Input data", "Achtung!!!", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        return actualId;
    }

    protected int getNewestClientId(){
        int id = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             String hql = "SELECT MAX(Klient.idKlienta) FROM KlientEntity Klient";
             Query hqlQuery = session.createQuery(hql);
             List results = hqlQuery.list();
             id = (int)results.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    protected void removeInvoice(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE FakturyEntity WHERE idFaktury = "+id;
            Query q = session.createQuery(hql);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Podano zle dane", "Achtung!!!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    protected void updateAdress(int id, String adres, String miasto, String kod){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql1 = "UPDATE KlientEntity Klient set Klient.adres = :adres" + " WHERE Klient.idKlienta = :id";
            Query query1 = session.createQuery(hql1);
            query1.setParameter("adres", adres);
            query1.setParameter("id",id);
            query1.executeUpdate();
            String hql2 = "UPDATE KlientEntity Klient set Klient.miasto = :miasto" + " WHERE Klient.idKlienta = :id";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("miasto", miasto);
            query2.setParameter("id",id);
            query2.executeUpdate();
            String hql3 = "UPDATE KlientEntity Klient set Klient.kodPocztowy = :kod" + " WHERE Klient.idKlienta = :id";
            Query query3 = session.createQuery(hql3);
            query3.setParameter("kod", kod);
            query3.setParameter("id",id);
            query3.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void removeClient(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE KlientEntity Klient WHERE Klient.idKlienta = " + id;
            Query q = session.createQuery(hql);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Podano zle dane", "Achtung!!!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    protected void addProduct(String name, double price){
        ProduktEntity product = new ProduktEntity(name, price, true);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    protected void updatePrice(int id, double price){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "UPDATE ProduktEntity Produkt set Produkt.cena = :cena" + " WHERE Produkt.idProduktu = :id";
            Query query = session.createQuery(hql);
            query.setParameter("cena", price);
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void updateState(int id){
        List<ProduktEntity> results = new ArrayList();
        boolean state;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql1 = "FROM ProduktEntity Produkt WHERE Produkt.id = " + id;
            Query hqlQuery = session.createQuery(hql1);
            results = hqlQuery.list();
            if (results.get(0).getSprzedawany()){
                state = false;
            }
            else {
                state = true;
            }
            String hql = "UPDATE ProduktEntity Produkt set Produkt.sprzedawany = :stan" + " WHERE Produkt.idProduktu = :id";
            Query query = session.createQuery(hql);
            query.setParameter("stan",state);
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addInvoice(List<ChoosenProds> choosenProds, KlientEntity clientId) {
        List<java.sql.Date> results;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            /*
            // TUTAJ SQL BO CURDATE NIE DZIALA NO I MARIADB MA CURDATE()
             */
            SQLQuery hqlQuery = session.createSQLQuery("SELECT CURDATE()");

            results = hqlQuery.list();
            java.sql.Date date = results.get(0);
            System.out.println(date);

            FakturyEntity fe = new FakturyEntity(clientId, date);
            session.beginTransaction();
            session.save(fe);

            for (ChoosenProds cp: choosenProds) {
                Query getProductQuery = session.createQuery("From ProduktEntity WHERE idProduktu = " + cp.getProduct_id());
                List<ProduktEntity> products = getProductQuery.list();

                IloscProduktuEntity ilp = new IloscProduktuEntity(fe, products.get(0), cp.getQuantity(), products.get(0).getCena());
                session.save(ilp);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public boolean clientChecker(int clientId) {
        List<Long> results = new ArrayList<>();
        results.add((long)0);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(*) FROM KlientEntity Klient WHERE idKlienta = ?1";
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.setParameter(1, clientId).list();
            System.out.println(results.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results.get(0).equals((long)1);
    }

    public KlientEntity getClient(int index) {
        List<KlientEntity> results = new ArrayList();
        results.add(null);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM KlientEntity Klient WHERE Klient.idKlienta = " + index;
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results.get(0);
    }

    public List getSoldProductList() {
        List<ProductQuantity> results = new ArrayList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ProduktEntity Products WHERE Products.sprzedawany = " + 1;
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List getWholeTable(String name){
        if(name.equals("produkt")){
            name = "ProduktEntity";
        } else if(name.equals("klient")){
            name = "KlientEntity";
        } else if(name.equals("faktura")){
            name = "FakturyEntity";
        }
        List results = new ArrayList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + name;
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    protected FakturyEntity getInvoice(int index) {
        List<FakturyEntity> results = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM FakturyEntity WHERE idFaktury = ?1";
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.setParameter(1, index).list();
        }
        return results.get(0);
    }

    protected boolean invoiceChecker(int invoiceId) {
        List<Long> results = new ArrayList<>();
        results.add((long)0);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(*) FROM FakturyEntity Faktura WHERE idFaktury = ?1";
            Query hqlQuery = session.createQuery(hql);
            results = hqlQuery.setParameter(1, invoiceId).list();
            System.out.println(results.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results.get(0).equals((long)1);
    }

    public List<IloscProduktuEntity> getProductsOfInvoice(int invoiceId) {
        List<IloscProduktuEntity> ipe = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM IloscProduktuEntity WHERE id_faktury.idFaktury = ?1";
            Query query = session.createQuery(hql);
            ipe = query.setParameter(1, invoiceId).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipe;
    }
}
