package com.fakturowanko;

/**
 * klasa obslugujaca glowna ramke
 */
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
            try {
                int n = Integer.parseInt(invoiceId);
            }
            catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Podano nieprawidlowe ID", "Blad!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int inId= Integer.parseInt(invoiceId);
            if (inId<1 || inId>dataExpert.invoiceList.size()) {
                JOptionPane.showMessageDialog(null, "Faktura o podanym ID nie istnieje", "Blad!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                InvoiceViewFrame invoiceFrame = new InvoiceViewFrame(dataExpert, inId);
            }
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
        NewInvoiceFrame newInvoiceFrame = new NewInvoiceFrame(clientId, dataExpert);
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
                int nip = Integer.parseInt(data.getNip());
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(data, "Podano nieprawidlowy NIP", "Blad!", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        int newClientId = dataExpert.getNewClientId();
        dataExpert.addClient(newClientId, data.getCName(), data.getSurname(), data.getNip(), data.getAdress(), data.getCity());
        return newClientId;
    }

    /**
     * pobiera od uzytkownika id klienta widniejacego w bazie i sprawdza jego poprawnosc
     * @return poprawne id klienta lub 0, gdy dane s� nieprawidlowe
     */
    private int existingClient() {
        String stringId = JOptionPane.showInputDialog(frame, "Podaj ID klienta");
        try {
            int clientId = Integer.parseInt(stringId);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Podano nieprawidlowe ID", "Blad!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        int clientId = Integer.parseInt(stringId);
        if (clientId < 1 || clientId > dataExpert.clientList.size()) {
            JOptionPane.showMessageDialog(null, "Klient o podanym ID nie istnieje", "Blad!", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return clientId;
    }

}