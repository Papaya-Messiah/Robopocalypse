/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Class in charge of displaying everything
 * This is one of the receivers in the command pattern
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Display extends Frame {
    private static Display instance = new Display();

    //different textareas separated by ui section
    private TextArea worldDisplay;
    private TextArea statDisplay;
    private TextArea invDisplay;
    private TextArea msgDisplay;
    private TextArea controlDisplay;
    private Font font;

    private Display() {
        setTitle("Robopocalypse");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //layout grid size
        int guiGridSize = 4;

        //initialize font
        font = new Font("Monospaced", Font.PLAIN, 12);
        
        /*
         * Creating the different display components. They are laid out in a 4x4 grid.
         * All set to not be editable or focusable, the focus should be on the window at all times to capture KeyEvents.
         * 
         * msgDisplay: used to give text messages to the user
         * worldDisplay: used to display the player and world
         * statDisplay: used to display player statistics
         * invDisplay: used to display player inventory
         * controlDisplay: used to display most commonly used control/keybind options
         */
        msgDisplay = new TextArea(UI.getInstance().getMsg(), 3, 1, TextArea.SCROLLBARS_NONE);
        msgDisplay.setFont(font);
        msgDisplay.setEditable(false);
        msgDisplay.setFocusable(false);
        msgDisplay.setBackground(Color.BLACK);
        msgDisplay.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = guiGridSize;
        c.gridx = 0;
        c.gridy = 0;
        add(msgDisplay, c);

        worldDisplay = new TextArea(World.getInstance().toString(), World.getInstance().worldSize+1, World.getInstance().worldSize*2, TextArea.SCROLLBARS_NONE);
        worldDisplay.setFont(font);
        worldDisplay.setEditable(false);
        worldDisplay.setFocusable(false);
        worldDisplay.setBackground(Color.BLACK);
        worldDisplay.setForeground(Color.WHITE);
        c.gridy += 1;
        c.gridwidth = guiGridSize-1;
        c.gridheight = guiGridSize-1;
        add(worldDisplay, c);

        statDisplay = new TextArea(UI.getInstance().getStats(), World.getInstance().worldSize/3, 30, TextArea.SCROLLBARS_NONE);
        statDisplay.setFont(font);
        statDisplay.setEditable(false);
        statDisplay.setFocusable(false);
        statDisplay.setBackground(Color.BLACK);
        statDisplay.setForeground(Color.WHITE);
        c.gridx = guiGridSize-1;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(statDisplay, c);

        invDisplay = new TextArea(UI.getInstance().getInv(), World.getInstance().worldSize/3, 30, TextArea.SCROLLBARS_NONE);
        invDisplay.setFont(font);
        invDisplay.setEditable(false);
        invDisplay.setFocusable(false);
        invDisplay.setBackground(Color.BLACK);
        invDisplay.setForeground(Color.WHITE);
        c.gridy += 1;
        add(invDisplay, c);

        controlDisplay = new TextArea(UI.getInstance().getControls(), World.getInstance().worldSize/3, 30, TextArea.SCROLLBARS_NONE);
        controlDisplay.setFont(font);
        controlDisplay.setEditable(false);
        controlDisplay.setFocusable(false);
        controlDisplay.setBackground(Color.BLACK);
        controlDisplay.setForeground(Color.WHITE);
        c.gridy += 1;
        add(controlDisplay, c);

        //size of the window
        setSize(1000, 1000);

        //focusable so we can get KeyEvents
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }
    
    public static Display getInstance() {
        return instance;
    }

    //update the worldDisplay with the most recent toString representaation
    public void updateWorldDisplay() {
        worldDisplay.setText(World.getInstance().toString());
    }

    public void updateMessageDisplay() {
        msgDisplay.setText(UI.getInstance().getMsg());
    }

    public void updateControlsDisplay() {
        controlDisplay.setText(UI.getInstance().getControls());
    }

    public void updateInventoryDisplay() {
        invDisplay.setText(UI.getInstance().getInv());
    }

    public void updateStatsDisplay() {
        statDisplay.setText(UI.getInstance().getStats());
    }
}
