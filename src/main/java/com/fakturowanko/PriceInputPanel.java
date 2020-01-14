package com.fakturowanko;

import javax.swing.*;
import java.awt.*;

public class PriceInputPanel extends JPanel {
    private JTextField price;

    public PriceInputPanel() {
        setLayout(new GridLayout(1,1));
        add(new JLabel("Cena: "));
        price = new JTextField();
        add(price);
    }

    public String getPrice(){
        return price.getText();
    }
}
