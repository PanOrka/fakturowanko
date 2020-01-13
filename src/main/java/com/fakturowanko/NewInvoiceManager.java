package com.fakturowanko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * klasa obslugujaca wprowadzanie nowej faktury
 * @author dszyd
 *
 */
public class NewInvoiceManager implements ActionListener{

    private NewInvoiceFrame frame;
    private DataExpert dataExpert;

    /**
     * konstruktor
     * @param frame ramka do wprowadzania faktury
     * @param dataExpert manager bazy danych
     */
    NewInvoiceManager(NewInvoiceFrame frame, DataExpert dataExpert){
        this.frame = frame;
        this.dataExpert = dataExpert;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.addProduct && frame.productFinder) {
            addProduct();
        } else if(e.getSource() == frame.finalizeInvoice) {

            //TODO dodawanie nowej fakturki
            dataExpert.addInvoice(frame.newInvoice);

            frame.setVisible(false);
            frame.dispose();
        } else if (e.getSource() == frame.addProduct) {
            JOptionPane.showMessageDialog(frame, "No products yet!", "Achtung!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * obsluguje dodawanie pojedynczego produktu do faktury
     */
    private void addProduct() {
        String answerP = (String)JOptionPane.showInputDialog(frame, "Wybierz produkt", "Nowy produkt", JOptionPane.QUESTION_MESSAGE,null, frame.productNames,frame.productNames[0]);
        //TODO znajdowanie produktu po nazwie
        Product newProduct = dataExpert.getProduct(answerP);
        String answerQ = JOptionPane.showInputDialog(frame, "Podaj ilosc produktu");
        try {
            int quantity = Integer.parseInt(answerQ);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Podano nieprawidlowa liczbe", "Blad!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(answerQ);
        if (quantity<=0) return;
        //TODO dodawanie odpowiednich rekordow do ilosc_produktu
        frame.newInvoice.addProduct(newProduct, quantity);
        updateDisplay();
    }

    /**
     * odswieza liste dodanych produktow wyswietlana w ramce
     */
    private void updateDisplay() {
        String display = "Dodane produkty:";
        for(int i=0;i<frame.newInvoice.getProductsQuantity();i++) {
            //TODO znajdowanie nazwy produktu i ilosci dla danej faktury
            display+="\n"+dataExpert.getProductName(frame.newInvoice.getProductId(i))+" x "+frame.newInvoice.productQ.get(i).getProductQuantity();
        }
        frame.addedProducts.setText(display);
    }
}

