import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class HomePage extends JFrame {
    private List<User> users;
    private DefaultTableModel trainTableModel;
    private DefaultTableModel busTableModel;
    private JTable trainTable;
    private JTable busTable;
    private JTextField trainSearchField;
    private JTextField busSearchField;

    public HomePage() {
        // Initialize user list
        users = new ArrayList<>();
        users.add(new User("admin", "admin", true));
        users.add(new User("user1", "password1", false));
        users.add(new User("user2", "password2", false));

        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);

        // Panel 0: Header panel
        JPanel panel0 = new JPanel();
        panel0.setBackground(new Color(0x8F1402));
        panel0.setLayout(new BorderLayout());
        panel0.setPreferredSize(new Dimension(1000, 100));

        JLabel labelLogo1 = new JLabel("I-Go!", SwingConstants.CENTER);
        labelLogo1.setFont(new Font("Aharoni", Font.BOLD, 50));
        labelLogo1.setForeground(Color.WHITE);

        JLabel labelLogo2 = new JLabel("Public Scheduling Transportation System", SwingConstants.CENTER);
        labelLogo2.setFont(new Font("Aharoni", Font.BOLD, 15));
        labelLogo2.setForeground(Color.WHITE);

        panel0.add(labelLogo1, BorderLayout.NORTH);
        panel0.add(labelLogo2, BorderLayout.SOUTH);

        // Panel 2: Train table panel
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBorder(new EmptyBorder(0, 100, 0, 0));

        // Train search panel
        JPanel trainSearchPanel = new JPanel();
        JLabel trainLabelSearch = new JLabel("Enter Train No.:");
        trainLabelSearch.setFont(new Font("Aharoni", Font.BOLD, 15));
        trainSearchField = new JTextField(15);
        JButton trainSearchButton = new JButton("Search");
        JButton trainClearButton = new JButton("Clear");

        trainSearchPanel.add(trainLabelSearch);
        trainSearchPanel.add(trainSearchField);
        trainSearchPanel.add(trainSearchButton);
        trainSearchPanel.add(trainClearButton);

        String[] trainColumns = {"Train Number", "Line", "Departure Time"};
        Object[][] trainData = {{"123", "Red Line", "12:00 PM"}, {"456", "Blue Line", "1:00 PM"}};
        trainTableModel = new DefaultTableModel(trainData, trainColumns);
        trainTable = new JTable(trainTableModel);
        JScrollPane scrollPane1 = new JScrollPane(trainTable);

        panel2.add(trainSearchPanel, BorderLayout.NORTH);
        panel2.add(scrollPane1, BorderLayout.CENTER);

        // Panel 3: Bus table panel
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBorder(new EmptyBorder(0, 0, 0, 20));

        // Create a container with margin for the bus panel content
        JPanel busContentPanel = new JPanel(new BorderLayout());
        busContentPanel.setBorder(new EmptyBorder(0, 0, 0, 100));

        // Bus search panel
        JPanel busSearchPanel = new JPanel();
        JLabel busLabelSearch = new JLabel("Enter Bus No.:");
        busLabelSearch.setFont(new Font("Aharoni", Font.BOLD, 15));
        busSearchField = new JTextField(15);
        JButton busSearchButton = new JButton("Search");
        JButton busClearButton = new JButton("Clear");

        busSearchPanel.add(busLabelSearch);
        busSearchPanel.add(busSearchField);
        busSearchPanel.add(busSearchButton);
        busSearchPanel.add(busClearButton);

        String[] busColumns = {"Bus Number", "Line", "Departure Time"};
        Object[][] busData = {{"789", "Green Line", "2:00 PM"}, {"101", "Yellow Line", "3:00 PM"}};
        busTableModel = new DefaultTableModel(busData, busColumns);
        busTable = new JTable(busTableModel);
        JScrollPane scrollPane2 = new JScrollPane(busTable);

        busContentPanel.add(busSearchPanel, BorderLayout.NORTH);
        busContentPanel.add(scrollPane2, BorderLayout.CENTER);

        panel3.add(busContentPanel);

        // Panel 4: Login form panel
        JPanel panel4 = new JPanel();
        panel4.setOpaque(true);
        panel4.setLayout(new GridBagLayout());
        panel4.setBackground(new Color(0x8F1402));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setFont(new Font("Aharoni", Font.BOLD, 15));
        labelUsername.setForeground(Color.WHITE);

        JTextField usernameField = new JTextField(15);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Aharoni", Font.BOLD, 15));
        labelPassword.setForeground(Color.WHITE);

        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Aharoni", Font.BOLD, 15));

        JButton clearFormButton = new JButton("Admin?");
        clearFormButton.setFont(new Font("Aharoni", Font.BOLD, 15));

        JButton registerButton = new JButton("No account?");
        registerButton.setFont(new Font("Aharoni", Font.BOLD, 15));

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Aharoni", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel4.add(labelUsername, gbc);
        gbc.gridx = 1;
        panel4.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel4.add(labelPassword, gbc);
        gbc.gridx = 1;
        panel4.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel4.add(loginButton, gbc);
        gbc.gridx = 1;
        panel4.add(clearFormButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel4.add(registerButton, gbc);
        gbc.gridx = 1;
        panel4.add(quitButton, gbc);

        // Train search button action
        trainSearchButton.addActionListener(e -> {
            String searchText = trainSearchField.getText().trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Train number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                filterTrainTable(searchText);
            }
        });

        // Train clear button action
        trainClearButton.addActionListener(e -> {
            trainSearchField.setText("");
            reloadTrainTable();
        });

        // Bus search button action
        busSearchButton.addActionListener(e -> {
            String searchText = busSearchField.getText().trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Bus number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                filterBusTable(searchText);
            }
        });

        // Bus clear button action
        busClearButton.addActionListener(e -> {
            busSearchField.setText("");
            reloadBusTable();
        });

        quitButton.addActionListener(e -> System.exit(0));

        // Login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password)) {
                new booking().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // No account? button
        registerButton.addActionListener(e -> {
            JTextField regUsernameField = new JTextField(15);
            JPasswordField regPasswordField = new JPasswordField(15);

            JPanel registerPanel = new JPanel(new GridLayout(2, 2));
            registerPanel.add(new JLabel("Username:"));
            registerPanel.add(regUsernameField);
            registerPanel.add(new JLabel("Password:"));
            registerPanel.add(regPasswordField);

            int result = JOptionPane.showConfirmDialog(this, registerPanel, "Register", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String username = regUsernameField.getText();
                String password = new String(regPasswordField.getPassword());
                if (register(username, password)) {
                    JOptionPane.showMessageDialog(this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Admin? button
        clearFormButton.addActionListener(e -> {
            JTextField adminUsernameField = new JTextField(15);
            JPasswordField adminPasswordField = new JPasswordField(15);

            JPanel adminPanel = new JPanel(new GridLayout(2, 2));
            adminPanel.add(new JLabel("Username:"));
            adminPanel.add(adminUsernameField);
            adminPanel.add(new JLabel("Password:"));
            adminPanel.add(adminPasswordField);

            int result = JOptionPane.showConfirmDialog(this, adminPanel, "Admin Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String adminUsername = adminUsernameField.getText();
                String adminPassword = new String(adminPasswordField.getPassword());
                if (authenticate(adminUsername, adminPassword) && isAdmin(adminUsername)) {
                    JOptionPane.showMessageDialog(this, "Admin login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new AdminDashboard().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Main layout
        setLayout(new BorderLayout());
        add(panel0, BorderLayout.NORTH);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.EAST);
        add(panel4, BorderLayout.SOUTH);
    }

    private boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    private boolean register(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password, false));
        return true;
    }

    private void filterTrainTable(String trainNo) {
        for (int i = 0; i < trainTableModel.getRowCount(); i++) {
            String currentTrainNo = (String) trainTableModel.getValueAt(i, 0);
            if (currentTrainNo.equals(trainNo)) {
                trainTable.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Train number not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reloadTrainTable() {
        // Reload the train table with original data
        Object[][] trainData = {{"123", "Red Line", "12:00 PM"}, {"456", "Blue Line", "1:00 PM"}};
        trainTableModel.setRowCount(0); // Clear existing data
        for (Object[] row : trainData) {
            trainTableModel.addRow(row);
        }
    }

    private void filterBusTable(String busNo) {
        for (int i = 0; i < busTableModel.getRowCount(); i++) {
            String currentBusNo = (String) busTableModel.getValueAt(i, 0);
            if (currentBusNo.equals(busNo)) {
                busTable.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Bus number not found.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reloadBusTable() {
        // Reload the bus table with original data
        Object[][] busData = {{"789", "Green Line", "2:00 PM"}, {"101", "Yellow Line", "3:00 PM"}};
        busTableModel.setRowCount(0); // Clear existing data
        for (Object[] row : busData) {
            busTableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        new HomePage().setVisible(true);
    }
}

// User class to store user details
class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
