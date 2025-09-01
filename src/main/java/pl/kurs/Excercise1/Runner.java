package pl.kurs.Excercise1;

import pl.kurs.Excercise1.models.*;

import java.util.Collections;

public class Runner {
    public static void main(String[] args) {

        Tshirt tshirt = new Tshirt("Armani", Size.XL, 500);
        Tshirt tshirt2 = new Tshirt("Boss", Size.M, 300);
        Jeans jeans = new Jeans("House", Size.M, 250);
        Pants pants = new Pants("Lidl", Size.S, 501);

        Wardrobe wardrobe = new Wardrobe(20);
        Wardrobe wardrobe2 = new Wardrobe(20);

        wardrobe2.add(tshirt, null);

        wardrobe.add(tshirt, tshirt2, jeans);
        wardrobe.add(tshirt, tshirt2);
        wardrobe.add(pants);
//
//        for (Clothes clothes : wardrobe) {
//            System.out.println(clothes);
//        }

//        Clothes.setActiveComparator(CriteriaOFSorting.SIZE);
//        Clothes biggest = Collections.max(wardrobe.getClothesAsList());
//        Clothes.setActiveComparator(CriteriaOFSorting.PRICE);
//        Clothes theMostExpensive = Collections.max(wardrobe.getClothesAsList());
//        System.out.println(theMostExpensive);

//        System.out.println(wardrobe);

//        wardrobe.saveToFile();
        wardrobe2.readFromFile();

    }


}
