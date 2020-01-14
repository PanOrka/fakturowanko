package com.fakturowanko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFrameManager implements ActionListener {

    private EditFrame frame;
    private DataExpert data;
    private String current = "";
    private String[] colClient = {"Nazwa", "NIP", "Adres", "Miasto", "Kod pocztowy"};
    private String[] opClient = { "Dodaj", "Usun", "Zmien adres"};
    private String[] colInvoice = {"Id", "Klient", "Produkty"};
    private String[] opInvoice = {"Dodaj", "Usun"};
    private String[] colProduct = {"Id","Nazwa", "Cena", "Dostepnosc"};
    private String[] opProduct = { "Dodaj", "Zmien cene", "Zmien dostepnosc"};

    public EditFrameManager(EditFrame frame){
        this.frame = frame;
        this.data = new DataExpert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.doButton){
            if (current.equals("")) {
                frame.columns.setText("Wybierz kolumne");
                return;
            }
            int op = frame.options.getSelectedIndex();
            if (current.equals("klient")){
                editClient(op);
            } else if (current.equals("faktura")){
                editInvoice(op);
            } else if (current.equals("produkt")){
                editProduct(op);
            }
        }
        else {
            display(e);
        }
    }

    private int getParameter(){
        String answer= JOptionPane.showInputDialog(frame, "Podaj id edytowanego elementu");
        try {
            Integer.parseInt(answer);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Prosze podac liczbe!", "Blad!", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        int par = Integer.parseInt(answer);
        if (par <= 0){
            JOptionPane.showMessageDialog(frame, "Prosze dodatnia liczbe!", "Blad!", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return par;
    }

    private void editClient(int op){
        if (op == 0){
            ClientDataInputPanel dane = new ClientDataInputPanel();
            int dataInput = JOptionPane.showConfirmDialog(frame, dane, "Podaj dane klienta", JOptionPane.OK_CANCEL_OPTION);
            if (dataInput == JOptionPane.OK_OPTION) {
                try {
                    if (!dane.getNip().equals("")) {
                        Integer.parseInt(dane.getNip());
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dane, "Podano nieprawidlowy NIP", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (dataInput == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (dataInput == JOptionPane.CLOSED_OPTION) {
                return;
            }
            data.addClient(frame, dane.getCName(), dane.getAdress(), dane.getCity(), dane.getPostalCode(), dane.getNip());
        } else if (op == 1){
            int par = getParameter();
            data.removeClient(par);
        } else if (op == 2){
            int par = getParameter();
            AdressDataInputPanel dane = new AdressDataInputPanel();
            int dataInput = JOptionPane.showConfirmDialog(frame, dane, "Podaj nowy adres", JOptionPane.OK_CANCEL_OPTION);
            if (dataInput == JOptionPane.OK_OPTION) {
                data.updateAdress(par, dane.getAdress(), dane.getCity(), dane.getPostalCode());
            }
        }
    }

    private void editInvoice(int op){
        if (op == 0){
            int clientId=0;
            int answer = JOptionPane.showConfirmDialog(frame, "Czy klient juz widnieje w bazie?", "Wazne pytanie", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.NO_OPTION) {
                editClient(0);
                data.getNewestClientId();
            }
            else if (answer == JOptionPane.YES_OPTION) {
                String stringId = JOptionPane.showInputDialog(frame, "Podaj ID klienta");
                try {
                    clientId = Integer.parseInt(stringId);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Podano nieprawidlowe ID", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clientId = Integer.parseInt(stringId);
                if (!data.clientChecker(clientId)) {
                    JOptionPane.showMessageDialog(null, "Klient o podanym ID nie istnieje", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (clientId == 0) return;
            new NewInvoiceFrame(clientId, data);
        } else if (op == 1){
            int par = getParameter();
            data.removeInvoice(par);
        }
    }

    private void editProduct(int op){
        if (op == 0){
            ProductDataInputPanel dane = new ProductDataInputPanel();
            int dataInput = JOptionPane.showConfirmDialog(frame, dane, "Podaj dane produktu", JOptionPane.OK_CANCEL_OPTION);
            if (dataInput == JOptionPane.OK_OPTION) {
                try {
                     Double.parseDouble(dane.getPrice());
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Podano nieprawidlowa cene", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (dane.getPName().equals("")) return;
                data.addProduct(dane.getPName(), Double.parseDouble(dane.getPrice()));
            }
        } else if (op == 1) {
            int par = getParameter();
            PriceInputPanel dane = new PriceInputPanel();
            int dataInput = JOptionPane.showConfirmDialog(frame, dane, "Podaj nowa cene produktu", JOptionPane.OK_CANCEL_OPTION);
            if (dataInput == JOptionPane.OK_OPTION) {
                try {
                    Double.parseDouble(dane.getPrice());
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Podano nieprawidlowa cene", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                data.updatePrice(par, Double.parseDouble(dane.getPrice()));
            }
        } else if (op == 2){
            int par = getParameter();
            data.updateState(par);
        }
    }

    private void display(ActionEvent e){
        StringBuilder build = new StringBuilder("Kolumny:");
        frame.options.removeAllItems();
        String[] tableCol;
        String[] tableOp;
        if (e.getSource() == frame.productButton ){
            tableCol = colProduct;
            tableOp = opProduct;
            current = "produkt";
        } else if (e.getSource() == frame.clientButton){
            tableCol = colClient;
            tableOp = opClient;
            current = "klient";
        } else{
            tableCol = colInvoice;
            tableOp = opInvoice;
            current = "faktura";
        }
        for (int i=0;i<tableCol.length;i++){
            build.append("\n").append(tableCol[i]);
        }
        frame.columns.setText(build.toString());
        for (int i=0;i<tableOp.length;i++){
            frame.options.addItem(tableOp[i]);
        }
    }
}

