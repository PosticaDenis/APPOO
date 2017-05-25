package APPOO.lab3.ChainOfResposibilityPattern;

/**
 * Created by Dennis on 21-May-17.
 **/
public class Mouse extends AbstractTeam{

    @Override
    protected double getMaxWeight() {
        return weight * 60;
    }

    @Override
    protected void addMember() {
        team.add("Mouse");
    }
}
