package APPOO.lab3.ChainOfResposibilityPattern;

import java.util.ArrayList;

/**
 * Created by Dennis on 20-May-17.
 **/
public abstract class AbstractTeam {

    protected static ArrayList<String> team = new ArrayList<>();

    protected double weight = 10.0;

    protected AbstractTeam nextMember;

    protected Radish radish;

    public void setNextMember(AbstractTeam nextMember) {
        this.nextMember = nextMember;
    }

    public void memberAction(double weight){

        radish = new Radish(weight);

        addMember();
        if(radish.getWeight() <= this.getMaxWeight()) {

            printMessage(team.size(), radish.getWeight());
            team.clear();
            return;
        }
        if(nextMember != null){
            nextMember.memberAction(weight);
        }
        if (nextMember == null) {
            System.out.println("This Radish is too heavy!");
            team.clear();
        }
    }

    private void printMessage(int nrOfMembers, double weight){

        if (nrOfMembers == 1) {
            System.out.println("Just " + team + " is required to get the " + weight + " kgs Radish.");
            return;
        }
        System.out.println("Members: " + team + " are required to get the " + weight + " kgs Radish.");
    }

    abstract protected void addMember();
    abstract protected double getMaxWeight();
}
