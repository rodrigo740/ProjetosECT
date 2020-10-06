package lib;
import java.util.ArrayList;

public class UnityComp extends Unity {
    private ArrayList<Unity> unities;

    public UnityComp(String nameUni, ArrayList<Unity> unities) {
        super(nameUni, 1);
        this.unities = unities;
    }

    public ArrayList<Unity> getListUnity() {
        return unities;
    }

    public String getListUnitys() {
        String s = unities.get(0).toString();

        for (int i = 1; i < unities.size(); i++) {
            s += ", " + unities.toString();
        }
        return s;
    }

    @Override
    public String toString() {
        return super.toString() + " Dimensions: " + getListUnitys();
    }

}
