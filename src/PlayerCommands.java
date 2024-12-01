/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/30/2024
 * 
 * Hold a list of possible commands the player can execute
 */

import java.util.ArrayList;

public class PlayerCommands {
    //reference to the command receiver
    private Player player = Player.getInstance();
    private ArrayList<Command> commands = new ArrayList<>();

    public PlayerCommands() {
        commands.add(new HelpCommand());
    }

    abstract class Command {
        public String name;
        public abstract void execute();
    }

    public class HelpCommand extends Command {
        public HelpCommand() {
            name = "Help";
        }

        @Override
        public void execute() {
            
        }
    }

    public class InspectCommand extends Command {
        public InspectCommand() {
            name = "Inspect";
        }

        @Override
        public void execute() {

        }
    }

    public class AttackCommand extends Command {
        public AttackCommand() {
            name = "Attack";
        }

        @Override
        public void execute() {

        }
    }

    public class PickupCommand extends Command {
        public PickupCommand() {
            name = "Pickup";
        }

        @Override
        public void execute() {

        }
    }

    public class EquipCommand extends Command {
        public EquipCommand() {
            name = "Equip";
        }

        @Override
        public void execute() {

        }
    }
}
