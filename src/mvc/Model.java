package mvc;

public class Model extends Publisher  {

    private String fileName = null;
    private boolean unsavedChanges = false;

    public Model() {}

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }
}



