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

    /**
     * defaultowy konstruktor
     */
    MainFrame(){
        super("Zadanie fakturowanie");
        setSize(350,200);
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
        setVisible(true);

    }
}
