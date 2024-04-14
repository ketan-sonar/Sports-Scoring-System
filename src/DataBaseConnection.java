import java.sql.*;
import java.util.ArrayList;

class Match {
    private int id;
    private String teamAName;
    private String teamBName;
    private Date date;
    private int teamAScore;
    private int teamBScore;

    public Match(int id, String teamAName, String teamBName, Date date, int teamAScore, int teamBScore) {
        this.id = id;
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.date = date;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

public class DataBaseConnection {
    private static final DataBaseConnection instance = new DataBaseConnection();

    Connection conn;

    private DataBaseConnection() {
        try {
            String url = "jdbc:mysql://avnadmin:AVNS_8vlIeeTvxtiRM-MLD-b@mysql-360c6e60-nimadir-2a58.a.aivencloud.com:24136/sports-scoring-system?ssl-mode=REQUIRED";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static DataBaseConnection getInstance() {
        return instance;
    }

    public boolean loginUser(String username, String password) {
        try {
            String query = "SELECT `password` FROM `users` WHERE `username` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var actualPassword = resultSet.getString("password");
                if (actualPassword.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean registerUser(String username, String password) {
        try {
            String query = "INSERT INTO `users` (`username`, `password`) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean scheduleMatch(String teamAName, String teamBName, Date date) {
        try {
            String query = "INSERT INTO `matches` (`teamAName`, `teamBName`, `date`) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, teamAName);
            preparedStatement.setString(2, teamBName);
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public ArrayList<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<>();

        try {
            String query = "SELECT * FROM `matches`";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String teamAName = resultSet.getString("teamAName");
                String teamBName = resultSet.getString("teamBName");
                Date date = resultSet.getDate("date");
                int teamAScore = resultSet.getInt("teamAScore");
                int teamBScore = resultSet.getInt("teamBScore");
                matches.add(new Match(id, teamAName, teamBName, date, teamAScore, teamBScore));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return matches;
    }

    public boolean incrementScore(Match match, int teamNo) {
        try {
            String query = String.format("UPDATE `matches` SET `%s` = ? WHERE `id` = ?", teamNo == 1 ? "teamAScore" : "teamBScore");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, match.getTeamAScore() + 1);
            preparedStatement.setInt(2, match.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
