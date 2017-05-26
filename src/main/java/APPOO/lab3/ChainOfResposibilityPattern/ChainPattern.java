package APPOO.lab3.ChainOfResposibilityPattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Dennis on 21-May-17.
 **/
public class ChainPattern {

    private static AbstractTeam getChainOfMembers(){

        AbstractTeam grandpa = new Grandpa();
        AbstractTeam grandma = new Grandma();
        AbstractTeam granddaughter = new Granddaughter();
        AbstractTeam dog = new Dog();
        AbstractTeam cat = new Cat();
        AbstractTeam mouse = new Mouse();

        grandpa.setNextMember(grandma);
        grandma.setNextMember(granddaughter);
        granddaughter.setNextMember(dog);
        dog.setNextMember(cat);
        cat.setNextMember(mouse);

        return grandpa;
    }

    public static void main(String[] args) {
        // Press Ctrl+C to end.
        try {
            while (true) {
                System.out.println("Enter the kg amount of the Radish.");
                System.out.print(">");
                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                getChainOfMembers().memberAction(d);
            }
        }
        catch (Exception e) {
            System.out.println("Invalid value!");
            System.exit(1);
        }
    }
}
