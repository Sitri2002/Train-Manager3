package Software;

import javax.swing.*;

import People.Manager;
import People.Passenger;
import People.Person;
import Hardware.Train;
import Software.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RGBImageFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
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
	private Data data1 = new Data();
	private ArrayList<Person> people_list = new ArrayList<Person>();
	private Person loggedin;
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
            if(loggedin instanceof Passenger) {
            	PassengerMainMenu();
            } 
            else if (loggedin instanceof Manager) {
            	ManagerMainMenu();
            }
            SwingUtilities.getWindowAncestor(this).dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed");
        }
    }

    private void createAccount() {
    	// replace me with actual logic
        // JOptionPane.showMessageDialog(this, "Create Account functionality not implemented yet.");
    	String[] items = {"Passenger", "Manager"};

        // Create a JComboBox with the array of items
        JComboBox<String> comboBox = new JComboBox<>(items);

        // Create a custom JPanel to hold the JComboBox
        JPanel create_account_panel = new JPanel(new GridLayout(5,1));
        create_account_panel.add(new JLabel("Select account type:"));
        create_account_panel.add(comboBox);

        JTextField username = new JTextField(); JPasswordField password = new JPasswordField(); JTextField name = new JTextField(); JTextField email = new JTextField(); ;
        
        JLabel namelabel = new JLabel("Name:");
        create_account_panel.add(namelabel);
        create_account_panel.add(name);
        
        JLabel userlabel = new JLabel("Username:");
        create_account_panel.add(userlabel);
        create_account_panel.add(username);
        
        JLabel passwordlabel = new JLabel("Password:");
        create_account_panel.add(passwordlabel);
        create_account_panel.add(password);
        
        JLabel emaillabel = new JLabel("Email:");
        create_account_panel.add(emaillabel);
        create_account_panel.add(email);
        
        // Show the JOptionPane with the custom panel
        int result = JOptionPane.showOptionDialog(null, create_account_panel, "Create New Account",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);

        String username_str = username.getText(); char[] password_char = password.getPassword(); String name_str = name.getText(); String email_str = email.getText();
        String password_str = String.valueOf(password_char);
        
        // Check the result when OK is clicked
        if (result == JOptionPane.OK_OPTION) {
            String selectedValue = (String) comboBox.getSelectedItem();
            if(selectedValue.equals("Passenger")) {
            	Passenger p = new Passenger();
            	p.setName(name_str); p.setUsername(username_str); p.setPassword(password_str); p.setEmail(email_str);
            	people_list.add(p);
            	
            	Passenger.saveData(people_list);
            	
            	JOptionPane.showMessageDialog(null, "Created a " + selectedValue + " account for " + name_str);
            }
            else if(selectedValue.equals("Manager")) {
            	Manager m = new Manager();
            	m.setName(name_str); m.setUsername(username_str); m.setPassword(password_str); m.setEmail(email_str);
            	people_list.add(m);
            	
            	Manager.saveData(people_list);
            	
            	JOptionPane.showMessageDialog(null, "Created a " + selectedValue + " account for " + name_str);
            }
        }
    }
   
    private boolean isValidLogin(String username, String password) {
        // replace me with actual logic
        // stupid hardcoded example
    	people_list = Person.loadData();
    	
    	for(int i = 0; i < people_list.size(); i++) {
    		if(people_list.get(i).getUsername().equals(username) && people_list.get(i).getPassword().equals(password)) {
    			loggedin = people_list.get(i);
    			return true;
    		}
    	}
    	return false; // default case
    }
    
    private void PassengerMainMenu() {
    	JFrame main_menu = new JFrame("Train Reservation System");

        main_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main_menu.setSize(1000, 700);
        main_menu.setLocationRelativeTo(null);

        JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        main_menu.setContentPane(pane1);
        
        pane1.setBackground(new Color(173, 216, 230));
        
        JLabel biglabel = new JLabel("  WELCOME, " + loggedin.getName().toUpperCase() + "!");
        biglabel.setFont(new Font("Arial", Font.ITALIC, 30));
        biglabel.setHorizontalAlignment(JLabel.CENTER);
        
        pane1.add(biglabel, BorderLayout.NORTH);

        main_menu.setVisible(true);
        
        main_menu.setContentPane(pane1);
        
        JButton view_train_button = new JButton("View available trains");
        JButton book_train_button = new JButton("Book a train");
        JButton view_booking_button = new JButton("View bookings");
        
        view_train_button.setPreferredSize(new Dimension(200, 40));
        book_train_button.setPreferredSize(new Dimension(200, 40));
        view_booking_button.setPreferredSize(new Dimension(200, 40));
        
        pane1.add(view_train_button);
        pane1.add(book_train_button);
        pane1.add(view_booking_button);
    }
    
    private void ManagerMainMenu() {
    	JFrame main_menu = new JFrame("Train Reservation System");

        main_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main_menu.setSize(1000, 700);
        main_menu.setLocationRelativeTo(null);

        JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        main_menu.setContentPane(pane1);
        
        pane1.setBackground(new Color(173, 216, 230));
        
        JLabel biglabel = new JLabel("  WELCOME, " + loggedin.getName().toUpperCase() + "!");
        biglabel.setFont(new Font("Arial", Font.ITALIC, 30));
        biglabel.setHorizontalAlignment(JLabel.CENTER);
        
        pane1.add(biglabel, BorderLayout.NORTH);

        main_menu.setVisible(true);
        
        main_menu.setContentPane(pane1);
        
        JButton manage_train = new JButton("Manage trains");
        JButton cancel_train = new JButton("Cancel a train");
        JButton create_new_train = new JButton("Create a new train");
        
        manage_train.setPreferredSize(new Dimension(200, 40));
        cancel_train.setPreferredSize(new Dimension(200, 40));
        create_new_train.setPreferredSize(new Dimension(200, 40));
        
        pane1.add(manage_train);
        pane1.add(cancel_train);
        pane1.add(create_new_train);
        
        manage_train.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	manage_train();
            }
        });
        
        cancel_train.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel_train();
            }
        });
        
        create_new_train.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	create_train();
            }
        });
        
    }
    private void create_train() {
    	JTextField traincode = new JTextField();
		
		Object[] inputFields = {"Train code:", traincode};
		
		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Train", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		String code_str = traincode.getText();
		
		
		if (option == JOptionPane.OK_OPTION) {
			if(Integer.valueOf(code_str) < 0) {
				String error_message = "Train code must 0 or greater";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				Train t = new Train();
				t.setTrainCode(Integer.valueOf(code_str));
				data1.add_train(t);
				
				String success_msg = "Successfully created new train with code " + code_str;
				JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
    }
    
    private void cancel_train() {
    	JTextField traincode = new JTextField();
		
		Object[] inputFields = {"Train code:", traincode};
		
		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Train", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		String code_str = traincode.getText();
		
		if (option == JOptionPane.OK_OPTION) {
			int index = -1;
			for(int i = 0; i < data1.get_trains().size(); i++) {
				if (data1.get_trains().get(i).getTrainCode() == Integer.valueOf(code_str)) {
					index = i;
				}
			}
			if(index == -1) {
				String error_message = "Train not found";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				data1.get_trains().remove(data1.get_trains().get(index));
				String success_msg = "Successfully removed train " + code_str;
				JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
    }

    private void manage_train() {
    	// implement later
    	JTextField traincode = new JTextField();
		
		Object[] inputFields = {"Enter the train code you would like to manage:", traincode};
		
		int option = JOptionPane.showConfirmDialog(null, inputFields, "Manage Train", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		String code_str = traincode.getText();
		
		if (option == JOptionPane.OK_OPTION) {
			if(Integer.valueOf(code_str) < 0) {
				String error_message = "Train code must 0 or greater";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				int index = -1;
				for(int i = 0; i < data1.get_trains().size(); i++) {
					if (data1.get_trains().get(i).getTrainCode() == Integer.valueOf(code_str)) {
						index = i;
					}
				}
				
				if(index == -1) {
					String error_msg = "Train " + code_str + " does not exist";
					JOptionPane.showMessageDialog(null, error_msg, "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					String success_msg = "Found train " + code_str;
					JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
					ManageTrainPanel(data1.get_trains().get(index));
				}	
			}
		}
    }
    public void ManageTrainPanel(Train t) {
		JFrame manage_menu = new JFrame("Manage Train");
		manage_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		manage_menu.setSize(265, 300);
		manage_menu.setLocationRelativeTo(null);

        JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        manage_menu.setContentPane(pane1);
        
        pane1.setBackground(new Color(173, 216, 230));
        
        manage_menu.setContentPane(pane1);
        
        JButton update_status = new JButton("Update status");
        JButton set_ticket_price = new JButton("Set ticket price");
        JButton manage_passengers = new JButton("Manage passengers");
        JButton change_ticket_price = new JButton("Change ticket price");
        JButton view_train_status = new JButton("View train status");
        
        update_status.setPreferredSize(new Dimension(200, 40));
        set_ticket_price.setPreferredSize(new Dimension(200, 40));
        manage_passengers.setPreferredSize(new Dimension(200, 40));
        change_ticket_price.setPreferredSize(new Dimension(200, 40));
        view_train_status.setPreferredSize(new Dimension(200, 40));
        
        pane1.add(update_status); pane1.add(set_ticket_price); pane1.add(manage_passengers); pane1.add(change_ticket_price); pane1.add(view_train_status);
        
        update_status.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	update_status(t);
            }
        });
        
        set_ticket_price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_ticket_price(t);
            }
        });
        
        manage_passengers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	manage_passengers(t);
            }
        });
        
        change_ticket_price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	change_ticket_price(t);
            }
        });
        
        view_train_status.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	view_train_status(t);
            }
        });
        
        manage_menu.setVisible(true);
	}
    
    private void update_status(Train t) {
    	
    }
    
    private void set_ticket_price(Train t) {
    	
    }
    
    private void manage_passengers(Train t) {
    	
    }
    
    private void change_ticket_price(Train t) {
    	
    }
    
    private void view_train_status(Train t) {
    	JOptionPane.showMessageDialog(null, "Train " + t.getTrainCode() + " is " + t.getStatus(), "Train Status", JOptionPane.PLAIN_MESSAGE);
    }
}

