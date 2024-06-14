import javax.swing.*;
import java.awt.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class adventureGame {

    // Initialize all the variables used in the game
    public room kitchen = new room();
    public room livingRoom = new room();

    public room currentRoom;
    public ArrayList<room> roomList = new ArrayList<room>();

    public item apple = new item();
    public item table = new item();

    public ArrayList<item> inventory = new ArrayList<item>();

    Font gameFont = new Font("Courier", Font.PLAIN, 18);
    Font buttonFont = new Font("Courier", Font.BOLD, 18);

    String[] commandList = {
    "help",
    "use",
    "goto",
    "take"};

    String[] commandDesc = {
    "help [command]\nGives information on how to use a command",
    "use [item1] [item2]\nUses an item on object or another item",
    "goto [room]\nMove to a connected room",
    "take [item]\nTakes an item into your inventory"};

    
    public void play(){

        // Initialize rooms
        createRooms();
        currentRoom = kitchen;
        createItems();
        
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
        roomPanel.setColumns(30);
        roomPanel.setRows(4);
        roomPanel.setFont(gameFont);
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;

        
        gameWindow.add(roomPanel,gbc);

        // Log Set up 
        log.setRows(10);
        log.setColumns(30);
        log.setEditable(false);
        log.setWrapStyleWord(true);
        log.setLineWrap(true);
        log.setFont(gameFont);

        // Move log into a scrollable panel
        logPane.createVerticalScrollBar();
        logPane.setViewportView(log);
        
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gameWindow.add(logPane,gbc);

        // Text entry area Set up 
        input.setFont(gameFont);

        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gameWindow.add(input,gbc);

        // Items display Set up 
        itemsPanel.setRows(3);
        itemsPanel.setColumns(5);
        itemsPanel.setEditable(false);
        itemsPanel.setFont(gameFont);

        gbc.gridx = 4;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gameWindow.add(itemsPanel,gbc);

        
        // Send button Set up
        send.setFont(buttonFont);

        gbc.gridx = 4;
        gbc.gridy = 4;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        // Set Action for button
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                // Writes command info into log, clears text entry and updates ui elements
                log.append(runCommand(input.getText()));
                input.setText(""); 
                roomPanel.setText(updateRoomDisplay());
                itemsPanel.setText(updateInventory());
            }  
        });

        //update the room UI
        roomPanel.setText(updateRoomDisplay());
        gameWindow.add(send,gbc);
        
        // Finish setting up window
        gameWindow.setSize(550,500);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameWindow.setVisible(true);

    }

    // Method to recieve and process a command
    String runCommand(String input){
        
        // Get the first word of the input (the command word, i.e. "Use")
        String cmd = input.split(" ")[0];
        
        String item1 = null;
        String item2 = null;

        String output = "> " + input + "\n";

        // Check for second word of the input (the action item, i.e. "apple")
        if (input.split(" ").length > 1){
            item1 = input.split(" ")[1];
            
            // Check for third word of the input (the acted on item, i.e. "fridge"))
            if (input.split(" ").length > 2){
                item2 = input.split(" ")[1];
            }
        }

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
            // Check if user entered "help" with no command, returns list of commands
            if (item1 == null){
                String commands = ""; 
                // Goes through the list of commands and formats them
                for (int i = 0; i < commandList.length; i ++){
                    commands = commands + commandList[i] + ", ";
                }
                output += ("Possible Commands : " + commands);
            }
            // Check if user entered "help [command]", returns description of command (if exists)
            else{
                int helpCommand = -1;
                for (int i = 0; i < commandList.length; i ++){
                    if (commandList[i].equals(item1)){
                        helpCommand = i;
                        break;
                    }
                }
                // helpCommand only equals -1 when the command the user entered is invalid
                if(helpCommand == -1){
                    output += "I can't help with \"" + item1 + "\"";
                }
                else{
                    output += commandDesc[helpCommand];
                }
            }
        }

        // "goto" command
        if(cmd.equals("goto")){
            room goingTo = new room();

            // Goes through the list of rooms and find the room the user is asking to go to
            for(int i = 0; i < roomList.size(); i++){
                if(item1.equals(roomList.get(i).name)){
                    goingTo = roomList.get(i);
                    break;
                }
            }
            // Check if the room the user is in connects to the one they're going to
            if(currentRoom.connects.contains(goingTo)){
                currentRoom = goingTo;
                output += "You go to the \"" + goingTo.name + "\"";
            }
            else {
                output += "Can't get to \"" + item1 + "\"";
            }
        }

        // "use" command
        if(cmd.equals("use")){
            item using = new item();

            // Goes through the list of your items and finds the one you're trying to use
            for(int i = 0; i < inventory.size(); i++){
                if(item1.equals(inventory.get(i).name)){
                    using = inventory.get(i);
                    break;
                }
            }
            
            
            if(inventory.contains(using)){
                item useOn = new item();
               // Goes through the list of items in the current room and finds the one you're using things on 
                for(int i = 0; i < currentRoom.contains.size(); i++){
                    if(item2.equals(currentRoom.contains.get(i).name)){
                        useOn = currentRoom.contains.get(i);
                        break;
                    }
                if(currentRoom.contains.contains(useOn)){
                    // Checks if the first item can be used on the second
                    if(using.useableOn.contains(useOn)){
                        output += using.usedText;
                        inventory.remove(using);
                    }
                    else{
                        output += "Can't use \"" + item1 + "\" on \"" + item2 + "\"";
                    }
                }
                else{
                    output += "Can't find \"" + item2 + "\" in the room";
                }
                }
            }
            else{
                output += "Can't find \"" + item1 + "\" in your inventory";
            }
        }

        //"take" command
        if(cmd.equals("take")){
        item taking = new item();

        // Goes through the list of items in the current room and finds the one you're taking 
        for(int i = 0; i < currentRoom.contains.size(); i++){
            if(item1.equals(currentRoom.contains.get(i).name)){
                taking = currentRoom.contains.get(i);
                break;
            }
        }
        if(currentRoom.contains.contains(taking))
            // check if the item is pickupable
            if(taking.pickable){
                inventory.add(taking);
                currentRoom.removeItem(taking);
                output += "Took the \"" + item1 + "\"";
            }
            else{
                output += "Couldn't pick up the \"" + item1 + "\"";
            }
        }

        // Should be "> [command]" + "[whatever info the command outputs]"
        return output;
    }

    // initializes all the rooms in the game
    void createRooms(){
        // Set name and description of all the rooms
        kitchen.setInfo("Kitchen","A small kitchen");
        livingRoom.setInfo("Living_Room","It's a living room");

        // Connect rooms to each other
        kitchen.addRoom(livingRoom);
        livingRoom.addRoom(kitchen);

        // Adds items to each room
        kitchen.addItem(apple);
        livingRoom.addItem(table);

        // Add all rooms into a list of all rooms
        roomList.add(kitchen);
        roomList.add(livingRoom);
    }

    // Returns formatted information of the current room
    String updateRoomDisplay(){
        String roomInfo = "Area : ";
        roomInfo += currentRoom.name + "\n";
        roomInfo += currentRoom.desc + "\nConnects to : ";

        // Get all the rooms that the current room connects to
        String connections = ""; 
        for(int i = 0; i < currentRoom.connects.size(); i ++){
            connections += currentRoom.connects.get(i).name + ", ";
        }

        roomInfo += connections;

        roomInfo += currentRoom.desc + "\nContains : ";

        // Get all the items in the current room
        String items = ""; 
        for(int i = 0; i < currentRoom.contains.size(); i ++){
            items += currentRoom.contains.get(i).name + ", ";
        }
        
        roomInfo += items;

        return roomInfo;
    }

    
    // initializes all the items in the game
    void createItems(){
        // Set name, description and pickablility of all items
        apple.setInfo("Apple","A red apple",true, "Put the apple on the table");
        table.setInfo("Table","It's a square table made of wood", false, "");

        // Adds usability to items
        apple.addUseable(table);
    }

    // Returns formatted information of your inventory
    String updateInventory(){
        String inventoryInfo = "Items : \n";

        // Get all the items in your inventory
        String items = ""; 
        for(int i = 0; i < inventory.size(); i ++){
            items += inventory.get(i).name + ", ";
        }

        inventoryInfo += items;

        return inventoryInfo;
    }

}