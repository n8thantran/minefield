package minefield;

public interface AppFactory {
    Model makeModel();
    View makeView();
    String getTitle();
    String getHelp();
    String about();
    Command[] getEditCommands();
    Command makeEditCommand(String command);
}
