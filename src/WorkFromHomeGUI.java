import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class WorkFromHomeGUI extends JFrame {

    private final JDateChooser startDateField;
    private final JDateChooser endDateField;
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
        startDateField = new JDateChooser();
        endDateField = new JDateChooser();
        comboBox1 = new JComboBox<>(comboBoxValues);
        comboBox2 = new JComboBox<>(comboBoxValues);
        calculateButton = new JButton("Calculate");

        // Add labels and fields to the frame
        startDateField.setDateFormatString("MM/dd/yyyy");
        endDateField.setDateFormatString("MM/dd/yyyy");
        this.add(new JLabel("Start Date (MM/DD/YYYY):"));
        this.add(startDateField);
        this.add(new JLabel("End Date (MM/DD/YYYY):"));
        this.add(endDateField);
        this.add(new JLabel("Week 1 Work from Home Date:"));
        this.add(comboBox1);
        this.add(new JLabel("Week 2 Work from Home Date:"));
        this.add(comboBox2);
        this.add(calculateButton);

        //Actions
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    LocalDate startDate = startDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = endDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    DayOfWeekUS weekOneDay = (DayOfWeekUS) comboBox1.getSelectedItem();
                    DayOfWeekUS weekTwoDay = (DayOfWeekUS) comboBox2.getSelectedItem();
                    Map<LocalDate, HolidayEnum> conflictMap = dateCalculatorController.doCalculation(startDate, endDate, weekOneDay, weekTwoDay);
                    showResultsMessage(conflictMap);
                }
            }
        });
    }

    public boolean validateInput(){
        boolean errorFlag = false;
        StringJoiner errorMessage = new StringJoiner("\n");
        if (startDateField.getDate() == null){
            errorMessage.add("Please input a valid start date.");
            errorFlag = true;
        }
        if (endDateField.getDate() == null){
            errorMessage.add("Please input a valid end date.");
            errorFlag = true;
        }
        if (startDateField.getDate() != null && endDateField.getDate() != null &&
                endDateField.getDate().before(startDateField.getDate())){
            errorMessage.add("End date must be after the start date.");
            errorFlag = true;
        }
        if (errorFlag){
            JOptionPane.showMessageDialog(this, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void showResultsMessage(Map<LocalDate, HolidayEnum> p_conflictMap){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final StringBuilder sb = new StringBuilder();
        final long conflicts = p_conflictMap.values().stream().filter(Objects::nonNull).count();
        if (conflicts > 0){
            sb.append(conflicts);
            sb.append(" conflicts found with this work from home schedule\n");
        }
        for (LocalDate date : p_conflictMap.keySet()) {
            sb.append(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
            sb.append(" ");
            sb.append(date.format(formatter));
            if (p_conflictMap.get(date) != null){
                sb.append("\n\tConflict with this date: ");
                sb.append(p_conflictMap.get(date).getDescription());
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(this, sb, "Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
