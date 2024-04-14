import javax.swing.*;

public class Register extends JDialog {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public Register(JFrame parent) {
        super(parent);
        setTitle("Register");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        registerButton.addActionListener(e -> {
            if (usernameField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password first!");
                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
                return;
            }
            var db = DataBaseConnection.getInstance();
            var res = db.registerUser(usernameField.getText(), new String(passwordField.getPassword()));
            if (res) {
                JOptionPane.showMessageDialog(null, "Registered!!!");
                dispose();
                Login login = new Login(parent);
                login.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error Occurred!!!");
            }
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        });
    }
}
