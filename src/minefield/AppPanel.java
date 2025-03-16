package minefield;

import minefield.Model;
import minefield.View;
import minefield.Utilities;
import tools.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//TODO: implement appfactory
public class AppPanel extends JPanel implements ActionListener, Subscriber
//        , AppFactory
{
    private Model model;
    private JPanel controlPanel;
    private View view;
    private String filePath = null;

    public AppPanel() {
        model = new Model();
        model.subscribe(this);
        view = new View(model);
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(8, 1));
        controlPanel.setPreferredSize(new Dimension(100, 300));

        JPanel p = new JPanel();
        JButton north = new JButton("N");
        north.addActionListener(this);
        p.add(north);
        controlPanel.add(p);

        p = new JPanel();
        JButton east = new JButton("E");
        east.addActionListener(this);
        p.add(east);
        controlPanel.add(p);

        p = new JPanel();
        JButton south = new JButton("S");
        south.addActionListener(this);
        p.add(south);
        controlPanel.add(p);

        p = new JPanel();
        JButton west = new JButton("W");
        west.addActionListener(this);
        p.add(west);
        controlPanel.add(p);

        p = new JPanel();
        JButton northwest = new JButton("NW");
        northwest.addActionListener(this);
        p.add(northwest);
        controlPanel.add(p);

        p = new JPanel();
        JButton northeast = new JButton("NE");
        northeast.addActionListener(this);
        p.add(northeast);
        controlPanel.add(p);

        p = new JPanel();
        JButton southwest = new JButton("SW");
        southwest.addActionListener(this);
        p.add(southwest);
        controlPanel.add(p);

        p = new JPanel();
        JButton southeast = new JButton("SE");
        southeast.addActionListener(this);
        p.add(southeast);
        controlPanel.add(p);

        this.setLayout(new BorderLayout());

        this.add(controlPanel, BorderLayout.WEST);
        this.add(view, BorderLayout.CENTER);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.createMenuBar());
        frame.add(this);
        frame.setTitle("Minefield");
        frame.setSize(500, 450);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Change"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            switch (command) {
                //TODO: implement moving
                // prob command class
                case "North":
                    break;

                case "South":
                    break;

                case "East":
                    break;

                case "West":
                    break;

                case "Northeast":
                    break;

                case "Northwest":
                    break;

                case "Southeast":
                    break;

                case "Southwest":
                    break;

                case "Save": {
                    if(filePath != null) {
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
                        os.writeObject(this.model);
                        os.close();
                    } else {
                        String fName = Utilities.getFileName((String) null, false);
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                        os.writeObject(this.model);
                        os.close();
                        filePath = fName;
                    }
                    break;
                }

                case "Save As": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    filePath = fName;
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        model = (Model) is.readObject();
//                        view.setModel(model);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    model = new Model();
//                    view.setModel(model);
                    break;
                }

                case "Quit": {
                    int option = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to quit?",
                            "Quit Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    break;
                }

                case "About": {
                    Utilities.inform("Allen Le Model Graphics, March 4, 2025. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Use North, East, South, West buttons to draw.",
                            "Clear button deletes all drawn lines.",
                            "Use Color button to change color.",
                            "Pen button disables/enables drawing."
                    };
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    throw new Exception("Unrecognized command: " + command);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    public void update() {

    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}
