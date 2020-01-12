package com.fakturowanko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFrameManager implements ActionListener {

    private EditFrame frame;

    public EditFrameManager(EditFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.productButton){
            String build = "Kolumny:";
            for (int i=0;i<frame.colProduct.length;i++){
                build+= "\n"+frame.colProduct[i];
            }
            frame.columns.setText(build);
        }
    }
}

