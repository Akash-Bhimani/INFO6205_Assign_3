package edu.neu.coe.info6205.functions;

import java.util.function.DoubleFunction;

public class Newton {

    public Newton(final String equation, final DoubleFunction<Double> f, final DoubleFunction<Double> dfbydx) {
        this.equation = equation;
        this.f = f;
        this.dfbydx = dfbydx;
    }

    public Either<String, Double> solve(final double x0, final int maxTries, final double tolerance) {
        double x = x0;
        int tries = maxTries;
        for (; tries > 0; tries--)
            try {
                final double y = f.apply(x);
                if (Math.abs(y) < tolerance) return Either.right(x);
                x = x - y / dfbydx.apply(x);
            } catch (Exception e) {
                return Either.left("Exception thrown solving " + equation + "=0, given x0=" + x0 + ", maxTries=" + maxTries + ", and tolerance=" + tolerance + " because " + e.getLocalizedMessage());
            }
        return Either.left(equation + "=0 did not converge given x0=" + x0 + ", maxTries=" + maxTries + ", and tolerance=" + tolerance);
    }

    public static void main(String[] args) {

//    	Newton newton = new Newton("cos(x) - x", (double x) -> Math.cos(x) - x, (double x) -> -Math.sin(x) - 1);
        Newton newton = new Newton("1 - sin(x)", (double x) -> Math.sin(x) , (double x) -> Math.cos(x));

        Either<String, Double> result = newton.solve(1.0, 200, 1E-7);

        result.apply(
                System.err::println,
                aDouble -> {
                    // Publish the happy news.
                    System.out.println("Good news! " + newton.equation + " was solved: " + aDouble);
                });
    }

    private final String equation;
    private final DoubleFunction<Double> f;
    private final DoubleFunction<Double> dfbydx;
}
