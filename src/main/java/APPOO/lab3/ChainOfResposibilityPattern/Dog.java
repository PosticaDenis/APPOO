package APPOO.lab3.ChainOfResposibilityPattern;

/**
 * Created by Dennis on 21-May-17.
 **/
public class Dog extends AbstractTeam{

    @Override
    protected double getMaxWeight() {
        return weight * 40;
    }

    @Override
    protected void addMember() {
        team.add("Dog");
    }
}
