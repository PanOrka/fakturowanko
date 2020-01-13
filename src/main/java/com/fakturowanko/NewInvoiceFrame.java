package com.fakturowanko;

import com.fakturowanko.db.KlientEntity;
import com.fakturowanko.db.ProduktEntity;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * ramka oblugujaca wprowadzanie nowej faktury
 * @author dszyd
 *
 */
public class NewInvoiceFrame extends JFrame{

    private JPanel clientData;
    private JLabel clientNS;
    private JLabel clientA;
    private JLabel clientC;
    private JLabel clientNIP;
    protected JButton addProduct;
    protected JButton finalizeInvoice;
    protected JTextArea addedProducts;

    protected boolean productFinder;
    protected Object[] productNames;
    private List<ProduktEntity> productList;
    protected Invoice newInvoice;
    private KlientEntity client;

    /**
     * defaultowy konstruktor
     * @param clientId id klienta na ktorego bedzie wystawiana faktura
     * @param dataExpert manager bazy danych
     */
    NewInvoiceFrame(int clientId, DataExpert dataExpert){
        super("Nowa faktura");
        setSize(450,500);
        setLocationRelativeTo(null);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
        setLayout(new FlowLayout(FlowLayout.LEFT,30,30));

        productList = dataExpert.getSoldProductList();

        if (productList.size() > 0) {
            productFinder = true;
            productNames = new Object[productList.size()];
            for (int i=0; i < productList.size(); i++) {
                productNames[i] = productList.get(i).getIdProduktu() + ":" +productList.get(i).getNazwa();
            }
        } else {
            productFinder = false;
        }

        //TODO dodawanko faktury z id klienta
        //newInvoice = new Invoice(dataExpert.getNewInvoiceId(), clientId);

        client = dataExpert.getClient(clientId);

        NewInvoiceManager manager = new NewInvoiceManager(this, dataExpert, client);

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

        addProduct = new JButton("Dodaj produkt");
        add(addProduct);
        addProduct.addActionListener(manager);

        finalizeInvoice = new JButton("Zakoncz");
        add(finalizeInvoice);
        finalizeInvoice.addActionListener(manager);

        addedProducts = new JTextArea("Dodane produkty:");
        addedProducts.setEditable(false);
        add(addedProducts);

        setVisible(true);
    }
}

