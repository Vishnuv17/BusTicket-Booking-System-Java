import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BusTicketBooking extends JFrame implements ActionListener {
    private JTextField nameField, ageField, ticketIdField;
    private JComboBox<String> genderBox, fromBox, toBox, seatBox;
    private String ticketDetail;
    private String ticketId;
    private Random random = new Random();
    private JButton bookButton, viewButton;

    public BusTicketBooking() {
        setTitle("Bus Ticket Booking");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        inputPanel.add(genderBox);

        inputPanel.add(new JLabel("From:"));
        fromBox = new JComboBox<>(new String[]{"City A", "City B", "City C"});
        inputPanel.add(fromBox);

        inputPanel.add(new JLabel("To:"));
        toBox = new JComboBox<>(new String[]{"City X", "City Y", "City Z"});
        inputPanel.add(toBox);

        inputPanel.add(new JLabel("Seat Type:"));
        seatBox = new JComboBox<>(new String[]{"Regular", "Business", "First Class"});
        inputPanel.add(seatBox);

        bookButton = new JButton("Book Ticket");
        inputPanel.add(bookButton);

        add(inputPanel, BorderLayout.NORTH);

        JPanel ticketPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        ticketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ticketPanel.add(new JLabel("Enter Ticket ID to View:"));
        ticketIdField = new JTextField();
        ticketPanel.add(ticketIdField);

        viewButton = new JButton("View Ticket");
        ticketPanel.add(viewButton);

        add(ticketPanel, BorderLayout.CENTER);

        bookButton.addActionListener(this);
        viewButton.addActionListener(this);

        setVisible(true); // Make the frame visible after adding all components
    }

    private void handleBookButton() {
        String name = nameField.getText();
        String ageStr = ageField.getText();
        String gender = (String) genderBox.getSelectedItem();
        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();
        String seat = (String) seatBox.getSelectedItem();

        if (name.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Age fields cannot be empty.");
            return;
        }

        double price = calculatePrice(seat);
        String tId = String.valueOf( random.nextInt(1000,9000));

        String tDetail = String.format(
                "Booking Details:\nTicket ID: %s\nName: %s\nAge: %s\nGender: %s\nFrom: %s\nTo: %s\nSeat Type: %s\nTotal Price: $%.2f\n",
                tId, name, ageStr, gender, from, to, seat, price
        );
        JOptionPane.showMessageDialog(this, "Ticket booked successfully!\nTicket ID: " + tId);
        ticketId = tId;
        ticketDetail = tDetail;
    }

    private void handleViewButton() {
        String ticketIds = ticketIdField.getText();
        if (ticketId.equals(ticketIds)) {
            JFrame ticketFrame = new JFrame("Ticket Details");
            ticketFrame.setSize(400, 300);
            JTextArea ticketArea = new JTextArea(ticketDetail);
            ticketArea.setEditable(false);
            ticketFrame.add(new JScrollPane(ticketArea));
            ticketFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Ticket ID");
        }
    }

    private double calculatePrice(String seatType) {
        switch (seatType) {
            case "Regular":
                return 50.0;
            case "Business":
                return 75.0;
            case "First Class":
                return 100.0;
            default:
                return 0.0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            handleBookButton();
        } else if (e.getSource() == viewButton) {
            handleViewButton();
        }
    }

    public static void main(String[] args) {
        BusTicketBooking b=new BusTicketBooking();
    }
}
