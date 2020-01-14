package com.fakturowanko;

import javax.swing.*;
import java.awt.*;

public class ProductDataInputPanel extends JPanel {
    private JTextField name;
    private JTextField price;

    /**
     * defaultowy konstruktor
     */
    ProductDataInputPanel(){
        setLayout(new GridLayout(2,2));

        add(new JLabel("Nazwa: "));
        name = new JTextField();
        add(name);

        add(new JLabel("Cena: "));
        price = new JTextField();
        add(price);
    }

    public String getPName(){
        return name.getText();
    }

    public String getPrice(){
        return price.getText();
    }
}
