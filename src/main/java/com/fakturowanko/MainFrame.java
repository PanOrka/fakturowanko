package com.fakturowanko;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * glowna ramka programu
 * @author dszyd
 *
 */
public class MainFrame extends JFrame {

    protected JLabel frontLabel;
    protected JButton newButton;
    protected JButton viewButton;
    protected JButton editButton;
    protected JButton overviewButton;
    protected JButton backupButton;

    /**
     * defaultowy konstruktor
     */
    MainFrame(){
        super("Zadanie fakturowanie");
        setSize(350,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
        setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

        DataExpert dataExpert = new DataExpert();
        MainFrameManager manager = new MainFrameManager(this, dataExpert);

        frontLabel = new JLabel("FAKTUROWANIE", JLabel.CENTER);
        frontLabel.setFont(new Font("Serif", Font.BOLD, 34));
        frontLabel.setForeground(Color.MAGENTA);
        add(frontLabel);

        newButton = new JButton("Nowa");
        add(newButton);
        newButton.addActionListener(manager);

        viewButton = new JButton("Wyswietl");
        add(viewButton);
        viewButton.addActionListener(manager);

        editButton = new JButton("Edytuj baze");
        add(editButton);
        editButton.addActionListener(manager);

        overviewButton = new JButton("Przegladaj baze");
        add(overviewButton);
        overviewButton.addActionListener(manager);

        backupButton = new JButton("Back-up");
        add(backupButton);
        backupButton.addActionListener(manager);

        setVisible(true);

    }
}
