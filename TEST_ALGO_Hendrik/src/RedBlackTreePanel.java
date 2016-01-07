import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class RedBlackTreePanel extends JPanel {
    {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        GridBagLayout gblFileChooser = new GridBagLayout();
        JPanel fileChooser = new JPanel(gblFileChooser);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbl.setConstraints(fileChooser, gbc);
        add(fileChooser);

        JTextField pathTextField = new JTextField("Dateipfad");
        pathTextField.setEnabled(false);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.95;
        gbc.insets = new Insets(1, 0, 0, 1);
        gbc.ipady = 1;
        gblFileChooser.setConstraints(pathTextField, gbc);
        fileChooser.add(pathTextField);

        JButton openFileDialogButton = new JButton("auswählen");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.05;
        gblFileChooser.setConstraints(openFileDialogButton, gbc);
        fileChooser.add(openFileDialogButton);

        JFileChooser fileDialog = new JFileChooser();
        openFileDialogButton.addActionListener(e -> {
            fileDialog.setFileFilter(new FileNameExtensionFilter("uDraw files", "udg"));
            fileDialog.showSaveDialog(this);
            pathTextField.setText(fileDialog.getSelectedFile().getPath());
        });

        GridLayout gblMainPanel = new GridLayout(1, 2);
        JPanel mainPanel = new JPanel(gblMainPanel);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbl.setConstraints(mainPanel, gbc);
        add(mainPanel);

        GridBagLayout gblControlPanel = new GridBagLayout();
        JPanel controlsPanel = new JPanel(gblControlPanel);
        controlsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        mainPanel.add(controlsPanel);

        JPanel addPanel = new JPanel(new GridLayout(1, 2));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gblControlPanel.setConstraints(addPanel, gbc);
        controlsPanel.add(addPanel);

        JTextField addTextField = new JTextField();
        addPanel.add(addTextField);

        JButton addButton = new JButton("hinzufügen");
        addPanel.add(addButton);

        JCheckBox saveAsTDCheckbox = new JCheckBox("als Top-Down 234 speichern");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gblControlPanel.setConstraints(saveAsTDCheckbox, gbc);
        controlsPanel.add(saveAsTDCheckbox);

        GridBagLayout gblListPanel = new GridBagLayout();
        JPanel listPanel = new JPanel(gblListPanel);
        mainPanel.add(listPanel);


        DefaultListModel listModel = new DefaultListModel();

        JList list = new JList(listModel);

        JScrollPane listScrollPane = new JScrollPane(list);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.95;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 2, 1, 2);
        gblListPanel.setConstraints(listScrollPane, gbc);
        listPanel.add(listScrollPane);

        JButton removeButton = new JButton("-");
        removeButton.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weightx = 0.05;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        gblListPanel.setConstraints(removeButton, gbc);
        listPanel.add(removeButton);

        /**
         * LISTENER
         */

        addButton.addActionListener(e -> {
            listModel.addElement(addTextField.getText());
        });

        removeButton.addActionListener(e -> {
            while(list.getSelectedIndex() != -1)
                listModel.removeElementAt(list.getSelectedIndex());
        });

        list.addListSelectionListener(e -> {
            if(list.getSelectedIndex() == -1)
                removeButton.setEnabled(false);
            else
                removeButton.setEnabled(true);
        });

    }
}