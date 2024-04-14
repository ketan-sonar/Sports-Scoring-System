import javax.swing.*;

public class ScoreMatch extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> matchComboBox;
    private JButton startScoringButton;

    public ScoreMatch(JFrame parent) {
        super(parent);
        setTitle("Score a Match");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        var db = DataBaseConnection.getInstance();
        var matches = db.getMatches();
        for (var match : matches) {
            matchComboBox.addItem(String.format("%s vs %s | %s", match.getTeamAName(), match.getTeamBName(), match.getDate().toString()));
        }

        startScoringButton.addActionListener(e -> {
            new MatchScorecard(parent, matches.get(matchComboBox.getSelectedIndex()));
        });

        setVisible(true);
    }
}
