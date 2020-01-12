package com.fakturowanko;

/**
 * main
 * @author dszyd
 *
 */
public class Main {

    private Main() {
        new LoginFrame(this);
    }

    public void programStarter() {
        new MainFrame();
    }

    public static void main(String[] args) {
        new Main();
    }

}
