/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Controls the actions a player can take by defining the keybinds available
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {
    private final int Q = 81;
    private final int W = 87;
    private final int A = 65;
    private final int S = 83;
    private final int D = 68;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Detected key:" + keyCode);
        //quit when q is pressed
        if (keyCode == Q) {
            System.exit(0);
        }
        if (keyCode == W || keyCode == A || keyCode == S || keyCode == D) {
            tryMovement(keyCode);
        }
    }

    public void tryMovement(int keyCode) {
        int posX = Player.getInstance().getX();
        int posY = Player.getInstance().getY();
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
