package com.fakturowanko;

import javax.swing.*;
import java.awt.*;

public class EditFrame extends JFrame {
    protected JButton clientButton;
    protected JButton invoiceButton;
    protected JButton productButton;
    protected JButton doButton;
    protected JTextArea columns;
    protected JLabel operations;
    protected JComboBox options;
    protected String[] colClient = {"Nazwa", "NIP", "Adres", "Miasto", "Kod pocztowy"};
    protected String[] opClient = { "Dodaj", "Usun", "Zmien adres"};
    protected String[] colInvoice = {"Id", "Klient", "Produkty"};
    protected String[] opInvoice = {"Dodaj", "Usun"};
    protected String[] colProduct = {"Id","Nazwa", "Cena", "Dostepnosc"};
    protected String[] opProduct = { "Dodaj", "Usun", "Zmien cene", "Zmien dostepnosc"};

    public EditFrame(){
        super("Edycja");
        setSize(350,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
        setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

        EditFrameManager manager = new EditFrameManager(this);

        clientButton = new JButton("Klienci");
        add(clientButton);
        clientButton.addActionListener(manager);

        invoiceButton = new JButton("Faktury");
        add(invoiceButton);
        invoiceButton.addActionListener(manager);

        productButton = new JButton("Produkty");
        add(productButton);
        productButton.addActionListener(manager);

        columns = new JTextArea("Kolumny: ");
        add(columns);
        columns.setEditable(false);

        operations = new JLabel("Operacje:");
        add(operations);

        doButton = new JButton("Wykonaj");
        add(doButton);
        doButton.addActionListener(manager);

        setVisible(true);
    }
}

