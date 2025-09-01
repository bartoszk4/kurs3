package pl.kurs.Excercise3;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        List<String> stringList = List.of("B", "A", "D", "C");
        List<Integer> integerList = List.of(-1,2,50,88,69,0);

        Person person1 = new Person(90);
        Person person2 = new Person(15);
        Person person3 = new Person(12);

        List<Person> personList = List.of(person1,person2,person3);

        System.out.println(stringList);
        System.out.println(integerList);
        System.out.println(personList);

        MinMax<String> result1 = MinMaxService.getMinAndMax(stringList);
        MinMax<Integer> result2 = MinMaxService.getMinAndMax(integerList);
        MinMax<Person> result3 = MinMaxService.getMinAndMax(personList);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);


    }
}
