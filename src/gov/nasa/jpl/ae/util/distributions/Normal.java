package gov.nasa.jpl.ae.util.distributions;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Normal extends NormalDistribution implements Distribution  {

  public Normal(Double mu, Double sigma){
    super(mu,sigma);
  }
}
