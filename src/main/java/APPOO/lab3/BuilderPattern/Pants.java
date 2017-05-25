package APPOO.lab3.BuilderPattern;

/**
 * Created by Dennis on 25-May-17.
 **/
public abstract class Pants implements PieceOfClothing {

    @Override
    public Color color() {
        return new Blue();
    }

    @Override
    public abstract float price();
}
