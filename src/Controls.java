/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Controls the actions a player can take by defining the keybinds available
 * Is the client in the command pattern
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;

public class Controls implements KeyListener {
    //hardcoding the keyCodes to what button is pressed
    private final int Q = 81;
    private final int W = 87;
    private final int A = 65;
    private final int S = 83;
    private final int D = 68;
    private final int E = 69;
    private final int F = 70;
    private final int X = 88;
    private final int H = 72;

    private HashSet<Integer> moveKeys = new HashSet<>();
    private HashSet<Integer> commandKeys = new HashSet<>();

    private PlayerCommands commands = new PlayerCommands();

    public Controls() {
        //add the movekeys to the HashSet
        moveKeys.addAll(Arrays.asList(W, A, S, D));

        //add the commandkeys to the HashSet
        commandKeys.addAll(Arrays.asList(H, E, F, X));
        UI.getInstance().setControls("Move:\t\tWASD\nAttack:\t\tX\nInspect:\tF\nEquip:\t\tE\nHelp:\t\tH\nQuit:\t\tQ");
    }

    //happens when key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        //get keyCode
        int keyCode = e.getKeyCode();
        System.out.println("Detected key:" + keyCode);
        //quit when q is pressed
        if (keyCode == Q) {
            Game.getInstance().quit();
        }
        //movement keys
        if (moveKeys.contains(keyCode)) {
            tryMovement(keyCode);
        }
        //action keybinds
        else if (commandKeys.contains(keyCode)) {
            doCommand(keyCode);
        }
        else {
            System.out.println("Pressed key does not do anything.");
        }
    }

    //execute different commands depending on the key pressed
    public void doCommand(int keyCode) {
        switch (keyCode) {
            case H:
                commands.list.get("help").execute();
                break;
            case F:
                commands.list.get("inspect").execute();
                break;
            case E:
                commands.list.get("equip").execute();
                break;
            case X:
                commands.list.get("attack").execute();
                break;
        }
    }

    //attempt to move, the player cannot move into walls or enemies
    public void tryMovement(int keyCode) {
        //Game.getInstance().enemy.move();
        int posX = Player.getInstance().getX();
        int posY = Player.getInstance().getY();
        //updating the direction the player is facing
        if (keyCode == W) {
            Player.getInstance().setFacing(Direction.NORTH);
        } else if (keyCode == A) {
            Player.getInstance().setFacing(Direction.WEST);
        } else if (keyCode == S) {
            Player.getInstance().setFacing(Direction.SOUTH);
        } else if (keyCode == D) {
            Player.getInstance().setFacing(Direction.EAST);
        }
        //checking that the player is not about to move into an occupied cell
        if (keyCode == W) {
            if (World.getInstance().getCell(posX, posY-1).getType() != Cell.CellType.WALL && World.getInstance().getCell(posX, posY-1).getType() != Cell.CellType.ENEMY) {
                Player.getInstance().setCoords(posX, posY-1);
            }
        } else if (keyCode == A) {
            if (World.getInstance().getCell(posX-1, posY).getType() != Cell.CellType.WALL && World.getInstance().getCell(posX-1, posY).getType() != Cell.CellType.ENEMY) {
                Player.getInstance().setCoords(posX-1, posY);
            }
        } else if (keyCode == S) {
            if (World.getInstance().getCell(posX, posY+1).getType() != Cell.CellType.WALL && World.getInstance().getCell(posX, posY+1).getType() != Cell.CellType.ENEMY) {
                Player.getInstance().setCoords(posX, posY+1);
            }
        } else if (keyCode == D) {
            if (World.getInstance().getCell(posX+1, posY).getType() != Cell.CellType.WALL && World.getInstance().getCell(posX+1, posY).getType() != Cell.CellType.ENEMY) {
                Player.getInstance().setCoords(posX+1, posY);
            }
        }
        for (Enemy e : Game.getInstance().enemies) {
            e.move();
        }
        System.out.println("New player position: " + Player.getInstance().getX() + ", " + Player.getInstance().getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }
    
    
}
