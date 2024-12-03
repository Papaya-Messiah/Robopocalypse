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
    private final int Q = 81;
    private final int W = 87;
    private final int A = 65;
    private final int S = 83;
    private final int D = 68;
    private final int E = 69;
    private final int F = 70;
    private final int G = 71;
    private final int C = 87;
    private final int X = 88;
    private final int Z = 90;
    private final int R = 82;
    private final int H = 72;

    private HashSet<Integer> moveKeys = new HashSet<>();
    private HashSet<Integer> commandKeys = new HashSet<>();

    private PlayerCommands commands = new PlayerCommands();

    public Controls() {
        moveKeys.addAll(Arrays.asList(W, A, S, D));
        commandKeys.addAll(Arrays.asList(H, E, F, G, C, X, Z, R));
        UI.getInstance().setControls("Move:\t\tWASD\nInspect:\tE\nHelp:\t\tH\nQuit:\t\tQ");
    }

    @Override
    public void keyPressed(KeyEvent e) {
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

    public void doCommand(int keyCode) {
        switch (keyCode) {
            case H:
                commands.list.get("help").execute();
                break;
            case E:
                commands.list.get("inspect").execute();
                break;
        }
    }

    public void tryMovement(int keyCode) {
        Game.getInstance().enemy.move();
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
            if (World.getInstance().getCell(posX, posY-1).getType() != Cell.CellType.WALL) {
                Player.getInstance().setCoords(posX, posY-1);
            }
        } else if (keyCode == A) {
            if (World.getInstance().getCell(posX-1, posY).getType() != Cell.CellType.WALL) {
                Player.getInstance().setCoords(posX-1, posY);
            }
        } else if (keyCode == S) {
            if (World.getInstance().getCell(posX, posY+1).getType() != Cell.CellType.WALL) {
                Player.getInstance().setCoords(posX, posY+1);
            }
        } else if (keyCode == D) {
            if (World.getInstance().getCell(posX+1, posY).getType() != Cell.CellType.WALL) {
                Player.getInstance().setCoords(posX+1, posY);
            }
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
