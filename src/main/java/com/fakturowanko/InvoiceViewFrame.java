package com.fakturowanko;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * klasa wyswietlajaca fakture
 * @author dszyd
 *
 */
public class InvoiceViewFrame extends JFrame {

    private DataExpert dataExpert;
    protected Invoice invoice;
    private JPanel clientData;
    private Client client;
    private JLabel clientNS;
    private JLabel clientA;
    private JLabel clientC;
    private JLabel clientNIP;
    private JTextArea products;

    /**
     * wyswietla fakture
     * @param dataExpert baza danych
     * @param invoiceId id faktury
     */
    InvoiceViewFrame(DataExpert dataExpert, int invoiceId){
        super("Wyswietlanie faktury");
        setSize(700,300);
        setLocationRelativeTo(null);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
        setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

        //TODO ma nie byc dataexperta, tylko pobieranie faktury z bazy po id
        this.dataExpert = dataExpert;
        invoice = dataExpert.getInvoice(invoiceId);
        //TODO tu ma byc pobieranie klienta z bazy
        client = dataExpert.getClient(invoice.getClientId());

        clientData = new JPanel();
        clientData.setLayout(new GridLayout(4,1));

        clientNS = new JLabel(client.getName());
        clientData.add(clientNS);

        clientNIP = new JLabel(client.getNip());
        clientData.add(clientNIP);

        clientA = new JLabel(client.getAdress());
        clientData.add(clientA);

        clientC = new JLabel(client.getCity()+" "+client.getPostal_code());
        clientData.add(clientC);

        add(clientData);
        products = new JTextArea();
        products.setEditable(false);
        products.setText(setUpData());
        add(products);
        setVisible(true);
    }

    /**
     * przygotowuje dane produktow do wyswietlenia
     * @return dane do wyswitlenia
     */
    private String setUpData() {
        String data = "Lp.\t|NAZWA\t|ILOSC\t|CENA\t|NETTO\t|VAT\t|BRUTTO";
        for(int i=0;i<invoice.getProductsQuantity();i++) {
            //TODO szukanie ceny po id produktu
            double price = dataExpert.getProductPrice(invoice.productQ.get(i).getProductId());
            int quantity = invoice.productQ.get(i).getProductQuantity();
            double subTotal = getSubTotal(price, quantity);
            data += "\n"+Integer.toString(i+1)+"\t|";
            data += dataExpert.getProductName(invoice.getProductId(i))+"\t|";
            data += Integer.toString(quantity)+"\t|";
            data += getFormat(price)+"\t|";
            data += getNetto(subTotal)+"\t|";
            data += getVAT(subTotal)+"\t|";
            data += getFormat(subTotal);
        }
        double total = getTotal();
        data += "\n\n\t\t\tSUMA\t|"+getNetto(total)+"\t|"+getVAT(total)+"\t|"+getFormat(total);
        return data;
    }

    /**
     * liczy netto z kwoty
     * @param d kwota
     * @return netto z kwoty
     */
    private String getNetto (double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(d*0.77);
    }

    /**
     * liczy vat z kwoty
     * @param d kwota
     * @return vat z kwoty
     */
    private String getVAT (double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(d*0.23);
    }

    /**
     * liczy sume posrednia
     * @param p cena produktu
     * @param q ilosc produktu
     * @return suma posrednia
     */
    private double getSubTotal(double p, int q) {
        return q*p;
    }

    /**
     * liczy calkowita kwote faktury
     * @return calkowita kwota faktury
     */
    protected double getTotal() {
        double total = 0.0;
        for(int i=0;i<invoice.getProductsQuantity();i++) {
            //TODO do product quantity trzeba dopisac pole z cena w momencie zakupu i tu z niej skorzystac
            total += dataExpert.getProductPrice(invoice.productQ.get(i).getProductId()) * invoice.productQ.get(i).getProductQuantity();
        }
        return total;
    }

    /**
     * formatuje kwote do postaci #.##
     * @param number kwota
     * @return sformatowana kwota
     */
    private String getFormat(double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(number);
    }
}
