import javax.swing.*;

public class WorkFromHomeCalculatorApp {

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WorkFromHomeGUI().setVisible(true);
            }
        });
    }
}

