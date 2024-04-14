import javax.swing.*;

public class Main extends JFrame {
    private JPanel contentPane;
    private JButton loginButton;
    private JButton registerButton;

    public Main() {
        setTitle("Sports Scoring System");
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 180);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(e -> {
            Login login = new Login(this);
            login.setVisible(true);
        });

        registerButton.addActionListener(e -> {
            Register register = new Register(this);
            register.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
