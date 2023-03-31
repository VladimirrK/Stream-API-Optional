import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
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
    }

    public static <T> void findMinMax (
    Stream<? extends T> stream,
    Comparator<? super T> order,
    BiConsumer<? super T, ? super T> minMaxConsumer) {

        Optional<? extends T> max = stream.max(order);
        Optional<? extends T> min = stream.min(order);
        minMaxConsumer.accept(min.orElse(null), max.orElse(null));
    }

    public static long findCountEvenNums(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .peek(System.out::print)
                .count();
    }
}