/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/30/2024
 * 
 * Hold a list of possible commands the player can execute
 * This is the invoker part of the command pattern
 */

import java.util.HashMap;

public class PlayerCommands {
    //command container
    public HashMap<String, Command> list = new HashMap<>();

    //constructor
    public PlayerCommands() {
        list.put("help", new HelpCommand());
        list.put("inspect", new InspectCommand());
        list.put("equip", new EquipCommand());
        list.put("attack", new AttackCommand());
    }

    interface Command {
        public void execute();
    }

    public class HelpCommand implements Command {
        @Override
        public void execute() {
            UI.getInstance().setMsg("You are a robot in a post apocalyptic world.\nCollect items to get stronger and defeat the enemies.");
        }
    }

    public class InspectCommand implements Command {
        @Override
        public void execute() {
            Player.getInstance().inspect();
        }
    }

    public class AttackCommand implements Command {
        @Override
        public void execute() {
            Player.getInstance().attack();
        }
    }

    public class EquipCommand implements Command {
        @Override
        public void execute() {
            Player.getInstance().equip();
        }
    }
}
