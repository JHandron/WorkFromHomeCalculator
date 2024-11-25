import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

public class WorkFromHomeGUI extends JFrame {

    private final JDateChooser dateField1;
    private final JDateChooser dateField2;
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
        dateField1 = new JDateChooser();
        dateField2 = new JDateChooser();
        comboBox1 = new JComboBox<>(comboBoxValues);
        comboBox2 = new JComboBox<>(comboBoxValues);
        calculateButton = new JButton("Calculate");

        // Add labels and fields to the frame
        dateField1.setDateFormatString("MM/dd/yyyy");
        dateField2.setDateFormatString("MM/dd/yyyy");
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
                LocalDate startDate = dateField1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = dateField2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DayOfWeekUS weekOneDay = (DayOfWeekUS) comboBox1.getSelectedItem();
                DayOfWeekUS weekTwoDay = (DayOfWeekUS) comboBox2.getSelectedItem();
                dateCalculatorController.doCalculation(startDate, endDate, weekOneDay, weekTwoDay);
            }
        });
    }
}
