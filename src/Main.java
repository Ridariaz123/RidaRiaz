import Controller.Controller;
import view.CompetitionGUI;
import model.CompetitorList;
import model.User;

import javax.swing.*;
import java.awt.*;


public class Main {
    private JFrame selectionFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().showSelectionPanel());
    }

    private void showSelectionPanel() {
        selectionFrame = new JFrame("Role Selection");
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridLayout(1, 2));

        JButton staffButton = new JButton("Staff");
        staffButton.setBackground(Color.WHITE);
        JButton competitorButton = new JButton("Competitor");
        competitorButton.setBackground(Color.LIGHT_GRAY);

        staffButton.addActionListener(e -> openCompetitionGUI("staff"));
        competitorButton.addActionListener(e -> openCompetitionGUI("competitor"));

        selectionPanel.add(staffButton);
        selectionPanel.add(competitorButton);

        selectionFrame.add(selectionPanel);
        selectionFrame.setSize(450, 450);
        selectionFrame.setLocationRelativeTo(null);
        selectionFrame.setVisible(true);
    }

    private void openCompetitionGUI(String role) {
        selectionFrame.dispose(); // Close the selection frame

        // Create the Model
        CompetitorList model = new CompetitorList();

        // Read competitors from CSV (optional)
        String localDir = System.getProperty("user.dir");        
        model.readCompetitorsFromCSV(localDir + "\\src\\competitors.csv");

        // Create the User based on the selected role
        User user = new User(role);

        // Create the View for staff and competitor
        CompetitionGUI competitionView = new CompetitionGUI(model, user);

        // Create the Controller for staff and competitor
        Controller competitionController = new Controller(competitionView, model);

        // Set up the GUI for staff and competitor
        competitionView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        competitionView.pack();
        competitionView.setLocationRelativeTo(null);
        competitionView.setVisible(true);
    }
}
