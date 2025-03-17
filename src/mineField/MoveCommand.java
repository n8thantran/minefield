package mineField;

import mvc.*;

public class MoveCommand extends Command {
    private Minefield.Heading heading;

    public MoveCommand(Model model, Minefield.Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        try {
            ((Minefield) model).move(heading);
        } catch (Exception e) {
            Utilities.error(e.getMessage());
        }
    }
}

