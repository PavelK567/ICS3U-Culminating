import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adventureGame {

    // Initialize all the variables used in the game
    String[] commandList = {"help","use"};
    String[] commandDesc = {"help [command]\nGives information on how to use a command","use [item1] [item2]\nUses an item on object or another item"};

    
    public void play(){
        
        // Start Initialize UI
        JFrame gameWindow = new JFrame("Adventure Game");

        // Initialize UI Elements
        // Output Elements
        JTextArea log = new JTextArea(10, 20);

        JTextArea roomPanel = new JTextArea("Area :");
        JTextArea itemsPanel = new JTextArea("Items :");
        JScrollPane logPane = new JScrollPane();


        // Input Elements
        JTextField input = new JTextField(1);
        JButton send = new JButton("Send");

        // Set layout of game window
        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        // Area display Set up 
        roomPanel.setEditable(false);
        roomPanel.setColumns(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        
        gameWindow.add(roomPanel,gbc);

        // Log Set up 
        log.setRows(5);
        log.setColumns(20);
        log.setEditable(false);
        log.setWrapStyleWord(true);

        logPane.createVerticalScrollBar();
        
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gameWindow.add(log,gbc);

        // Text entry area Set up 
        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gameWindow.add(input,gbc);

        // Items display Set up 
        itemsPanel.setRows(3);
        itemsPanel.setColumns(5);
        itemsPanel.setEditable(false);

        gbc.gridx = 4;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gameWindow.add(itemsPanel,gbc);

        
        // Send button Set up
        gbc.gridx = 4;
        gbc.gridy = 4;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        // Set Action for button
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                log.setText(toLog(log.getText(),runCommand(input.getText()))); 
                input.setText(""); 
            }  
        });

        gameWindow.add(send,gbc);

        // Finish setting up window
        gameWindow.setSize(300,250);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameWindow.setVisible(true);

    }

    // Method to recieve and process a command
    public String runCommand(String input){

        // Get the first word of the input (the command word, i.e. "Use")
        String cmd = input.split(" ")[0];

        String item1 = null;
        String item2 = null;
        // Second word of the input (the action item, i.e. "apple")
        if (input.split(" ").length > 1){
            item1 = input.split(" ")[1];
            if (input.split(" ").length > 2){
                item2 = input.split(" ")[1];
            }
        }
            // OPTIONAL : Third word of the input (the acted item, i.e. "fridge"))


        String output = "> " + input + "\n";
        boolean invalid = true;
        
        // Check if command is invalid
        for (int i = 0; i < commandList.length; i ++){
            if (commandList[i].equals(cmd)){
                invalid = false;
            }
        }

        // Print "Command "x" not found" if command is invalid
        if (invalid){
            output += "Command \"" + cmd + "\" not found";
        }

        // "help" command
        if (cmd.equals("help")){
            if (item1 == null){
                String commands = ""; 
                for (int i = 0; i < commandList.length; i ++){
                    commands = commands + commandList[i] + ", ";
                }
                output += ("Possible Commands : " + commands);
            }
            else{
                int helpCommand = -1;
                for (int i = 0; i < commandList.length; i ++){
                    if (commandList[i].equals(item1)){
                        helpCommand = i;
                        break;
                    }
                }
                if(helpCommand == -1){
                    output += "I can't help with \"" + item1 + "\"";
                }
                else{
                    output += commandDesc[helpCommand];
                }
            }
        }
        return output;
    }

    // Method for controlling text in the log
    public String toLog(String logText, String text){
        return (logText + "\n" + text);
    }


}
