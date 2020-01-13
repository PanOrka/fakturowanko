package com.fakturowanko;

import com.fakturowanko.db.FakturyEntity;
import com.fakturowanko.db.IloscProduktuEntity;
import com.fakturowanko.db.KlientEntity;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

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
    protected FakturyEntity invoice;
    private JPanel clientData;
    private KlientEntity client;
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

        this.dataExpert = dataExpert;

        this.invoice = dataExpert.getInvoice(invoiceId);
        //TODO tu ma byc pobieranie klienta z bazy
        client = dataExpert.getClient(invoice.getIdKlienta().getIdKlienta());

        clientData = new JPanel();
        clientData.setLayout(new GridLayout(4,1));

        clientNS = new JLabel(client.getNazwa());
        clientData.add(clientNS);

        clientNIP = new JLabel(client.getNip());
        clientData.add(clientNIP);

        clientA = new JLabel(client.getAdres());
        clientData.add(clientA);

        clientC = new JLabel(client.getMiasto()+" "+client.getKodPocztowy());
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
        List<IloscProduktuEntity> ipe = dataExpert.getProductsOfInvoice(invoice.getIdFaktury());

        for(int i=0; i<ipe.size(); i++) {
            //TODO szukanie ceny po id produktu
            double price = ipe.get(i).getCenaZakupu();
            int quantity = ipe.get(i).getIlosc();
            double subTotal = getSubTotal(price, quantity);
            data += "\n"+Integer.toString(i+1)+"\t|";
            data += ipe.get(i).getProdukt().getNazwa()+"\t|";
            data += Integer.toString(quantity)+"\t|";
            data += getFormat(price)+"\t|";
            data += getNetto(subTotal)+"\t|";
            data += getVAT(subTotal)+"\t|";
            data += getFormat(subTotal);
        }
        double total = getTotal(ipe);
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
    protected double getTotal(List<IloscProduktuEntity> ipe) {
        double total = 0.0;
        for(int i=0;i<ipe.size();i++) {
            //TODO do product quantity trzeba dopisac pole z cena w momencie zakupu i tu z niej skorzystac
            total += ipe.get(i).getCenaZakupu()*ipe.get(i).getIlosc();
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
