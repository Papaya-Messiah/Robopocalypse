/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/30/2024
 * 
 * Hold a list of possible commands the player can execute
 * This is the invoker part of the command pattern
 */

import java.util.HashMap;

public class PlayerCommands {
    public HashMap<String, Command> list = new HashMap<>();

    public PlayerCommands() {
        list.put("help", new HelpCommand());
    }

    interface Command {
        public void execute();
    }

    public class HelpCommand implements Command {
        @Override
        public void execute() {
            UI.getInstance().setMsg("Help command output.");
        }
    }

    public class InspectCommand implements Command {
        @Override
        public void execute() {

        }
    }

    public class AttackCommand implements Command {
        @Override
        public void execute() {

        }
    }

    public class PickupCommand implements Command {
        @Override
        public void execute() {

        }
    }

    public class EquipCommand implements Command {
        @Override
        public void execute() {

        }
    }
}
