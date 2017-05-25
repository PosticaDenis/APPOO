package APPOO.lab3.BuilderPattern;

/**
 * Created by Dennis on 25-May-17.
 **/
public abstract class Shirt implements PieceOfClothing{

    @Override
    public Color color() {
        return new Black();
    }

    @Override
    public abstract float price();
}
