package com.fakturowanko;

/**
 * klasa obslugujaca glowna ramke
 */

import com.fakturowanko.db.BackupClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MainFrameManager implements ActionListener {

    private MainFrame frame;
    private DataExpert dataExpert;

    /**
     * defaultowy konstruktor
     * @param frame ramka do obslugi
     * @param dataExpert manager bazy dancyh
     */
    MainFrameManager(MainFrame frame, DataExpert dataExpert){
        this.frame = frame;
        this.dataExpert = dataExpert;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == frame.newButton) {
            newInvoice();
        }
        else if (source==frame.viewButton) {
            String invoiceId = JOptionPane.showInputDialog(frame, "Podaj ID faktury");
            if (invoiceId != null) {
                try {
                    int n = Integer.parseInt(invoiceId);
                }
                catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Podano nieprawidlowe ID", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int inId= Integer.parseInt(invoiceId);
                if (!dataExpert.invoiceChecker(inId)) {
                    JOptionPane.showMessageDialog(null, "Faktura o podanym ID nie istnieje", "Blad!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    new InvoiceViewFrame(dataExpert, inId);
                }
            }
        }
        else if (source == frame.editButton){
            EditFrame edit = new EditFrame();
        }
        else if (source == frame.overviewButton){
            new OverviewFrame();
        }
        else if (source == frame.backupButton){
            BackupClass.makeBackupGreatAgain();
        }
    }

    /**
     * funckja inicjujaca wprowadzenie nowej faktury, odpowiada za pozyskanie id klienta
     */
    private void newInvoice() {
        int clientId=0;
        int answer = JOptionPane.showConfirmDialog(frame, "Czy klient juz widnieje w bazie?", "Wazne pytanie", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.NO_OPTION) {
            clientId = newClient();
        }
        else if (answer == JOptionPane.YES_OPTION) {
            clientId = existingClient();
        }
        if (clientId == 0) return;
        new NewInvoiceFrame(clientId, dataExpert);
    }

    /**
     * pobiera od uzytkownika dane nowego klienta i sprawdza ich poprawnosc
     * @return id nowego klienta lub 0, gdy dane sa niepoprawne
     */
    private int newClient() {
        ClientDataInputPanel data = new ClientDataInputPanel();
        int dataInput = JOptionPane.showConfirmDialog(frame, data, "Podaj dane klienta", JOptionPane.OK_CANCEL_OPTION);
        if (dataInput == JOptionPane.OK_OPTION) {
            try {
                if (!data.getNip().equals("")) {
                    Integer.parseInt(data.getNip());
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(data, "Podano nieprawidlowy NIP", "Blad!", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        } else if (dataInput == JOptionPane.CANCEL_OPTION) {
            return 0;
        } else if (dataInput == JOptionPane.CLOSED_OPTION) {
            return 0;
        }
        return dataExpert.addClient(frame, data.getCName(), data.getAdress(), data.getCity(), data.getPostalCode(), data.getNip());
    }

    /**
     * pobiera od uzytkownika id klienta widniejacego w bazie i sprawdza jego poprawnosc
     * @return poprawne id klienta lub 0, gdy dane sa nieprawidlowe
     */
    private int existingClient() {
        String stringId = JOptionPane.showInputDialog(frame, "Podaj ID klienta");
        int clientId = 0;
        if (stringId != null) {
            try {
                clientId = Integer.parseInt(stringId);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidlowe ID", "Blad!", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            clientId = Integer.parseInt(stringId);

            if (!dataExpert.clientChecker(clientId)) {
                JOptionPane.showMessageDialog(null, "Klient o podanym ID nie istnieje", "Blad!", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        return clientId;
    }

}