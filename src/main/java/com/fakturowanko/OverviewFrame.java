package com.fakturowanko;

import javax.swing.*;
import java.awt.*;

public class OverviewFrame extends JFrame {
    protected JButton clientButton;
    protected JButton invoiceButton;
    protected JButton productButton;
    protected JTextArea table;

    public OverviewFrame(){
        super("Przegladanie");
        setSize(700,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
        setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

        OverviewFrameManager manager = new OverviewFrameManager(this);

        clientButton = new JButton("Klienci");
        add(clientButton);
        clientButton.addActionListener(manager);

        invoiceButton = new JButton("Faktury");
        add(invoiceButton);
        invoiceButton.addActionListener(manager);

        productButton = new JButton("Produkty");
        add(productButton);
        productButton.addActionListener(manager);

        table = new JTextArea("tu pojawi sie tabela");
        table.setEditable(false);
        add(table);

        setVisible(true);
    }
}
