package am.ith.app.db_engine;

public class Model {
    long id=1;
    String isSelected;
    long position;

    public Model() {
    }

    public Model(long position, String isSelected) {
        this.position=position;
        this.isSelected = isSelected;
    }

    public long getId() {
        return id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
