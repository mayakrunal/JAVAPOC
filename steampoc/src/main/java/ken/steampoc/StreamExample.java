package ken.steampoc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void doStreamExample() {

        // empty stream
        emptyStream();

        // stream collection & array
        streamOfArrayAndCollection();

        // stream builder
        streamBuilder();

        // stream generate
        streamGenerate();

        // stream iterate
        streamIterate();

        // stream reduce
        streamReduce();

        // stream collector
        streamCollector();
    }

    private static void emptyStream() {
        final Stream<String> streamEmpty = Stream.empty();
        System.out.println("Empty Stream : " + streamEmpty.collect(Collectors.toList()));
    }

    private static void streamOfArrayAndCollection() {

        final Collection<String> collection = Arrays.asList("a", "b", "c");
        final Stream<String> streamOfCollection = collection.stream();
        System.out.println("Stream of collection: " + streamOfCollection.collect(Collectors.toList()));

        final Stream<String> streamOfArray = Stream.of("a", "b", "c");
        System.out.println("Stream of array: " + streamOfArray.collect(Collectors.toList()));
    }

    private static void streamBuilder() {

        final Stream<String> streamBuilder = Stream.<String>builder()
                                                   .add("a")
                                                   .add("b")
                                                   .add("c")
                                                   .build();

        System.out.println("Stream builder: " + streamBuilder.collect(Collectors.toList()));

    }

    private static void streamGenerate() {
        final Stream<String> streamGenerated = Stream.generate(() -> "element")
                                                     .limit(10);
        System.out.println("Stream generator: " + streamGenerated.collect(Collectors.toList()));
    }

    private static void streamIterate() {

        final Stream<Integer> streamIterated = Stream.iterate(0, n -> n + 2)
                                                     .limit(50);
        System.out.println("Stream iterated: " + streamIterated.collect(Collectors.toList()));
    }

    private static void streamReduce() {
        final OptionalInt reduced = IntStream.range(1, 4)
                                             .reduce((a, b) -> a + b);

        System.out.println("Stream reduced (1 param): " + reduced.getAsInt());

        final int reducedTwoParams = IntStream.range(1, 4)
                                              .reduce(10, (a, b) -> a + b);
        System.out.println("Stream reduced (2 param): " + reducedTwoParams);
    }

    private static void streamCollector() {

        final List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"), new Product(13, "lemon"),
                                                        new Product(23, "bread"), new Product(13, "sugar"));

        System.out.println("Product List: " + productList);

        // stream to collection
        final List<String> nameCollection = productList.stream()
                                                       .map(Product::getName)
                                                       .collect(Collectors.toList());
        System.out.println("Stream to collection: " + nameCollection);

        // reduce to string
        final String listToString = productList.stream()
                                               .map(Product::getName)
                                               .collect(Collectors.joining(",", "\"", "\""));
        System.out.println("Stream Reduced to String: " + listToString);

        // avaragePrice
        final double avaragePrice = productList.stream()
                                               .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println("Stream Reduced to Avarage: " + avaragePrice);

        // sumPrice
        final int sumPrice = productList.stream()
                                        .collect(Collectors.summingInt(Product::getPrice));
        System.out.println("Stream Reduced to Sum: " + sumPrice);

        // integer sum statistics
        final IntSummaryStatistics statistics = productList.stream()
                                                           .collect(Collectors.summarizingInt(Product::getPrice));
        System.out.println("Stream Reduced to SummaryStatistics: " + statistics);

        // grouping by specific map
        final Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
                                                                           .collect(Collectors.groupingBy(Product::getPrice));

        System.out.println("Stream Group by Map Function: " + collectorMapOfLists);

        // partition by
        final Map<Boolean, List<Product>> mapPartioned = productList.stream()
                                                                    .collect(Collectors.partitioningBy(x -> x.getPrice() > 15));
        System.out.println("Stream Partitioned by Map Function: " + mapPartioned);

        // collector do additional task
        final Set<Product> unmodifiableSet = productList.stream()
                                                        .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        System.out.println("Stream to set and then unmodifiableset" + unmodifiableSet);

        // custom collector
        final Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new, LinkedList::add, (first, second) -> {
            first.addAll(second);
            return first;
        });

        final LinkedList<Product> linkedListOfPersons = productList.stream()
                                                                   .sorted(Comparator.comparing(Product::getPrice)
                                                                                     .thenComparing(Product::getName))
                                                                   .collect(toLinkedList);

        System.out.println("Linked List of Person : " + linkedListOfPersons);
    }
}
