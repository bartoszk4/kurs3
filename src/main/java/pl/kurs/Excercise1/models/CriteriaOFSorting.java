package pl.kurs.Excercise1.models;

import java.util.Comparator;

public enum CriteriaOFSorting implements Comparator<Clothes> {
    PRICE {
        @Override
        public int compare(Clothes a, Clothes b) {
            return Double.compare(a.getPrice(), b.getPrice());
        }
    },
    SIZE {
        @Override
        public int compare(Clothes a, Clothes b) {
            return a.getSize().compareTo(b.getSize());
        }
    };

}
