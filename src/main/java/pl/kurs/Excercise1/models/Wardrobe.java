package pl.kurs.Excercise1.models;

import java.io.*;
import java.util.*;

public class Wardrobe implements Iterable<Clothes> {

    private final int capacity;
    private int size;
    private Node<Clothes> head;
    private Node<Clothes> tail;

    public Wardrobe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Pojemność musi być większa od zera!");
        }
        this.capacity = capacity;
    }

    public void saveToFile() {
        try (
                FileWriter fileWriter = new FileWriter("wardrobe.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            List<Clothes> clothes = getClothesAsList();
            for (Clothes clothe : clothes) {
                bufferedWriter.write(clothe.formatToCSV());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try (
                FileReader fileReader = new FileReader("wardrobe.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line = null;
            String[] splitedData;
            while ((line = bufferedReader.readLine()) != null) {
                splitedData = line.split(",");
                Clothes clothes = switch (splitedData[0]) {
                    case "Jeans" ->
                            new Jeans(splitedData[1], Size.valueOf(splitedData[2]), Double.parseDouble(splitedData[3]));
                    case "Pants" ->
                            new Pants(splitedData[1], Size.valueOf(splitedData[2]), Double.parseDouble(splitedData[3]));
                    case "Tshirt" ->
                            new Tshirt(splitedData[1], Size.valueOf(splitedData[2]), Double.parseDouble(splitedData[3]));
                    default -> throw new IllegalStateException("Nieznane ubranie " + splitedData[0]);
                };
                append(clothes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public void add(Clothes... items) {
        if (items == null) {
            System.out.println("Błąd dodawania");
            return;
        }
        for (Clothes item : items) {
            if (!append(item)) {
                if (size >= capacity) {
                    break;
                }
            } else {
                System.out.println("Dodano ubranie!");
            }
        }
    }

    private boolean append(Clothes item) {
        if (item == null) {
            System.out.println("Nie można tego dodac!");
            return false;
        }
        if (size >= capacity) {
            System.out.println("Brak miejsca w szafie");
            return false;
        }
        Node<Clothes> node = new Node<>(item);
        if (head == null) {
            tail = node;
            head = tail;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public List<Clothes> getClothesAsList() {
        if (head == null) {
            return Collections.emptyList();
        }
        List<Clothes> clothes = new ArrayList<>(size);
        for (Node<Clothes> n = head; n != null; n = n.next) {
            clothes.add(n.value);
        }
        return clothes;
    }

    @Override
    public String toString() {
        return "Wardrobe{" +
                "capacity=" + capacity +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator<Clothes> iterator() {
        return new Iterator<>() {
            private Node<Clothes> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Clothes next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Clothes value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private static final class Node<E> {
        private E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }
}
