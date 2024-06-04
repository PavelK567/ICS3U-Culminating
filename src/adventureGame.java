import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;


public class adventureGame {

    JTextArea room;
    JTextArea log;
    JTextArea items;
    JTextField input;

    final static String ENTER_ACTION = "update-log";

    class updateLog extends AbstractAction {
        public void actionPerformed(ActionEvent ev) {
            log.setText(input.getText());
        }
    }

    public void play(){
        initComponents();

        InputMap im = input.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = input.getActionMap();
        im.put(KeyStroke.getKeyStroke("ESCAPE"), ENTER_ACTION);
        am.put(ENTER_ACTION, new updateLog());
        
    };
    
    public void initComponents(){
        
        JFrame gameWindow = new JFrame("Adventure Game");

        JTextArea room = new JTextArea("this is what room you're in");
        JTextArea log = new JTextArea("here's all the things that happened");
        JTextArea items = new JTextArea("things you hold");

        JTextField input = new JTextField(1);

        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        room.setEditable(false);
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        
        gameWindow.add(room,gbc);

        log.setRows(10);
        log.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gameWindow.add(log,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gameWindow.add(input,gbc);

        items.setRows(3);
        items.setEditable(false);

        gbc.gridx = 4;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gameWindow.add(items,gbc);

        gameWindow.setSize(300,250);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameWindow.setVisible(true);

    }


}
