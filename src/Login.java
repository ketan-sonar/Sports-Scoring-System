import javax.swing.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        loginButton.addActionListener(e -> {
            if (usernameField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password first!");
                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
                return;
            }
            var db = DataBaseConnection.getInstance();
            var res = db.loginUser(usernameField.getText(), new String(passwordField.getPassword()));
            if (res) {
                JOptionPane.showMessageDialog(null, "Logged In!!!");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong username or password!");
            }
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        });
    }
}
