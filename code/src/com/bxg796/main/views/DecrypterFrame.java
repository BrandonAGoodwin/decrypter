package com.bxg796.main.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// The JFrame for the Decrypter
public class DecrypterFrame extends JFrame implements ActionListener{

    private static final String FRAME_TITLE = "Decrypter";

    private static final String FILE_MENU_TEXT = "File";

    private static final String LOAD_FREQ_MENU_ITEM_TEXT = "Load Frequencies File";

    private static final String LOAD_CIPH_MENU_ITEM_TEXT = "Load Cipher File";

    private static final String OPTIONS_MENU_TEXT = "Options";

    private static final String DECRYP_TYPE_1_TEXT = "Decryption Type 1";

    private static final String DECRYP_TYPE_2_TEXT = "Decryption Type 2";

    private static final String DECRYPT_BUTTON_TEXT = "Decrypt";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    // Has the menu items to load in files
    private JMenuBar menuBar;

    // Where messages to the user will be displayed
    private JPanel messagePanel;

    // The text area where the messages will be printed
    private JTextArea messageLog;

    // Allows the log to be scrollable
    private JScrollPane scrollPane;

    // Where the buttons will be placed
    private JPanel buttonPanel;

    // Stores the view that creates the frame the view's methods can be called
    private DecrypterView view;

    public DecrypterFrame(DecrypterView view){

        // Set up the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(FRAME_TITLE);
		setVisible(true);

        this.view = view;

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Menu items to load in the frequencies file and the cipher file
        JMenuItem loadFrequenciesItem = new JMenuItem(LOAD_FREQ_MENU_ITEM_TEXT);
        loadFrequenciesItem.setActionCommand("LoadFreq");
        loadFrequenciesItem.addActionListener(this);
        fileMenu.add(loadFrequenciesItem);

        JMenuItem loadCipherItem = new JMenuItem(LOAD_CIPH_MENU_ITEM_TEXT);
        loadCipherItem.setActionCommand("LoadCiph");
        loadCipherItem.addActionListener(this);
        fileMenu.add(loadCipherItem);

        // Menu items to set the decryption type to use
        JMenu optionsMenu = new JMenu(OPTIONS_MENU_TEXT);
        menuBar.add(optionsMenu);

        // Radio button menu items to set which decryption type is to be applied
        ButtonGroup optionsGroup = new ButtonGroup();
        JRadioButtonMenuItem decryptType1Item = new JRadioButtonMenuItem(DECRYP_TYPE_1_TEXT);
        // Make type 1 decryption the default
        decryptType1Item.setSelected(true);
        decryptType1Item.setActionCommand("D-Type 1");
        decryptType1Item.addActionListener(this);
        optionsGroup.add(decryptType1Item);
        optionsMenu.add(decryptType1Item);

        JRadioButtonMenuItem decryptType2Item = new JRadioButtonMenuItem(DECRYP_TYPE_2_TEXT);
        decryptType2Item.setActionCommand("D-Type 2");
        decryptType2Item.addActionListener(this);
        optionsGroup.add(decryptType2Item);
        optionsMenu.add(decryptType2Item);

        // Add the menu bar to the frame
        setJMenuBar(menuBar);

        // The message panel to store the JTextArea and JScrollPane
        messagePanel = new JPanel(new FlowLayout());

        messagePanel.setPreferredSize(new Dimension(500,500));

        messageLog = new JTextArea(29,44);

        // Setting up the text area
        messageLog.setLineWrap(true);
        messageLog.setEditable(false);

        // Making the text area scrollable
        scrollPane = new JScrollPane(messageLog);

        // Add vertical and horizontal scroll bars
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Adding the scroll pane / text area to the message panel
        messagePanel.add(scrollPane);

        // Adding the message panel to the frame
        add(messagePanel, BorderLayout.CENTER);

        // The panel to contain the decrypt button
        buttonPanel = new JPanel(new FlowLayout());

        // The button to decrypt loaded files
        JButton decryptButton = new JButton(DECRYPT_BUTTON_TEXT);
        decryptButton.setActionCommand("Decrypt");
        decryptButton.addActionListener(this);

        // Add the decrypt button to the button panel
        buttonPanel.add(decryptButton);

        // Add the panel which contains the decrypt button to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }


    public void addMessage(String message){
        // Get the current time
        Calendar cal = Calendar.getInstance();

        // Print a message to the messageLog preceded by the time the message was printed
        messageLog.append("[" + simpleDateFormat.format(cal.getTime()) + "] : " + message + "\n");
    }

    // Clear the messageLog
    public void clearMessage(){
        messageLog.setText("");
    }

    // This method calls the relevant view methods depending on what events occur
    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getActionCommand().equals("LoadFreq")){
            view.loadFrequencies();
        }
        else if(e.getActionCommand().equals("LoadCiph")){
            view.loadCipher();
        }
        else if(e.getActionCommand().equals("D-Type 1")){
            view.setDecryptionType1();
        }
        else if(e.getActionCommand().equals("D-Type 2")){
            view.setDecryptionType2();
        }
        else if(e.getActionCommand().equals("Decrypt")){
            view.decrypt();
        }

    }

}
