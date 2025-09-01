package pl.kurs.Excercise2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/*
MONDAY=535
TUESDAY=61
WEDNESDAY=38
THURSDAY=40
FRIDAY=21
SATURDAY=26
SUNDAY=40
 */

public class Runner {
    public static void main(String[] args) {

        List<File> myJavaFiles = new ArrayList<>();

        findJavaFiles(new File("C:\\Users\\xyz\\IdeaProjects"), myJavaFiles);

        printWeeklySummary(myJavaFiles);

    }

    static void printWeeklySummary(List<File> list) {
        Map<DayOfWeek, Integer> dayOfWeekMap = new TreeMap<>();
        DayOfWeek dayOfCreation;

        for (File file : list) {
            dayOfCreation = getCreationDayOfWeekFromFile(file);
            if (!dayOfWeekMap.containsKey(dayOfCreation)) {
                dayOfWeekMap.put(dayOfCreation, 1);
            } else {
                dayOfWeekMap.merge(dayOfCreation, 1, Integer::sum);
            }
        }
        Set<Map.Entry<DayOfWeek, Integer>> entries = dayOfWeekMap.entrySet();

        for (Map.Entry<DayOfWeek, Integer> entry : entries) {
            System.out.println(entry);
        }
    }

    private static DayOfWeek getCreationDayOfWeekFromFile(File file) {
        DayOfWeek creationDayOfWeek = null;
        Path path = Paths.get(file.getPath());
        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime creationTime = attr.creationTime();
            LocalDate creationLocalDate = LocalDate.ofInstant(creationTime.toInstant(), ZoneId.systemDefault());

            creationDayOfWeek = creationLocalDate.getDayOfWeek();

        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu atrybutów pliku: " + e.getMessage());
        }
        return creationDayOfWeek;
    }

    static void findJavaFiles(File directory, List<File> filesList) {
        if (!directory.isDirectory()) {
            throw new RuntimeException("Selected path is not a directory!");
        }
        File[] filesArray = directory.listFiles();
        for (File f : filesArray) {
            if (f.isDirectory()) {
                findJavaFiles(f, filesList);
            } else if (f.getName().endsWith(".java")) {
                filesList.add(f);
            }
        }
    }

}

