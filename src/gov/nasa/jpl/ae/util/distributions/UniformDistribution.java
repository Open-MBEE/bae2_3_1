package gov.nasa.jpl.ae.util.distributions;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.random.RandomGenerator;

/**
 * Created by dank on 7/12/17.
 */
public class UniformDistribution extends UniformIntegerDistribution implements Distribution {
    public UniformDistribution(int lower, int upper) throws NumberIsTooLargeException {
        super(lower, upper);
    }

    public UniformDistribution(RandomGenerator rng, int lower, int upper) throws NumberIsTooLargeException {
        super(rng, lower, upper);
    }
}
