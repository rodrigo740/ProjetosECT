package lib;
import java.util.ArrayList;

public class Dimension {
    private Unity defaultUnity;
    private String dimension;
    private ArrayList<Unity> unities;
    private Boolean allowNeg = false;

    public Dimension(String d, Unity uni) {
        dimension = d;
        defaultUnity = uni;
        unities = new ArrayList<>();
    }

    public String getDimension() {
        return dimension;
    }

    public Unity getUnity() {
        return defaultUnity;
    }

    public boolean getAllowNeg() {
        return allowNeg;
    }

    public ArrayList<Unity> getUnities() {
        return unities;
    }

    public boolean containsUnity(Unity u) {
        return unities.contains(u);
    }

    public boolean checkUnityByName(String name) {

        for (Unity unity : unities) {
            if (unity.getUnity().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addElem(Unity u) {
        if (unities.contains(u)) {
            return false;
        }
        unities.add(u);
        return true;
    }

    public void setUnity(Unity u) {
        this.defaultUnity = u;
    }

    public void allowNeg() {
        this.allowNeg = true;
    }

    @Override
    public String toString() {
        return "Dimension = " + dimension + " tem " + unities.size() + "unidades";
    }
}