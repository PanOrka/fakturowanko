package com.fakturowanko;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * klasa obslugujaca panel do wprowadzania danych nowego klienta
 * @author dszyd
 *
 */
public class ClientDataInputPanel extends JPanel {

    private JTextField name;
    private JTextField surname;
    private JTextField adress;
    private JTextField city;
    private JTextField nip;

    /**
     * defaultowy konstruktor
     */
    ClientDataInputPanel(){
        setLayout(new GridLayout(5,2));

        add(new JLabel("Imie: "));
        name = new JTextField();
        add(name);

        add(new JLabel("Nazwisko: "));
        surname = new JTextField();
        add(surname);

        add(new JLabel("NIP: "));
        nip = new JTextField();
        add(nip);

        add(new JLabel("Adres (ulica): "));
        adress = new JTextField();
        add(adress);

        add(new JLabel("Adres (kod pocztowy i miasto): "));
        city = new JTextField();
        add(city);

    }

    public String getCName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public String getAdress() {
        return adress.getText();
    }

    public String getNip() {
        return nip.getText();
    }
    public String getCity() {
        return city.getText();
    }
}
