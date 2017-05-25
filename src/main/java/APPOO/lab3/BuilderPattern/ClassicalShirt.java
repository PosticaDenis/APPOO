package APPOO.lab3.BuilderPattern;

/**
 * Created by Dennis on 25-May-17.
 **/
public class ClassicalShirt extends Shirt {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Classical Shirt";
    }
}
