package Software;

import javax.swing.*;

import Hardware.Route;
import Hardware.Train;
import People.Manager;
import People.Passenger;
import People.Person;
import Software.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
		ImageIcon bottomLeftGraphic = new ImageIcon("./src/Software/resources/trainImage1.png");
		ImageIcon modifiedIcon = makeWhitePixelsTransparent(bottomLeftGraphic);
		JLabel bottomLeftLabel = new JLabel(modifiedIcon);

		JPanel imagePanel = new JPanel(new BorderLayout());
		imagePanel.setBackground(new Color(173, 216, 230));

		imagePanel.add(bottomLeftLabel, BorderLayout.WEST);

		// Adding wildcat Logo to the right
		ImageIcon bottomRightGraphic = new ImageIcon("./src/Software/resources/wildcatLogo.png");
		ImageIcon modifiedIcon2 = makeWhitePixelsTransparent(bottomRightGraphic);
		JLabel bottomRightLabel = new JLabel(modifiedIcon2);

		imagePanel.add(bottomRightLabel, BorderLayout.EAST);

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.add(loginPanel);

		add(centerPanel, BorderLayout.CENTER);
		add(imagePanel, BorderLayout.SOUTH);
	}

//	private ImageIcon createImageIcon(String filename) {
//		URL url = getClass().getClassLoader().getResource(filename);
//		if (url != null) {
//			return new ImageIcon(url);
//		} else {
//			System.err.println("Resource not found: " + filename);
//			return null;
//		}
//	}

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

		Image modifiedImage = Toolkit.getDefaultToolkit()
				.createImage(new FilteredImageSource(image.getSource(), filter));

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
	public static Data data1 = new Data();
	public ArrayList<Person> people_list = new ArrayList<Person>();
	public ArrayList<Passenger> passenger_list = new ArrayList<Passenger>();
	public ArrayList<Manager> manager_list = new ArrayList<Manager>();
	private Person loggedin;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public Manager m1 = new Manager();

	public LoginPanel() {
		// initializing m1 to test - can change to a driver that we pass into the GUI
		// file or from data.java
		/*
		 * m1.createTrain(113); m1.createTrain(227); m1.createTrain(331);
		 * m1.createRoute("Bend", "Portland", 1700, 1930, 45);
		 * m1.addRouteToTrain(m1.getTrainsManaged().get(0), m1.createRoute("Tucson",
		 * "Phoenix", 1200, 1400, 40)); m1.addRouteToTrain(m1.getTrainsManaged().get(1),
		 * m1.createRoute("Tucson", "Phoenix", 1200, 1400, 40));
		 * m1.addRouteToTrain(m1.getTrainsManaged().get(2), m1.createRoute("Tucson",
		 * "Phoenix", 1200, 1400, 40)); m1.addRouteToTrain(m1.getTrainsManaged().get(0),
		 * m1.createRoute("Bend", "Portland", 1700, 1930, 45));
		 * m1.addRouteToTrain(m1.getTrainsManaged().get(0), m1.createRoute("Bend",
		 * "Portland", 1300, 1430, 45));
		 * m1.addRouteToTrain(m1.getTrainsManaged().get(0), m1.createRoute("Bend",
		 * "Portland", 900, 1100, 45));
		 * 
		 * manager_list.add(m1);
		 */

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
				login(m1);
			}
		});

		createAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});
	}

	private void login(Manager m) {
		String username = usernameField.getText();
		char[] password = passwordField.getPassword();

		if (isValidLogin(username, new String(password))) {
			JOptionPane.showMessageDialog(this, "Login Successful");
			if (loggedin instanceof Passenger) {
				Passenger p = (Passenger) loggedin;
				PassengerMainMenu(m, username, p);
			} else if (loggedin instanceof Manager) {
				ManagerMainMenu();
			}
			// SwingUtilities.getWindowAncestor(this).dispose();
			SwingUtilities.getWindowAncestor(this).setVisible(false);
		} else {
			JOptionPane.showMessageDialog(this, "Login Failed");
		}
	}

	private void createAccount() {
		// JOptionPane.showMessageDialog(this, "Create Account functionality not
		// implemented yet.");
		String[] items = { "Passenger", "Manager" };

		// Create a JComboBox with the array of items
		JComboBox<String> comboBox = new JComboBox<>(items);

		// Create a custom JPanel to hold the JComboBox
		JPanel create_account_panel = new JPanel(new GridLayout(5, 1));
		create_account_panel.add(new JLabel("Select account type:"));
		create_account_panel.add(comboBox);

		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		JTextField name = new JTextField();
		JTextField email = new JTextField();
		;

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
		int result = JOptionPane.showOptionDialog(null, create_account_panel, "Create New Account",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

		String username_str = username.getText();
		char[] password_char = password.getPassword();
		String name_str = name.getText();
		String email_str = email.getText();
		String password_str = String.valueOf(password_char);

		// Check the result when OK is clicked
		if (result == JOptionPane.OK_OPTION) {
			String selectedValue = (String) comboBox.getSelectedItem();
			if (selectedValue.equals("Passenger")) {
				selectedValue = (String) comboBox.getSelectedItem();

				if (selectedValue.equals("Passenger")) {
					if (!isValidUsername(name_str)) {
						JOptionPane.showMessageDialog(null,
								"That username is already being used.\nPlease enter a different one.",
								"Username already taken", JOptionPane.ERROR_MESSAGE);
					}

					else if (!isValidPassword(password_str)) {
						JOptionPane.showMessageDialog(null, "Password must be 8 characters or more.",
								"Please enter a new password", JOptionPane.ERROR_MESSAGE);
					} else {
						Passenger p = new Passenger();
						p.setName(name_str);
						p.setUsername(username_str);
						p.setPassword(password_str);
						p.setEmail(email_str);
						people_list.add(p);
						passenger_list.add(p);

						Passenger.saveData(people_list);

						JOptionPane.showMessageDialog(null, "Created a " + selectedValue + " account for " + name_str);
					}
				} else if (selectedValue.equals("Manager")) {
					if (selectedValue.equals("Passenger")) {
						if (!isValidUsername(name_str)) {
							JOptionPane.showMessageDialog(null,
									"That username is already being used.\nPlease enter a different one.",
									"Username already taken", JOptionPane.ERROR_MESSAGE);
						}

						else if (!isValidPassword(password_str)) {
							JOptionPane.showMessageDialog(null, "Password must be 8 characters or more.",
									"Please enter a new password", JOptionPane.ERROR_MESSAGE);
						} else {
							Manager m = new Manager();
							m.setName(name_str);
							m.setUsername(username_str);
							m.setPassword(password_str);
							m.setEmail(email_str);
							people_list.add(m);
							manager_list.add(m);

							Manager.saveData(people_list);

							JOptionPane.showMessageDialog(null,
									"Created a " + selectedValue + " account for " + name_str);
						}
					}
				}
			}
		}
	}

	// find is username is already taken... needs to be unique
	private boolean isValidUsername(String username) {
		people_list = Person.loadData();

		for (int i = 0; i < people_list.size(); i++) {
			if (people_list.get(i).getUsername().equals(username)) {
				return false;
			}
		}
		return true; // default case
	}

	// check if password is 8 or more characters
	private boolean isValidPassword(String password) {
		people_list = Person.loadData();
		if (password.length() < 8) {
			return false;
		}
		return true; // default case
	}

	// check if username and login are a valid match to the system
	private boolean isValidLogin(String username, String password) {
		people_list = Person.loadData();

		for (int i = 0; i < people_list.size(); i++) {
			if (people_list.get(i).getUsername().equals(username)
					&& people_list.get(i).getPassword().equals(password)) {
				loggedin = people_list.get(i);
				return true;
			}
		}
		return false; // default case
	}

	// Passenger main menu stuff
	private void PassengerMainMenu(Manager m, String name, Passenger p) {
		JFrame main_menu = new JFrame("Train Reservation System");

		main_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main_menu.setSize(430, 190);
		main_menu.setLocationRelativeTo(null);
		main_menu.setResizable(false);

		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		main_menu.setContentPane(pane1);

		pane1.setBackground(new Color(173, 216, 230));

		JLabel biglabel = new JLabel("  WELCOME, " + loggedin.getName().toUpperCase() + "!");
		biglabel.setFont(new Font("Arial", Font.ITALIC, 30));
		biglabel.setHorizontalAlignment(JLabel.LEFT);

		pane1.add(biglabel, BorderLayout.NORTH);

		main_menu.setVisible(true);

		main_menu.setContentPane(pane1);

		Icon icon = new ImageIcon("./src/Software/resources/logout.png");
		JButton logout = new JButton(icon);

		JButton view_train_button = new JButton("View available trains");
		JButton book_train_button = new JButton("Book a train");
		JButton view_booking_button = new JButton("View booking");
		JButton cancel_button = new JButton("Cancel booking");

		logout.setFocusable(false);
		view_train_button.setFocusable(false);
		book_train_button.setFocusable(false);
		view_booking_button.setFocusable(false);
		cancel_button.setFocusable(false);

		logout.setPreferredSize(new Dimension(60, 40));
		view_train_button.setPreferredSize(new Dimension(200, 40));
		book_train_button.setPreferredSize(new Dimension(200, 40));
		view_booking_button.setPreferredSize(new Dimension(200, 40));
		cancel_button.setPreferredSize(new Dimension(200, 40));

		pane1.add(logout);
		pane1.add(view_train_button);
		pane1.add(book_train_button);
		pane1.add(view_booking_button);
		pane1.add(cancel_button);

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout(logout);
			}
		});

		view_train_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewAvailableTrains(m, name); // Call your function
			}
		});

		book_train_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookATrain(name, p);
			}
		});

		view_booking_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewBookings(name, p);
			}
		});

		cancel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelBooking(name, p);
			}
		});
	}

	private void viewAvailableTrains(Manager m, String name) {
		redirectToGUI();
		System.out.println("Viewing available trains:\n");
		/*
		 * m.viewTrains();
		 */
		if (data1.get_trains().size() != 0) {
			for (int i = 0; i < data1.get_trains().size(); i++) {
				System.out.println("Train " + data1.get_trains().get(i).getTrainCode() + " is currently "
						+ data1.get_trains().get(i).getStatus());
				System.out.println("Train routes available: ");
				if (data1.get_trains().get(i).getRouteList().size() == 0) {
					System.out.println("No routes scheduled");
				} else {
					for (int j = 0; j < data1.get_trains().get(i).getRouteList().size(); j++) {
						System.out.println(
								"Departing from: " + data1.get_trains().get(i).getRouteList().get(j).getStartLocation()
										+ " at " + data1.get_trains().get(i).getRouteList().get(j).getDepartureTime());
						System.out.println("Arriving at: "
								+ data1.get_trains().get(i).getRouteList().get(j).getEndLocation() + " at "
								+ data1.get_trains().get(i).getRouteList().get(j).getArrivalTime() + "\n");
					}
				}
			}
		} else {
			System.out.println("No trains could not be found.");
		}
	}

	private void bookATrain(String name, Passenger p) {
		if (p.getbookedTrain() != null) {
			JOptionPane.showMessageDialog(null, "You already have a train booked", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		System.out.println("Book a Train...");
		JPanel destinationPanel = new JPanel(new GridLayout(2, 2));

		JLabel startDestLabel = new JLabel("Enter desired start destination: ");
		JTextField startDestField = new JTextField();

		JLabel endDestLabel = new JLabel("Enter desired end destination: ");
		JTextField endDestField = new JTextField();

		destinationPanel.add(startDestLabel);
		destinationPanel.add(startDestField);

		destinationPanel.add(endDestLabel);
		destinationPanel.add(endDestField);

		int result = JOptionPane.showConfirmDialog(null, destinationPanel, "Book a Train",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (isEmpty(startDestField) || isEmpty(endDestField)) {
				JOptionPane.showMessageDialog(null, "Error: User input cannot be empty", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			else {
				String departure = startDestField.getText();
				String destination = endDestField.getText();

				JFrame frame = new JFrame("Route Selection");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(400, 300);
				frame.setLayout(new BorderLayout());
				frame.setLocationRelativeTo(null);

				// Create a panel to display the list of routes
				JPanel routePanel = new JPanel();
				routePanel.setLayout(new BoxLayout(routePanel, BoxLayout.Y_AXIS));

				boolean available = false;
				for (int i = 0; i < data1.get_trains().size(); i++) {
					for (int j = 0; j < data1.get_trains().get(i).getRouteList().size(); j++) {
						if (data1.get_trains().get(i).getRouteList().get(j).getStartLocation()
								.equalsIgnoreCase(departure)
								&& data1.get_trains().get(i).getRouteList().get(j).getEndLocation()
										.equalsIgnoreCase(destination)) {
							available = true;
						}
					}
				}
				if (available == false) {
					JOptionPane.showMessageDialog(null, "Sorry, there is no routes available from " + departure + " to "
							+ destination + " at this time.", "No available routes", JOptionPane.ERROR_MESSAGE);
				} else {
					// for(Train t : m1.getTrainsManaged()) {
					for (int i = 0; i < data1.get_trains().size(); i++) {
						// for(Route r : t.getRouteList()) {
						for (int j = 0; j < data1.get_trains().get(i).getRouteList().size(); j++) {
							if (data1.get_trains().get(i).getRouteList().get(j).getStartLocation()
									.equalsIgnoreCase(departure)
									&& data1.get_trains().get(i).getRouteList().get(j).getEndLocation()
											.equalsIgnoreCase(destination)) {
								final int finali = i;
								final int finalj = j;
								// Populate the panel with route entries
								JPanel routeEntryPanel = new JPanel(new BorderLayout());
								JLabel routeLabel = new JLabel("<html>Train " + data1.get_trains().get(i).getTrainCode()
										+ ": " + data1.get_trains().get(i).getRouteList().get(j).getStartLocation()
										+ "->" + data1.get_trains().get(i).getRouteList().get(j).getEndLocation()
										+ "<br>" + "Departing at: "
										+ data1.get_trains().get(i).getRouteList().get(j).getDepartureTime()
										+ ", Arriving at: "
										+ data1.get_trains().get(i).getRouteList().get(j).getArrivalTime() + "<br>"
										+ "Price: $" + data1.get_trains().get(i).getRouteList().get(j).getPrice()
										+ "</html>");
								JButton selectButton = new JButton("Select");
								selectButton.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) { // select button for this specific
																					// train
										// showConfirmationGUI(data1.get_trains().get(i).getRouteList().get(j), p,
										// data1.get_trains().get(i));
										p.bookTrain(data1.get_trains().get(finali),
												data1.get_trains().get(finali).getRouteList().get(finalj));
										JOptionPane.showMessageDialog(null, "You have successfully booked the train to "
												+ data1.get_trains().get(finali).getRouteList().get(finalj)
														.getEndLocation()
												+ " that departs at "
												+ data1.get_trains().get(finali).getRouteList().get(finalj)
														.getDepartureTime()
												+ " for $"
												+ data1.get_trains().get(finali).getRouteList().get(finalj).getPrice(),
												"Booked train", JOptionPane.PLAIN_MESSAGE);
										frame.dispose();
									}
								});
								// routeLabel.setHorizontalAlignment();
								routeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
								routeEntryPanel.add(routeLabel, BorderLayout.CENTER);
								routeEntryPanel.add(selectButton, BorderLayout.EAST);
								routePanel.add(routeEntryPanel);

							}
						}
					}
					// Add the route panel to a scroll pane
					JScrollPane scrollPane = new JScrollPane(routePanel);

					frame.add(scrollPane, BorderLayout.CENTER);
					frame.setVisible(true);
				}
			}
		}

	}

	private Boolean isThereRouteAvailable(String dep, String dest) {
		for (Train t : m1.getTrainsManaged()) {
			for (Route r : t.getRouteList()) {
				if (r.getStartLocation().equalsIgnoreCase(dep) && r.getEndLocation().equalsIgnoreCase(dest)) {
					return true;
				}
			}
		}
		return false;
	}

	private void cancelBooking(String name, Passenger p) {
		Train t = p.getbookedTrain();
		if (p.getbookedTrain() == null) {
			JOptionPane.showMessageDialog(null, "You do not have a booking at this time", "No booking",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JFrame frame = new JFrame("Booking Cancellation Confirmation");
			frame.setSize(400, 100);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();
			JLabel messageLabel = new JLabel("Are you sure you want to cancel your booking from "
					+ p.getBookedRoute().getStartLocation() + "->" + p.getBookedRoute().getEndLocation() + "?");
			JButton yesButton = new JButton("Yes");
			JButton noButton = new JButton("No");

			yesButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					p.cancelBooking(t);
					JOptionPane.showMessageDialog(null, "Booking from " + p.getBookedRoute().getStartLocation() + "->"
							+ p.getBookedRoute().getEndLocation() + " canceled!");
					frame.dispose();
				}
			});

			noButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Booking not cancelled.");
					frame.dispose();
				}
			});

			panel.add(messageLabel);
			panel.add(yesButton);
			panel.add(noButton);

			frame.getContentPane().add(panel);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	private void showConfirmationGUI(Route selectedRoute, Passenger passenger, Train t) {
		JFrame confirmationFrame = new JFrame("Confirmation");
		confirmationFrame.setSize(300, 150);
		confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		confirmationFrame.setLayout(new BorderLayout());

		JLabel confirmationLabel = new JLabel("Selected Route: " + selectedRoute.printRoute());
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Perform actions on confirmation if needed
				JOptionPane.showMessageDialog(null, "Train booked successfully");
				confirmationFrame.dispose();
				passenger.bookTrain(t, selectedRoute);
			}
		});

		confirmationFrame.add(confirmationLabel, BorderLayout.CENTER);
		confirmationFrame.add(okButton, BorderLayout.SOUTH);
		confirmationFrame.setLocationRelativeTo(null);
		confirmationFrame.setVisible(true);
	}

	private static boolean isEmpty(JTextField textField) {
		return textField.getText().trim().isEmpty();
	}

	private void viewBookings(String name, Passenger p) {
		if (p.getbookedTrain() == null) {
			JOptionPane.showMessageDialog(null, "You have no current bookings.\n", "No bookings",
					JOptionPane.ERROR_MESSAGE);
		} else if (p != null) {
			/*
			 * redirectToGUI(); System.out.println("Viewing bookings..."); p.viewBooking();
			 */
			JOptionPane.showMessageDialog(null,
					"Your booking is currently " + p.getBookedRoute().getStartLocation() + "->"
							+ p.getBookedRoute().getEndLocation() + " departing at "
							+ p.getBookedRoute().getDepartureTime(),
					"Viewing Booking", JOptionPane.PLAIN_MESSAGE);
		}
	}
	// passenger menu stuff ends

	// Manager train menu stuff
	private void ManagerMainMenu() {
		JFrame main_menu = new JFrame("Train Reservation System");
		setLayout(new BorderLayout());

		main_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main_menu.setSize(450, 250);
		main_menu.setLocationRelativeTo(null);
		main_menu.setResizable(false);

		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		main_menu.setContentPane(pane1);

		pane1.setBackground(new Color(173, 216, 230));

		JLabel biglabel = new JLabel("  WELCOME, " + loggedin.getName().toUpperCase() + "!");
		biglabel.setFont(new Font("Arial", Font.ITALIC, 30));
		biglabel.setHorizontalAlignment(JLabel.LEFT);

		pane1.add(biglabel, BorderLayout.NORTH);

		main_menu.setVisible(true);

		main_menu.setContentPane(pane1);

		JPanel buttonpanel = new JPanel(new GridLayout(4, 1));

		main_menu.add(buttonpanel);

		JButton manage_train = new JButton("Manage trains");
		JButton cancel_train = new JButton("Cancel a train");
		JButton create_new_train = new JButton("Create a new train");
		JButton manage_route = new JButton("View routes");
		JButton create_route = new JButton("Create route");
		Icon icon = new ImageIcon("./src/Software/resources/logout.png");
		JButton logout = new JButton(icon);

		logout.setFocusable(false);
		manage_train.setFocusable(false);
		cancel_train.setFocusable(false);
		create_new_train.setFocusable(false);

		manage_train.setPreferredSize(new Dimension(200, 40));
		cancel_train.setPreferredSize(new Dimension(200, 40));
		create_new_train.setPreferredSize(new Dimension(200, 40));
		create_route.setPreferredSize(new Dimension(200, 40));
		manage_route.setPreferredSize(new Dimension(200, 40));
		logout.setPreferredSize(new Dimension(60, 40));

		pane1.add(logout);
		pane1.add(manage_train);
		pane1.add(create_new_train);
		pane1.add(cancel_train);
		pane1.add(create_route);
		pane1.add(manage_route);

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

		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout(logout);
			}
		});

		manage_route.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage_route();
			}
		});

		create_route.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create_route();
			}
		});
	}

	private void manage_route() {
		ArrayList<String> options = new ArrayList<String>();

		if (data1.get_routes().size() == 0) {
			JOptionPane.showMessageDialog(null, "There are currently no routes", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < data1.get_routes().size(); i++) {
			options.add("Route " + data1.get_routes().get(i).getStartLocation() + " - "
					+ data1.get_routes().get(i).getEndLocation() + ": "
					+ Route.timeDisplay(data1.get_routes().get(i).getDepartureTime()) + " - "
					+ Route.timeDisplay(data1.get_routes().get(i).getArrivalTime()));
		}
		String[] option_str = options.toArray(new String[0]);
		JComboBox<String> combobox = new JComboBox<>(option_str);

		int option = JOptionPane.showConfirmDialog(null, combobox, "Pick Route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		int selectedValue = (int) combobox.getSelectedIndex(); // index starts at 0

		if (option == JOptionPane.OK_OPTION) {
			routeManagePanel(data1.get_routes().get(selectedValue));
		}
	}

	private void change_route_price(Route t) {
		JTextField price = new JTextField();
		Object[] input = { "New price", price };
		int option = JOptionPane.showConfirmDialog(null, input, "Change route price", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.OK_OPTION) {
			if (price.getText().equals("")) {
				String error_message = "Please input all the neccessary fields.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (Integer.valueOf(price.getText()) < 0) {
				String error_message = "Price cannot be negative.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				t.setPrice(Integer.valueOf(price.getText()));
				JOptionPane.showMessageDialog(null, "Successfully changed price to " + Integer.valueOf(price.getText()),
						"Success", JOptionPane.PLAIN_MESSAGE);
				return;
			}
		}

	}

	private void change_route_time(Route t) {
		JTextField depart = new JTextField();
		JTextField arrive = new JTextField();
		Object[] input = { "New departure time:", depart, "New arrival time:", arrive };
		int option = JOptionPane.showConfirmDialog(null, input, "Change route schedule", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.OK_OPTION) {
			if (depart.getText().equals("") || arrive.getText().equals("")) {
				String error_message = "Please input all the neccessary fields.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int departureTime = Integer.valueOf(depart.getText());
			int arrivalTime = Integer.valueOf(arrive.getText());
			if (departureTime < 0 || departureTime > 2359 || arrivalTime < 0 || arrivalTime > 2359) {
				String error_message = "Invalid time code.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				t.setArrivalTime(arrivalTime);
				t.setDepartureTime(departureTime);
				JOptionPane.showMessageDialog(null, "Successfully set new time.",
						"Success", JOptionPane.PLAIN_MESSAGE);
				return;
			}
		}

	}

	private void routeManagePanel(Route r) {
		JFrame route_menu = new JFrame("Commands for route " + r.getStartLocation() + " - " + r.getEndLocation() + ": "
				+ Route.timeDisplay(r.getDepartureTime()) + " - " + Route.timeDisplay(r.getArrivalTime()));
		route_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		route_menu.setSize(470, 200);
		route_menu.setResizable(false);
		route_menu.setLocationRelativeTo(null);

		// JPanel imagePanel = new JPanel(new BorderLayout());

		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		route_menu.setContentPane(pane1);
		// pane1.add(new JLabel(new ImageIcon("wildcatLogo.png")));

		pane1.setBackground(new Color(173, 216, 230));

		JButton change_route_price = new JButton("Change route pricing.");
		JButton change_route_time = new JButton("Change departure and arrival time of this route.");
		JButton routeInfoDisplay = new JButton("Display route information.");

		pane1.add(change_route_price);
		pane1.add(change_route_time);
		pane1.add(routeInfoDisplay);

		change_route_price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_route_price(r);
			}
		});

		change_route_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_route_time(r);
			}
		});

		routeInfoDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				routeInfoDisplay(r);
			}
		});

		route_menu.setContentPane(pane1);
		route_menu.setVisible(true);
	}

	class JTextAreaOutputStream extends OutputStream {
		private final JTextArea destination;

		public JTextAreaOutputStream(JTextArea destination) {
			if (destination == null)
				throw new IllegalArgumentException("Destination is null");

			this.destination = destination;
		}

		@Override
		public void write(byte[] buffer, int offset, int length) throws IOException {
			final String text = new String(buffer, offset, length);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					destination.append(text);
				}
			});
		}

		@Override
		public void write(int b) throws IOException {
			write(new byte[] { (byte) b }, 0, 1);
		}
	}

	private void routeInfoDisplay(Route r) {
		JTextArea outputArea;
		JFrame popInfo = new JFrame();
		JScrollPane scrollPane = null;
		popInfo.setTitle("Route Info");
		popInfo.setLayout(new FlowLayout());
		outputArea = new JTextArea(20, 50);
		outputArea.setEditable(false);
		scrollPane = new JScrollPane(outputArea);
		popInfo.add(scrollPane);
		popInfo.pack();
		popInfo.setVisible(true);
		JTextAreaOutputStream out = new JTextAreaOutputStream(outputArea);
		System.setOut(new PrintStream(out));
		r.routeDisplay();
	}

	private void create_route() {
		JTextField startLoc = new JTextField();
		JTextField endLoc = new JTextField();
		JTextField departureTime = new JTextField();
		JTextField arrivalTime = new JTextField();
		JTextField price = new JTextField();

		Object[] inputFields = { "Starting Location:", startLoc, "Destination:", endLoc, "Departs at:", departureTime,
				"Arrives at:", arrivalTime, "Pricing:", price };

		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		boolean repeat = false;
		String start = startLoc.getText();
		String end = endLoc.getText();

		if (option == JOptionPane.OK_OPTION) {
			if (start.equals("") || end.equals("") || departureTime.getText().equals("")
					|| arrivalTime.getText().equals("") || price.getText().equals("")) {
				String error_message = "Please input all the neccessary fields.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int depart = Integer.valueOf(departureTime.getText());
			int arrive = Integer.valueOf(arrivalTime.getText());
			int pricing = Integer.valueOf(price.getText());

			for (Route r : data1.get_routes()) {
				if (r.getStartLocation().equals(start) && r.getEndLocation().equals(end) && r.getArrivalTime() == arrive
						&& r.getDepartureTime() == depart) {
					repeat = true;
				}
			}

			if (repeat) {
				String error_message = "This route already exists, same start-end location and arrival-departure time.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (depart < 0 || depart > 2359 || arrive < 0 || arrive > 2359) {
				String error_message = "Invalid time code.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (pricing < 0) {
				String error_message = "Price cannot be negative.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Route r = new Route();
				r.setStartLocation(start);
				r.setEndLocation(end);
				r.setArrivalTime(arrive);
				r.setDepartureTime(depart);
				r.setPrice(pricing);
				data1.get_routes().add(r);
				String success_msg = "Successfully created new route " + start + " - " + end;
				JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void logout(JButton logoutbutton) {
		JFrame mainMenuFrame = (JFrame) SwingUtilities.getRoot(logoutbutton);
		mainMenuFrame.dispose();
		MainGUI maingui = new MainGUI();

		maingui.setVisible(true);
	}

	private void create_train() {
		JTextField traincode = new JTextField();

		Object[] inputFields = { "Train code:", traincode };

		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Train", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		String code_str = traincode.getText();

		boolean repeat = false;
		for (int i = 0; i < data1.get_trains().size(); i++) {
			if (data1.get_trains().get(i).getTrainCode() == Integer.valueOf(code_str)) {
				repeat = true;
			}
		}

		if (option == JOptionPane.OK_OPTION) {
			if (code_str.equals("")) {
				String error_message = "Please input train code.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (Integer.valueOf(code_str) < 0) {
				String error_message = "Train code must 0 or greater";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (repeat == true) {
				String error_message = "There is already a train with code " + code_str;
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Train t = new Train();
				t.setTrainCode(Integer.valueOf(code_str));
				data1.add_train(t);

				String success_msg = "Successfully created new train with code " + code_str;
				JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void cancel_train() {
		ArrayList<String> options = new ArrayList<String>();

		if (data1.get_trains().size() == 0) {
			JOptionPane.showMessageDialog(null, "There are currently no trains", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < data1.get_trains().size(); i++) {
			options.add("Train " + data1.get_trains().get(i).getTrainCode());
		}
		String[] option_str = options.toArray(new String[0]);
		JComboBox<String> combobox = new JComboBox<>(option_str);

		int option = JOptionPane.showConfirmDialog(null, combobox, "Pick train to cancel", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		int selectedValue = (int) combobox.getSelectedIndex(); // index starts at 0

		int traincode = data1.get_trains().get(selectedValue).getTrainCode();
		if (option == JOptionPane.OK_OPTION) {
			data1.get_trains().remove(selectedValue);
			JOptionPane.showMessageDialog(null, "Successfully cancelled train " + traincode, "Success",
					JOptionPane.PLAIN_MESSAGE);
		}
		/*
		 * JTextField traincode = new JTextField();
		 * 
		 * Object[] inputFields = {"Train code:", traincode};
		 * 
		 * int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Train",
		 * JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		 * 
		 * String code_str = traincode.getText();
		 * 
		 * if (option == JOptionPane.OK_OPTION) { int index = -1; for(int i = 0; i <
		 * data1.get_trains().size(); i++) { if
		 * (data1.get_trains().get(i).getTrainCode() == Integer.valueOf(code_str)) {
		 * index = i; } } if(index == -1) { String error_message = "Train not found";
		 * JOptionPane.showMessageDialog(null, error_message, "Error",
		 * JOptionPane.ERROR_MESSAGE); return; } else {
		 * data1.get_trains().remove(data1.get_trains().get(index)); String success_msg
		 * = "Successfully removed train " + code_str;
		 * JOptionPane.showMessageDialog(null, success_msg, "Success",
		 * JOptionPane.PLAIN_MESSAGE); } }
		 */
	}

	private void manage_train() {
		// implement later
		ArrayList<String> options = new ArrayList<String>();

		if (data1.get_trains().size() == 0) {
			JOptionPane.showMessageDialog(null, "There are currently no trains", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < data1.get_trains().size(); i++) {
			options.add("Train " + data1.get_trains().get(i).getTrainCode());
		}
		String[] option_str = options.toArray(new String[0]);
		JComboBox<String> combobox = new JComboBox<>(option_str);

		int option = JOptionPane.showConfirmDialog(null, combobox, "Pick Train", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		int selectedValue = (int) combobox.getSelectedIndex(); // index starts at 0

		if (option == JOptionPane.OK_OPTION) {
			ManageTrainPanel(data1.get_trains().get(selectedValue));
		}
	}

	public void ManageTrainPanel(Train t) {
		JFrame manage_menu = new JFrame("Manage Train " + t.getTrainCode());
		manage_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		manage_menu.setSize(470, 200);
		manage_menu.setResizable(false);
		manage_menu.setLocationRelativeTo(null);

		// JPanel imagePanel = new JPanel(new BorderLayout());

		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		manage_menu.setContentPane(pane1);
		// pane1.add(new JLabel(new ImageIcon("wildcatLogo.png")));

		pane1.setBackground(new Color(173, 216, 230));

		manage_menu.setContentPane(pane1);

		JButton update_status = new JButton("Update status");
		JButton set_ticket_price = new JButton("Set ticket price");
		JButton manage_passengers = new JButton("Manage passengers");
		JButton add_route = new JButton("Add new route");
		JButton view_train_status = new JButton("View train status");
		JButton display_info = new JButton("Display train information");

		update_status.setPreferredSize(new Dimension(200, 40));
		set_ticket_price.setPreferredSize(new Dimension(200, 40));
		manage_passengers.setPreferredSize(new Dimension(200, 40));
		add_route.setPreferredSize(new Dimension(200, 40));
		view_train_status.setPreferredSize(new Dimension(200, 40));
		display_info.setPreferredSize(new Dimension(200, 40));

		pane1.add(update_status);
		pane1.add(set_ticket_price);
		pane1.add(manage_passengers);
		pane1.add(add_route);
		pane1.add(view_train_status);
		pane1.add(display_info);

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

		add_route.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_route(t);
			}
		});

		view_train_status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_train_status(t);
			}
		});
		display_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display_info(t);
			}
		});

		manage_menu.setVisible(true);
	}

	private void display_info(Train t) {
		JTextArea outputArea;
		JFrame popInfo = new JFrame();
		JScrollPane scrollPane = null;
		popInfo.setTitle("Route Info");
		popInfo.setLayout(new FlowLayout());
		outputArea = new JTextArea(20, 20);
		outputArea.setEditable(false);
		scrollPane = new JScrollPane(outputArea);
		popInfo.add(scrollPane);
		popInfo.pack();
		popInfo.setVisible(true);
		JTextAreaOutputStream out = new JTextAreaOutputStream(outputArea);
		System.setOut(new PrintStream(out));

	}

	private void add_route(Train t) {
		JFrame route_menu = new JFrame("Adding route to train schedule");
		route_menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		route_menu.setSize(470, 120);
		route_menu.setResizable(false);
		route_menu.setLocationRelativeTo(null);

		// JPanel imagePanel = new JPanel(new BorderLayout());

		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		route_menu.setContentPane(pane1);
		// pane1.add(new JLabel(new ImageIcon("wildcatLogo.png")));

		pane1.setBackground(new Color(173, 216, 230));

		JButton add_route = new JButton("Create a new route outside of existing list.");
		JButton select_route = new JButton("Select a route from the already created routes.");

//		add_route.setPreferredSize(new Dimension(200, 80));
//		select_route.setPreferredSize(new Dimension(200, 80));

		pane1.add(select_route);
		pane1.add(add_route);

		select_route.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_route(t);
			}
		});

		add_route.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_new_route(t);
			}
		});

		route_menu.setContentPane(pane1);
		route_menu.setVisible(true);
	}

	private void update_status(Train t) {
		JTextField trainstatus = new JTextField();

		Object[] inputFields = { "New train status:", trainstatus };

		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Train", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		String status_str = trainstatus.getText();

		if (option == JOptionPane.OK_OPTION) {
			if (status_str.equals("")) {
				JOptionPane.showMessageDialog(null, "Empty input for status", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				t.setStatus(status_str);
				JOptionPane.showMessageDialog(null, "Successfully changed status for train " + t.getTrainCode(),
						"Success", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void set_ticket_price(Train t) {
		// ArrayList<String>options = new ArrayList<String>();
		JTextField tier1 =  new JTextField();
		JTextField tier2 =  new JTextField();
		JTextField tier3 =  new JTextField();
		
		Object[] field = {"T1 Price", tier1, "T2 price", tier2, "T3 price", tier3};
		
		int option = JOptionPane.showConfirmDialog(null, field, "Change ticket price", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}

	private void manage_passengers(Train t) {
		// show number of passengers and give ability to remove passenger
		ArrayList<String> options = new ArrayList<String>();

		if (t.getPassengers().size() == 0) {
			JOptionPane.showMessageDialog(null, "There are currently no passengers on this train", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < t.getPassengers().size(); i++) {
			options.add(t.getPassengers().get(i).getName());
		}
		String[] option_str = options.toArray(new String[0]);
		JComboBox<String> combobox = new JComboBox<>(option_str);

		int option = JOptionPane.showConfirmDialog(null, combobox, "Pick Passenger", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		int selectedValue = (int) combobox.getSelectedIndex(); // index starts at 0

		// passenger name:
		// passenger route:
		// passenger seat:
		String route_msg;
		if (t.getRouteList().size() == 0) {
			route_msg = "No route booked";
		} else {
			route_msg = t.getPassengers().get(selectedValue).getBookedRoute().getStartLocation() + "->"
					+ t.getPassengers().get(selectedValue).getBookedRoute().getEndLocation();
		}

		if (option == JOptionPane.OK_OPTION) {
			String passenger_msg = "Name: " + t.getPassengers().get(selectedValue).getName() + "\nRoute: " + route_msg
					+ "\nSeat: NOT IMPLEMENTED";
			JOptionPane.showMessageDialog(null, passenger_msg,
					"Passenger " + t.getPassengers().get(selectedValue).getName() + " Info", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void select_route(Train t) {
		ArrayList<String> options = new ArrayList<String>();

		if (data1.get_routes().size() == 0) {
			JOptionPane.showMessageDialog(null, "There are currently no routes", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (int i = 0; i < data1.get_routes().size(); i++) {
			options.add("Route " + data1.get_routes().get(i).getStartLocation() + " - "
					+ data1.get_routes().get(i).getEndLocation() + ": "
					+ Route.timeDisplay(data1.get_routes().get(i).getDepartureTime()) + " - "
					+ Route.timeDisplay(data1.get_routes().get(i).getArrivalTime()));
		}
		String[] option_str = options.toArray(new String[0]);
		JComboBox<String> combobox = new JComboBox<>(option_str);

		int option = JOptionPane.showConfirmDialog(null, combobox, "Pick Route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		int selectedValue = (int) combobox.getSelectedIndex(); // index starts at 0

		if (option == JOptionPane.OK_OPTION) {
			Route r = data1.get_routes().get(selectedValue);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);
			t.addRoute(r);
			System.out.flush();
			System.setOut(old);
			String[] msgArr = baos.toString().split("\n");
			String msg = msgArr[0];
			JOptionPane.showMessageDialog(null, msg, "Route Validity Check", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void add_new_route(Train t) {
		JTextField startLoc = new JTextField();
		JTextField endLoc = new JTextField();
		JTextField departureTime = new JTextField();
		JTextField arrivalTime = new JTextField();
		JTextField price = new JTextField();

		Object[] inputFields = { "Starting Location:", startLoc, "Destination:", endLoc, "Departs at:", departureTime,
				"Arrives at:", arrivalTime, "Pricing:", price };

		int option = JOptionPane.showConfirmDialog(null, inputFields, "Create Route", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		boolean repeat = false;
		String start = startLoc.getText();
		String end = endLoc.getText();

		if (option == JOptionPane.OK_OPTION) {
			if (start.equals("") || end.equals("") || departureTime.getText().equals("")
					|| arrivalTime.getText().equals("") || price.getText().equals("")) {
				String error_message = "Please input all the neccessary fields.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int depart = Integer.valueOf(departureTime.getText());
			int arrive = Integer.valueOf(arrivalTime.getText());
			int pricing = Integer.valueOf(price.getText());

			for (Route r : data1.get_routes()) {
				if (r.getStartLocation().equals(start) && r.getEndLocation().equals(end) && r.getArrivalTime() == arrive
						&& r.getDepartureTime() == depart) {
					repeat = true;
				}
			}

			if (repeat) {
				String error_message = "This route already exists, same start-end location and arrival-departure time.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (depart < 0 || depart > 2359 || arrive < 0 || arrive > 2359) {
				String error_message = "Invalid time code.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (pricing < 0) {
				String error_message = "Price cannot be negative.";
				JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Route r = new Route();
				r.setStartLocation(start);
				r.setEndLocation(end);
				r.setArrivalTime(arrive);
				r.setDepartureTime(depart);
				r.setPrice(pricing);
				data1.get_routes().add(r);
				String success_msg = "Successfully created new route " + start + " - " + end;
				JOptionPane.showMessageDialog(null, success_msg, "Success", JOptionPane.PLAIN_MESSAGE);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				PrintStream old = System.out;
				System.setOut(ps);
				t.addRoute(r);
				System.out.flush();
				System.setOut(old);
				String[] msgArr = baos.toString().split("\n");
				String msg = msgArr[0];
				JOptionPane.showMessageDialog(null, msg, "Route Validity Check", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void view_train_status(Train t) {
		JOptionPane.showMessageDialog(null, "Train " + t.getTrainCode() + " is \"" + t.getStatus() + "\"",
				"Train Status", JOptionPane.PLAIN_MESSAGE);
	}
	// Manager page stuff ends

	// Utility functions
	private Passenger findPassenger(String name) {
		for (Passenger p : passenger_list) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	private Train findTrain(Manager m, int trainCode) {
		for (Train t : m.getTrainsManaged()) {
			if (t.getTrainCode() == trainCode) {
				return t;
			}
		}
		return null;
	}

	private Route findRoute(Manager m, String dep, String des) {
		for (Train t : m.getTrainsManaged()) {
			for (Route r : t.getRouteList()) {
				if (r.getStartLocation().equalsIgnoreCase(dep) && r.getEndLocation().equalsIgnoreCase(des))
					;
				return r;
			}
		}
		return null;
	}

	private void redirectToGUI() {
		JFrame window = new JFrame("Viewing Available Trains");
		window.setSize(700, 800);

		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setBackground(new Color(173, 216, 230));

		JTextArea outputTextArea = new JTextArea();

		PrintStream printStream = new PrintStream(new CustomOutputStream(outputTextArea));
		System.setOut(printStream);

		outputTextArea.setEditable(false);
		outputTextArea.setBackground(new Color(173, 216, 230));

		window.setLayout(new BorderLayout());
		window.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
		window.setSize(700, 800);
		window.setVisible(true);
	}

	class CustomOutputStream extends OutputStream {
		private JTextArea textArea;

		public CustomOutputStream(JTextArea textArea) {
			this.textArea = textArea;
		}

		@Override
		public void write(int b) throws IOException {
			textArea.append(String.valueOf((char) b));
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}
}
