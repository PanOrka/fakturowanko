package com.fakturowanko;

import com.fakturowanko.db.FakturyEntity;
import com.fakturowanko.db.HibernateUtil;
import com.fakturowanko.db.IloscProduktuEntity;
import com.fakturowanko.db.KlientEntity;
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

    protected ArrayList<Client> clientList;
    protected ArrayList<Product> productList;
    protected ArrayList<Invoice> invoiceList;

    DataExpert(){
        clientList = new ArrayList<>();
        productList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        addProduct(1, "Pad thai", 22.0);
        addProduct(2, "Krewetki", 34.50);
        addProduct(3, "Hummus", 15.0);
        addProduct(4, "Woda 0.5L", 3.90);
        addProduct(5, "Hopium Ale", 9.80);
    }

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
            session.save(client);
            session.getTransaction().commit();

            String hql = "SELECT MAX(Klient.idKlienta) FROM KlientEntity Klient";
            Query hqlQuery = session.createQuery(hql);
            List results = hqlQuery.list();
            actualId = (int)results.get(0);

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            JOptionPane.showMessageDialog(frame, "Bad Input data", "Achtung!!!", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        return actualId;
    }

    protected void addProduct(int id, String name, double price){
        Product product = new Product(id, name, price);
        productList.add(product);
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

    protected void addInvoice(Invoice invoice){
        invoiceList.add(invoice);
    }

    protected int getNewClientId() {
        return clientList.size()+1;
    }

    protected int getNewProductId() {
        return productList.size()+1;
    }

    protected int getNewInvoiceId() {
        return invoiceList.size()+1;
    }

    protected Product getProduct(String name) {
        for (int i=0;i<productList.size();i++) {
            if(productList.get(i).getName().equals(name)) {
                return productList.get(i);
            }
        }
        return null;
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

    protected String getProductName(int index) {
        for(int i=0;i<productList.size();i++) {
            if(productList.get(i).getProductId()==index) return productList.get(i).getName();
        }
        return null;
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

    protected double getProductPrice(int index) {
        for (int i=0;i<productList.size();i++) {
            if(productList.get(i).getProductId()==index) return productList.get(i).getPrice();
        }
        return 0.0;
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
