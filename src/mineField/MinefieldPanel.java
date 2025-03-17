package mineField;

import mvc.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MinefieldPanel extends AppPanel {
    private JButton north, south, east, west, northeast, southwest, northwest, southeast;

    public MinefieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(5, 3));

        north = new JButton("N");
        south = new JButton("S");
        east = new JButton("E");
        west = new JButton("W");
        northeast = new JButton("NE");
        northwest = new JButton("NW");
        southeast = new JButton("SE");
        southwest = new JButton("SW");

        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        northeast.addActionListener(this);
        northwest.addActionListener(this);
        southeast.addActionListener(this);
        southwest.addActionListener(this);

        controlPanel.add(new JLabel(""));
        controlPanel.add(new JLabel(""));
        controlPanel.add(new JLabel(""));
        controlPanel.add(northwest);
        controlPanel.add(north);
        controlPanel.add(northeast);
        controlPanel.add(west);
        controlPanel.add(new JLabel(""));
        controlPanel.add(east);
        controlPanel.add(southwest);
        controlPanel.add(south);
        controlPanel.add(southeast);
        controlPanel.add(new JLabel(""));
        controlPanel.add(new JLabel(""));
        controlPanel.add(new JLabel(""));
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String cmmd = e.getActionCommand();
            MoveCommand command = null;
            switch (cmmd) {
                case "N": command = new MoveCommand(model, Minefield.Heading.NORTH); break;
                case "S": command = new MoveCommand(model, Minefield.Heading.SOUTH); break;
                case "E": command = new MoveCommand(model, Minefield.Heading.EAST); break;
                case "W": command = new MoveCommand(model, Minefield.Heading.WEST); break;
                case "NE": command = new MoveCommand(model, Minefield.Heading.NORTHEAST); break;
                case "NW": command = new MoveCommand(model, Minefield.Heading.NORTHWEST); break;
                case "SE": command = new MoveCommand(model, Minefield.Heading.SOUTHEAST); break;
                case "SW": command = new MoveCommand(model, Minefield.Heading.SOUTHWEST); break;
                case "Save": Utilities.save(model, false); break;
                case "SaveAs": Utilities.save(model, true); break;
                case "Open": Model newModel = Utilities.open(model);
                    if (newModel != null) setModel(newModel);
                    break;
                case "New":
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    model.setUnsavedChanges(false);
                    break;
                case "Quit":
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                case "About": Utilities.inform(factory.about()); break;
                case "Help": Utilities.inform(factory.getHelp()); break;
                default:
                    Command editCommand = factory.makeEditCommand(model, cmmd, e.getSource());
                    if (editCommand != null) {
                        editCommand.execute();
                    }
            }

            if (command != null) {
                command.execute();
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MinefieldFactory();
        AppPanel panel = new MinefieldPanel(factory);
        panel.display();
    }
}

