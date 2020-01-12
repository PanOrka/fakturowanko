package com.fakturowanko;

import com.fakturowanko.db.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private final JTextField Login;
    private final JTextField Password;
    private final JButton LoginButton;
    private final Main main;

    LoginFrame(Main main) {
        super("Sign up");
        this.main = main;
        this.Login = new JTextField();
        this.Password = new JTextField();
        this.LoginButton = new JButton("Login");
        this.setUI();
    }

    private void setUI() {
        this.setVisible(true);
        this.setSize(300, 100);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 2));
        this.LoginButton.addActionListener(this);
        this.add(new JLabel("Login", JLabel.CENTER));
        this.add(this.Login);
        this.add(new JLabel("Password", JLabel.CENTER));
        this.add(this.Password);
        this.add(new JLabel());
        this.add(this.LoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.LoginButton) {
            System.out.println("CLICK");
            HibernateUtil.setUser(this.Login.getText());
            HibernateUtil.setPassword(this.Password.getText());
            dbLoginTest();
        }
    }

    private void dbLoginTest() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            this.main.programStarter();
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot Log in", "Achtung!!!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
