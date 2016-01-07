import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    {
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Algorithmen und Datenstrukturen - Assignment 3");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        JTabbedPane tabbedPane = new JTabbedPane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(tabbedPane, gbc);
        add(tabbedPane);

        RedBlackTreePanel redBlackTreePanel = new RedBlackTreePanel();
        tabbedPane.add("Rot-Schwarz-Baum", redBlackTreePanel);

        PatriciaTriePanel patriciaTriePanel = new PatriciaTriePanel();
        tabbedPane.add("PATRICIA-Trie", patriciaTriePanel);

        RoBDDPanel roBDDPanel = new RoBDDPanel();
        tabbedPane.add("RoBDD", roBDDPanel);

        tabbedPane.add("Info", new JPanel());

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbl.setConstraints(southPanel, gbc);

        add(southPanel);

        JCheckBox openUDrawCheckbox = new JCheckBox("danach in uDraw öffnen");
        southPanel.add(openUDrawCheckbox);

        JButton saveButton = new JButton("speichern");
        southPanel.add(saveButton);

        saveButton.addActionListener(e -> {

        });

        setVisible(true);
    }
}