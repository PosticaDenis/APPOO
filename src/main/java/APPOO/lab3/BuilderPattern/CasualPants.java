package APPOO.lab3.BuilderPattern;

/**
 * Created by Dennis on 25-May-17.
 **/
public class CasualPants extends Pants {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Casual Pants";
    }
}
