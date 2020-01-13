package com.fakturowanko;

import com.fakturowanko.db.FakturyEntity;
import com.fakturowanko.db.KlientEntity;
import com.fakturowanko.db.ProduktEntity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OverviewFrameManager implements ActionListener {

    private OverviewFrame frame;

    public OverviewFrameManager(OverviewFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.clientButton){
            displayClient();
        } else if (e.getSource() == frame.invoiceButton){
            displayInvoice();
        } else if(e.getSource() == frame.productButton){
            displayProduct();
        }
    }

    private void displayClient(){
        List<KlientEntity> table = DataExpert.getWholeTable("klient");
        String text = "Lp.\t|Nazwa\t\t|NIP\t|Adres\t\t|Miasto\t|Kod pocztowy\n";
        StringBuilder builder = new StringBuilder(text);
        for (int i=0;i<table.size();i++){
            builder.append(table.get(i).getIdKlienta());
            builder.append("\t|");
            builder.append(table.get(i).getNazwa());
            builder.append("\t|");
            if (table.get(i).getNip() != null){
                builder.append(table.get(i).getNip());
            } else {
                builder.append("-\t\t|");
            }
            builder.append("\t|");
            builder.append(table.get(i).getAdres());
            builder.append("\t|");
            builder.append(table.get(i).getMiasto());
            builder.append("\t|");
            builder.append(table.get(i).getKodPocztowy());
            builder.append("\n");
        }
        text = builder.toString();
        frame.table.setText(text);
    }

    private void displayInvoice(){
        List<FakturyEntity> table = DataExpert.getWholeTable("faktura");
        String text = "Id faktury\t|Id klienta\t|Data\n";
        StringBuilder builder = new StringBuilder(text);
        for (int i=0;i<table.size();i++){
            builder.append(table.get(i).getIdFaktury());
            builder.append("\t|");
            builder.append(table.get(i).getIdKlienta());
            builder.append("\t|");
            builder.append(table.get(i).getData());
            builder.append("\n");
        }
        text = builder.toString();
        frame.table.setText(text);
    }

    private void displayProduct(){
        List<ProduktEntity> table = DataExpert.getWholeTable("produkt");
        String text = "Lp.\t|Nazwa\t|Cena\t|Czy w sprzedazy\n";
        StringBuilder builder = new StringBuilder(text);
        for (int i=0;i<table.size();i++){
            builder.append(table.get(i).getIdProduktu());
            builder.append("\t|");
            builder.append(table.get(i).getNazwa());
            builder.append("\t|");
            builder.append(table.get(i).getCena());
            builder.append("\t|");
            if (table.get(i).getSprzedawany()){
                builder.append("tak");
            } else {
                builder.append("nie");
            }
            builder.append("\n");
        }
        text = builder.toString();
        frame.table.setText(text);
    }
}
