package gov.nasa.jpl.ae.util.distributions;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;

/**
 * Created by dank on 6/29/17.
 */
public class BooleanDistribution extends BinomialDistribution implements Distribution{

    public BooleanDistribution(double p) {
        super(1, p);
    }

    // This may be needed at some point.
//    public BooleanDistribution(RandomGenerator rng, double p) {
//        super(rng, 1, p);
//    }
}
