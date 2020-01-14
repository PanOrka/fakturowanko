package com.fakturowanko;

import javax.swing.*;
import java.awt.*;

public class AdressDataInputPanel extends JPanel {
    private JTextField adress;
    private JTextField city;
    private JTextField postal_code;

    /**
     * defaultowy konstruktor
     */
    AdressDataInputPanel(){
        setLayout(new GridLayout(3,2));

        add(new JLabel("Adres (ulica): "));
        adress = new JTextField();
        add(adress);

        add(new JLabel("Miasto: "));
        city = new JTextField();
        add(city);

        add(new JLabel("Kod pocztowy: "));
        postal_code = new JTextField();
        add(postal_code);
    }

    public String getAdress(){
        return adress.getText();
    }
    public String getCity() {
        return city.getText();
    }
    public String getPostalCode() {
        return postal_code.getText();
    }
}
