import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel contentPane;
    private JButton scheduleAMatchButton;
    private JButton scoreAMatchButton;

    public Dashboard() {
        setTitle("Dashboard");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        scheduleAMatchButton.addActionListener(e -> {
            new ScheduleMatch(this);
        });

        scoreAMatchButton.addActionListener(e -> {
            new ScoreMatch(this);
        });

        setVisible(true);
    }
}
