package lib;
import java.util.ArrayList;

public class DimensionComp extends Dimension {

    private ArrayList<Dimension> dimensions;

    public DimensionComp(String d, Unity uni, ArrayList<Dimension> dimensions) {
        super(d, uni);
        // ordenar as dimensoes por ordem alfabetica
        this.dimensions = ordenar(dimensions);
    }

    private ArrayList<Dimension> ordenar(ArrayList<Dimension> dimensions) {
        for (Dimension dim : dimensions) {
            for (Dimension d : dimensions) {
                if (dim.getDimension().compareTo(d.getDimension()) > 0) { // a<b da <0 se a>b da >0 e se for igual da =0
                    Dimension temp = dim;
                    dimensions.add(dimensions.indexOf(dim), d);
                    dimensions.add(dimensions.indexOf(d), temp);
                }
            }
        }
        return dimensions;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // acrescentar funcoes de multiplicacao e isso
    // verificar se um deles tem a mesma dimencao para a +
}
