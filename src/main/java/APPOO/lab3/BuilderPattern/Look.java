package APPOO.lab3.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 25-May-17.
 **/
public class Look {
    private List<PieceOfClothing> clothes = new ArrayList<>();

    public void addClothing(PieceOfClothing clothing){
        clothes.add(clothing);
    }

    public float getCost(){
        float cost = 0.0f;

        for (PieceOfClothing clothing : clothes) {
            cost += clothing.price();
        }
        return cost;
    }

    public void showItems(){

        for (PieceOfClothing item : clothes) {
            System.out.print("Piece Of Clothing : " + item.name());
            System.out.print(", Color : " + item.color().getColor());
            System.out.println(", Price : " + item.price());
        }
    }
}
