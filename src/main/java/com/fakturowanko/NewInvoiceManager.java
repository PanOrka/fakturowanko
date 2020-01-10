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

    NewInvoiceFrame frame;
    DataExpert dataExpert;

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
        if (e.getSource() == frame.addProduct) {
            addProduct();
        }
        else if(e.getSource() == frame.finalizeInvoice) {
            dataExpert.addInvoice(frame.newInvoice);

            frame.setVisible(false);
            frame.dispose();
        }
    }

    /**
     * obsluguje dodawanie pojedynczego produktu do faktury
     */
    private void addProduct() {
        String answerP = (String)JOptionPane.showInputDialog(frame, "Wybierz produkt", "Nowy produkt", JOptionPane.QUESTION_MESSAGE,null, frame.productNames,frame.productNames[0]);
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
        frame.newInvoice.addProduct(newProduct, quantity);
        updateDisplay();
    }

    /**
     * odswieza liste dodanych produktow wyswietlana w ramce
     */
    private void updateDisplay() {
        String display = "Dodane produkty:";
        for(int i=0;i<frame.newInvoice.getProductsQuantity();i++) {
            display+="\n"+dataExpert.getProductName(frame.newInvoice.getProductId(i))+" x "+frame.newInvoice.productQ.get(i).getProductQuantity();
        }
        frame.addedProducts.setText(display);
    }
}

