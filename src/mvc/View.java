package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        if (model != null) {
            model.subscribe(this);
        }
    }

    public void setModel(Model model) {
        if (this.model != null) {
            this.model.unsubscribe(this);
        }
        this.model = model;
        if (model != null) {
            model.subscribe(this);
        }
        repaint();
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void update() {
        repaint();
    }
}



