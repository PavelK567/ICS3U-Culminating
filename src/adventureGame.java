import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class adventureGame {

    JTextArea room;
    JTextArea log;
    JTextArea items;
    JTextField input;
    JButton send;

    
    public void play(){
        
        JFrame gameWindow = new JFrame("Adventure Game");

        JTextArea room = new JTextArea("Area :");
        JTextArea log = new JTextArea(10, 20);
        JTextArea items = new JTextArea("Items :");
        JScrollPane logPane = new JScrollPane();

        JTextField input = new JTextField(1);
        JButton send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                log.append(input.getText()+"\n"); 
                input.setText(""); 
            }  
        });

        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        room.setEditable(false);
        room.setColumns(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        
        gameWindow.add(room,gbc);

        log.setRows(5);
        log.setColumns(20);
        log.setEditable(false);
        log.setWrapStyleWord(true);

        logPane.createVerticalScrollBar();
        
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gameWindow.add(logPane,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gameWindow.add(input,gbc);

        items.setRows(3);
        items.setColumns(5);
        items.setEditable(false);

        gbc.gridx = 4;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gameWindow.add(items,gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gameWindow.add(send,gbc);

        gameWindow.setSize(300,250);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameWindow.setVisible(true);

    }


}
