package com.fakturowanko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFrameManager implements ActionListener {

    private EditFrame frame;
    private String current = "";
    private String[] colClient = {"Nazwa", "NIP", "Adres", "Miasto", "Kod pocztowy"};
    private String[] opClient = { "Dodaj", "Usun", "Zmien adres"};
    private String[] colInvoice = {"Id", "Klient", "Produkty"};
    private String[] opInvoice = {"Dodaj", "Usun"};
    private String[] colProduct = {"Id","Nazwa", "Cena", "Dostepnosc"};
    private String[] opProduct = { "Dodaj", "Zmien cene", "Zmien dostepnosc"};

    public EditFrameManager(EditFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.doButton){
            if (current.equals("")) {
                frame.columns.setText("Wybierz kolumne");
                return;
            }
            int op = frame.options.getSelectedIndex();
        }
        else {
            display(e);
        }
    }

    private void editClient(int op, int par){

    }

    private void editInvoice(int op, int par){

    }

    private void editProduct(int op, int par){

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

