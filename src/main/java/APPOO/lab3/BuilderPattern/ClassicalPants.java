package APPOO.lab3.BuilderPattern;

/**
 * Created by Dennis on 25-May-17.
 **/
public class ClassicalPants extends Pants {

    @Override
    public float price() {
        return 50.0f;
    }

    @Override
    public String name() {
        return "Classical Pants";
    }
}
