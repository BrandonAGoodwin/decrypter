package com.bxg796.main;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class DecrypterTextFrame extends JFrame implements Runnable{

    private static final String FRAME_TITLE = "Decrypted Text";

    JPanel panel;

    JTextArea textArea;

    JScrollPane scrollPane;

    public DecrypterTextFrame(String text){

        Thread thread = new Thread(this);

        thread.start();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
		setResizable(false);
		setTitle(FRAME_TITLE);
		setVisible(true);

        panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500,500));

        textArea = new JTextArea(text);

        textArea.setLineWrap(true);
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane);

        add(panel);

        pack();

    }

    @Override
    public void run(){}

}
