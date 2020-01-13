package com.fakturowanko;

import com.fakturowanko.db.KlientEntity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * klasa obslugujaca wprowadzanie nowej faktury
 * @author dszyd
 *
 */
public class NewInvoiceManager implements ActionListener{

    private NewInvoiceFrame frame;
    private DataExpert dataExpert;
    private List<ChoosenProds> choosenProds;
    private KlientEntity clientId;

    /**
     * konstruktor
     * @param frame ramka do wprowadzania faktury
     * @param dataExpert manager bazy danych
     */
    NewInvoiceManager(NewInvoiceFrame frame, DataExpert dataExpert, KlientEntity clientId){
        this.clientId = clientId;
        this.frame = frame;
        this.dataExpert = dataExpert;
        this.choosenProds = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.addProduct && frame.productFinder) {
            addProduct();
        } else if(e.getSource() == frame.finalizeInvoice) {

            //TODO dodawanie nowej fakturki

            if (choosenProds.size() > 0) {
                this.dataExpert.addInvoice(choosenProds, clientId);
            }

            //dataExpert.addInvoice(frame.newInvoice);

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

        if (answerP != null) {
            String answerQ = JOptionPane.showInputDialog(frame, "Podaj ilosc produktu");
            try {
                int quantity = Integer.parseInt(answerQ);
                if (quantity<=0) throw new NumberFormatException();
                //TODO dodawanie odpowiednich rekordow do ilosc_produktu
                this.parseAdd(answerP, quantity);
                updateDisplay();
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Podano nieprawidlowa liczbe", "Blad!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void parseAdd(String answer, int quantity) throws NumberFormatException {
        String[] splitted = answer.split(":", 2);
        System.out.println(splitted[0]);
        int id = Integer.parseInt(splitted[0]);
        choosenProds.add(new ChoosenProds(id, splitted[1], quantity));
    }

    /**
     * odswieza liste dodanych produktow wyswietlana w ramce
     */
    private void updateDisplay() {
        String display = "Dodane produkty:";

        for (ChoosenProds cp: choosenProds) {
            display += "\n" + cp.getName() + " x " + cp.getQuantity();
        }

        frame.addedProducts.setText(display);

//        for(int i=0;i<frame.newInvoice.getProductsQuantity();i++) {
//            //TODO znajdowanie nazwy produktu i ilosci dla danej faktury
//            display+="\n"+dataExpert.getProductName(frame.newInvoice.getProductId(i))+" x "+frame.newInvoice.productQ.get(i).getProductQuantity();
//        }
//        frame.addedProducts.setText(display);
    }
}

