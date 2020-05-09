import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalOperations {

    /**
     * @param <A> the type of the first parameter to the interface
     * @param <B> the type of the second parameter to the interface
     * @param <C> the type of the result of the interface
     */
    interface NamedBiFunction<A, B, C> extends java.util.function.BiFunction<A, B, C> {
        String name();
    }

    /**
     *instance of NamedBiFunction with name "add", performs addition between two Doubles and returns a Double
     */
    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "add";
        }

        @Override
        public Double apply(Double a, Double b) {
            return a + b;
        }
    };

    /**
     * instance of NamedBiFunction with the name "diff", performs subtraction between two Doubles and returns a Double
     */
    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double a, Double b) {
            return a - b;
        }
    };

    /**
     * instance of NamedBiFunction with the name "mult", performs multiplication of two Doubles and returns a Double
     */
    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double a, Double b) {
            return a * b;
        }
    };

    /**
     * instance of NamedBiFunction with the name "div", performs division between two Doubles and returns a Double
     * throws an ArithmeticException if a division by zero is being attempted
     */
    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double a, Double b) {
            if (b != 0) {
                return a/b;
            } else {
                throw new ArithmeticException("Cannot divide by zero!");
            }
        }
    };

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a
     * certain type, and produce a single instance of that type -- to a list of
     * arguments of that type. The functions are applied in an iterative manner, and
     * the result of each function is stored in the list in an iterative manner as
     * well, to be used by the next bifunction in the next iteration.
     * For example, given
     *   List<Double> args = [1,1,3,0,4], and
     *   List<BiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],
     * <code>zip(args, bfs)</code> will proceed iteratively as follows:
     *   - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
     *   - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
     *   - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
     *   - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1]
     *
     * @param args:        the arguments over which <code>bifunctions</code>
     *                     will be iteratively applied.
     * @param bifunctions: the given list of bifunctions that will iteratively be
     *                     applied on the <code>args</code>.
     * @param <T>:         the type parameter of the arguments (e.g., Integer, Double)
     * @return             the last element in <code>args</code>, the final result of
     *                     all the bifunctions being applied in sequence.
     *                     NOTE: if amount of args given is not equal the amount of bfs given plus one,
     *                     null is returned
     */
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions){
        T result = null;
        if(args.size() == bifunctions.size() + 1) {
            for(int i = 0; i < bifunctions.size(); i++) {
                int next_index = i + 1;
                result = bifunctions.get(i).apply(args.get(i), args.get(next_index));
                args.set(next_index, result);
            }
        }
        return result;
    };

    /**
     * @param <A> the type of the first parameter to the class
     * @param <B> the type of the second parameter to the class
     * @param <C> the type of the result of the class
     */
    public static class FunctionComposition<A, B, C> {
        /**
         * <code>BiFunction</code> that takes in two functions, composes them, and returns one function as result
         * Function composition should be consistent with the types,
         * i.e.: the result between composing two given functions f: x -> y and g: y -> z should be h: x -> z
         */
        public BiFunction<Function<A, B>,  Function<B, C>, Function<A, C>> composition = Function::andThen;
    }
}
