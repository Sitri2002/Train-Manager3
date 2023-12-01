package Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RGBImageFilter;
import java.net.URL;
import java.awt.image.FilteredImageSource;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("WILDCAT RAILWAY");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("WILDCAT RAILWAY");
        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 50));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        loginPanel.setPreferredSize(new Dimension(700, 350));

        // Adding train image to the left
        ImageIcon bottomLeftGraphic = createImageIcon("trainImage1.png");
        ImageIcon modifiedIcon = makeWhitePixelsTransparent(bottomLeftGraphic);
        JLabel bottomLeftLabel = new JLabel(modifiedIcon);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(173, 216, 230));

        imagePanel.add(bottomLeftLabel, BorderLayout.WEST);

        // Adding wildcat Logo to the right
        // fix image link
        ImageIcon bottomRightGraphic = createImageIcon("wildcatLogo.png");
        ImageIcon modifiedIcon2 = makeWhitePixelsTransparent(bottomRightGraphic);
        JLabel bottomRightLabel = new JLabel(modifiedIcon2);

        imagePanel.add(bottomRightLabel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(loginPanel);
        
        add(centerPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
    }

    private ImageIcon createImageIcon(String filename) {
        URL url = getClass().getClassLoader().getResource(filename);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Resource not found: " + filename);
            return null; 
        }
    }
    
    private ImageIcon makeWhitePixelsTransparent(ImageIcon icon) {
        Image image = icon.getImage();

        RGBImageFilter filter = new RGBImageFilter() {
            @Override
            public int filterRGB(int x, int y, int rgb) {
                if ((rgb & 0xFFFFFF) == 0xFFFFFF) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        Image modifiedImage = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(), filter));

        return new ImageIcon(modifiedImage);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            }
        });
    }
}

class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel loginFieldsPanel = new JPanel();
        loginFieldsPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginFieldsPanel.add(usernameLabel);
        loginFieldsPanel.add(usernameField);
        loginFieldsPanel.add(passwordLabel);
        loginFieldsPanel.add(passwordField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.lightGray);
                    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.darkGray);
                g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
            }
        };

        JButton createAccountButton = new JButton("Create Account");

        buttonsPanel.add(loginButton);
        buttonsPanel.add(createAccountButton);

        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));
        generalPanel.setOpaque(false);
        
        generalPanel.add(loginFieldsPanel);
        generalPanel.add(buttonsPanel);

        add(generalPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
    }


    private void login() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        if (isValidLogin(username, new String(password))) {
            JOptionPane.showMessageDialog(this, "Login Successful");
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed");
        }
    }

    private void createAccount() {
    	// replace me with actual logic
        JOptionPane.showMessageDialog(this, "Create Account functionality not implemented yet.");
    }

    private boolean isValidLogin(String username, String password) {
        // replace me with actual logic
        // stupid hardcoded example
        return username.equals("user") && password.equals("123");
    }
}
