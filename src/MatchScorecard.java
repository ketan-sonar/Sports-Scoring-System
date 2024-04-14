import javax.swing.*;

public class MatchScorecard extends JDialog {
    private JPanel contentPane;
    private JLabel title;
    private JPanel teamAPanel;
    private JPanel teamBPanel;
    private JTextField teamAScore;
    private JTextField teamBScore;
    private JButton teamAIncScore;
    private JButton teamBIncScore;
    private JButton doneButton;

    public MatchScorecard(JFrame parent, Match match) {
        super(parent);
        setTitle("Match");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        title.setText(String.format("%s vs %s", match.getTeamAName(), match.getTeamBName()));
        teamAPanel.setName(match.getTeamAName());
        teamBPanel.setName(match.getTeamBName());
        teamAScore.setText(String.format("%d", match.getTeamAScore()));
        teamBScore.setText(String.format("%d", match.getTeamBScore()));
        teamAScore.setEnabled(false);
        teamBScore.setEnabled(false);

        var db = DataBaseConnection.getInstance();

        teamAIncScore.addActionListener(e -> {
            if (db.incrementScore(match, 1)) {
                match.setTeamAScore(match.getTeamAScore() + 1);
                teamAScore.setText(String.format("%d", match.getTeamAScore()));
            } else {
                JOptionPane.showMessageDialog(this, "Error Occurred!!!");
            }
        });

        teamBIncScore.addActionListener(e -> {
            if (db.incrementScore(match, 2)) {
                match.setTeamBScore(match.getTeamBScore() + 1);
                teamBScore.setText(String.format("%d", match.getTeamBScore()));
            } else {
                JOptionPane.showMessageDialog(this, "Error Occurred!!!");
            }
        });

        doneButton.addActionListener(e -> {

        });

        setVisible(true);
    }
}
