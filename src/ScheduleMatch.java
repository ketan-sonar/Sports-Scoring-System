import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.Date;

public class ScheduleMatch extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> sportsComboBox;
    private JTextField teamAName;
    private JTextField teamBName;
    private JDateChooser dateChooser;
    private JButton scheduleButton;

    public ScheduleMatch(JFrame parent) {
        super(parent);
        setTitle("Schedule a Match");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        String[] sportsOptions = { "Volleyball", "Table Tennis", "Badminton" };
        for (String option : sportsOptions) {
            sportsComboBox.addItem(option);
        }

        scheduleButton.addActionListener(e -> {
            var db = DataBaseConnection.getInstance();
            var res = db.scheduleMatch(teamAName.getText(), teamBName.getText(), new Date(dateChooser.getDate().getTime()));
            if (res) {
                JOptionPane.showMessageDialog(null, "Match Scheduled!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Could not schedule match!");
            }
        });

        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dateChooser = new JDateChooser();
    }
}
