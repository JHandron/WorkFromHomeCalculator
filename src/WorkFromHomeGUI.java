import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WorkFromHomeGUI extends JFrame {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private final JFormattedTextField dateField1;
    private final JFormattedTextField dateField2;
    private final JComboBox<DayOfWeekUS> comboBox1;
    private final JComboBox<DayOfWeekUS> comboBox2;
    private final JButton calculateButton;

    private final DayOfWeekUS[] comboBoxValues = {DayOfWeekUS.MONDAY, DayOfWeekUS.TUESDAY, DayOfWeekUS.WEDNESDAY, DayOfWeekUS.THURSDAY, DayOfWeekUS.FRIDAY};
    private final WorkFromHomeCalculatorController dateCalculatorController = new WorkFromHomeCalculatorController();

    public WorkFromHomeGUI() {
        this.setTitle("DoT Work From Home Calculator");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(5, 2));

        // Initialize the fields
        dateField1 = new JFormattedTextField(DATE_FORMAT);
        dateField2 = new JFormattedTextField(DATE_FORMAT);
        comboBox1 = new JComboBox<>(comboBoxValues);
        comboBox2 = new JComboBox<>(comboBoxValues);
        calculateButton = new JButton("Calculate");

        // Add labels and fields to the frame
        this.add(new JLabel("Start Date (MM/DD/YYYY):"));
        this.add(dateField1);
        this.add(new JLabel("End Date (MM/DD/YYYY):"));
        this.add(dateField2);
        this.add(new JLabel("Week 1 Work From Home Date:"));
        this.add(comboBox1);
        this.add(new JLabel("Week 2 Work From Home Date:"));
        this.add(comboBox2);
        this.add(calculateButton);

        //Actions
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate startDate = LocalDate.parse(dateField1.getText(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate endDate = LocalDate.parse(dateField2.getText(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                DayOfWeekUS weekOneDay = (DayOfWeekUS) comboBox1.getSelectedItem();
                DayOfWeekUS weekTwoDay = (DayOfWeekUS) comboBox2.getSelectedItem();
                dateCalculatorController.doCalculation(startDate, endDate, weekOneDay, weekTwoDay);
            }
        });
    }
}
