import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Person> personStream = Stream.of(new Person ("Oleg", 37),
                new Person("Vadim", 28),
                new Person("Igor", 35),
                new Person("Egor", 41));

        BiConsumer<Person, Person> biConsumer = (min, max) -> System.out.println(min.toString() + max.toString());
        Comparator<Person> personComparator = Comparator.comparing(Person::getAge).thenComparing(Person::getName);
        findMinMax(personStream, personComparator, biConsumer);

        findCountEvenNums(List.of(34,56,6,8));

    }

    public static <T> void findMinMax (
    Stream<? extends T> stream,
    Comparator<? super T> order,
    BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> list = stream.collect(Collectors.toList());
        if (list.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            list.sort(order);
            minMaxConsumer.accept(list.get(0), list.get(list.size() - 1));
        }
    }

    public static void findCountEvenNums(List<Integer> list) {
        Stream<Integer> stream = list.stream();
        Predicate<Integer> predicate;
        predicate = (n) -> (n % 2) == 0;
        Stream<Integer> resSteam = stream.filter(predicate);
        System.out.println("Количество чётных чисел: " + resSteam.count());
    }
}