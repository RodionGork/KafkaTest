package kafkafib;

public class App {

    public static void main(String... args) {
        if (args.length > 0) {
            String numericArgument = args.length > 1 ? args[1] : "";
            switch (args[0]) {
                case "produce":
                    new FibProducer(numericArgument).run();
                    return;
                case "consume":
                    new FibConsumer(numericArgument).run();
            }
        }
        System.out.println("USAGE: java -jar kafka-fib.jar <produce|consume> [n|m]");
    }
}
