package pl.kurs.Excercise1.models;

import java.util.Comparator;

public abstract class Clothes implements Comparable<Clothes> {

    private static Comparator<Clothes> activeComparator = CriteriaOFSorting.PRICE;
    private String name;
    private Size size;
    private double price;

    protected Clothes(String name, Size size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    static public Comparator<Clothes> getActiveComparator() {
        return activeComparator;
    }

    static public void setActiveComparator(Comparator<Clothes> activeComparator) {
        activeComparator = activeComparator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String formatToCSV() {
        return getClass().getSimpleName() + ","
                + name + ","
                + size + ","
                + price
                ;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Clothes o) {
        return activeComparator.compare(this, o);
    }


}
