package APPOO.lab3.ChainOfResposibilityPattern;

/**
 * Created by Dennis on 21-May-17.
 **/
public class Cat extends AbstractTeam{

    @Override
    protected double getMaxWeight() {
        return weight * 50;
    }

    @Override
    protected void addMember() {
        team.add("Cat");
    }
}

