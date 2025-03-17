package mineField;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    public Model makeModel() { return new Minefield(); }

    public View makeView(Model m) { return new MinefieldView((Minefield) m); }

    public String[] getEditCommands() {
        return new String[]{"Move North", "Move South", "Move East", "Move West", "Move North-East", "Move North-West", "Move South-East", "Move South-West"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "Move North": return new MoveCommand(model, Minefield.Heading.NORTH);
            case "Move South":
                System.out.println("awa");
                return new MoveCommand(model, Minefield.Heading.SOUTH);
            case "Move East": return new MoveCommand(model, Minefield.Heading.EAST);
            case "Move West": return new MoveCommand(model, Minefield.Heading.WEST);
            case "Move North-East": return new MoveCommand(model, Minefield.Heading.NORTHEAST);
            case "Move North-West": return new MoveCommand(model, Minefield.Heading.NORTHWEST);
            case "Move South-East": return new MoveCommand(model, Minefield.Heading.SOUTHEAST);
            case "Move South-West": return new MoveCommand(model, Minefield.Heading.SOUTHWEST);
            default: return null;
        }
    }

    public String getTitle() { return "Mine Field"; }

    public String[] getHelp() {
        return new String[]{"Navigate through the minefield.", "Use logic to avoid mines."};
    }

    public String about() {
        return "Mine Field version 1.0. Created using MVC framework.";
    }
}

