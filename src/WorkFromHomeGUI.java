import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

//TODO: Look and feel
//TODO: File writer
//TODO: Menu items
//TODO: Layout
//TODO: Calendar generator

public class WorkFromHomeGUI extends JFrame {

    private final JPanel inputPanel;
    private final JDateChooser startDateField;
    private final JDateChooser endDateField;
    private final JComboBox<String> comboBox1;
    private final JComboBox<String> comboBox2;
    private final JButton calculateButton;

    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JScrollPane tableScrollPane;
    private final JSplitPane splitPane;

    private final JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem menuItemSave;
    private final JMenuItem menuItemOpen;
    private final JMenuItem menuItemExit;

    private final WorkFromHomeCalculatorController calculatorController = new WorkFromHomeCalculatorController();
    private final String[] columnNames = {"Day", "Date", "Conflict"};
    private final String[] comboBoxValues = {DayOfWeekUS.MONDAY.getDescription(),
                                             DayOfWeekUS.TUESDAY.getDescription(),
                                             DayOfWeekUS.WEDNESDAY.getDescription(),
                                             DayOfWeekUS.THURSDAY.getDescription(),
                                             DayOfWeekUS.FRIDAY.getDescription()};

    public WorkFromHomeGUI() {
        this.setTitle("DoT Work From Home Calculator");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the fields
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        startDateField = new JDateChooser();
        endDateField = new JDateChooser();
        comboBox1 = new JComboBox<>(comboBoxValues);
        comboBox2 = new JComboBox<>(comboBoxValues);
        calculateButton = new JButton("Calculate");
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItemSave = new JMenuItem("Save");
        menuItemSave.setEnabled(false);
        menuItemExit = new JMenuItem("Exit");
        menuItemOpen = new JMenuItem("Open");

        // Add labels and fields to the frame
        startDateField.setDateFormatString("MM/dd/yyyy");
        endDateField.setDateFormatString("MM/dd/yyyy");
        inputPanel.add(new JLabel("Start Date (MM/DD/YYYY):"));
        inputPanel.add(startDateField);
        inputPanel.add(new JLabel("End Date (MM/DD/YYYY):"));
        inputPanel.add(endDateField);
        inputPanel.add(new JLabel("Week 1 Work from Home Date:"));
        inputPanel.add(comboBox1);
        inputPanel.add(new JLabel("Week 2 Work from Home Date:"));
        inputPanel.add(comboBox2);
        inputPanel.add(calculateButton);
        menu.add(menuItemOpen);
        menu.add(menuItemSave);
        menu.addSeparator();
        menu.add(menuItemExit);
        menuBar.add(menu);

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, tableScrollPane);
        splitPane.setDividerLocation(150);
        splitPane.setResizeWeight(0.5);
        this.add(menuBar);
        this.setJMenuBar(menuBar);
        this.add(splitPane);

        //Actions
        calculateButton.addActionListener(this::calculateButtonClicked);
        menuItemExit.addActionListener(this::menuItemExitClicked);
    }

    public void calculateButtonClicked(ActionEvent e){
        if (validateInput()) {
            final LocalDate startDate = startDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            final LocalDate endDate = endDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            final DayOfWeekUS weekOneDay = DayOfWeekUS.getEnumByDescription((String) comboBox1.getSelectedItem());
            final DayOfWeekUS weekTwoDay = DayOfWeekUS.getEnumByDescription((String) comboBox2.getSelectedItem());
            final Map<LocalDate, HolidayEnum> conflictMap = calculatorController.doCalculation(startDate, endDate, weekOneDay, weekTwoDay);
            updateTable(conflictMap);
        }
    }

    public void updateTable(Map<LocalDate, HolidayEnum> p_conflictMap){
        tableModel.setRowCount(0);
        for (Map.Entry<LocalDate, HolidayEnum> entry : p_conflictMap.entrySet()) {
            tableModel.addRow(new Object[]{entry.getKey().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US),
                                           entry.getKey(),
                                           entry.getValue() != null ? entry.getValue().getDescription(): null});
        }
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(p_conflictMap.values().stream().filter(Objects::nonNull).count() + " conflict(s) found with this schedule."));
        tableModel.fireTableDataChanged();
        menuItemSave.setEnabled(true);
    }

    public void menuItemExitClicked(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit Application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
            System.exit(0);
        }
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
}