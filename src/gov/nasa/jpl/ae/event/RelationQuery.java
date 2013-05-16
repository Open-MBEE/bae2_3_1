package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.IntegerDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * A RelationQuery is a query based on relationships among components in a
 * system model (e.g., SysML).
 */
public class RelationQuery extends Query {

  protected Collection< Object > relation = null, tuple = null;
  protected int chainLength = 1;
  protected EObject model = null;
  protected Expression< Boolean > whereClause;

  /**
   * @param objects
   */
  public RelationQuery( Object[] objects ) {
    super( objects );
    if ( param.length > 0 ) {
      relation = new ArrayList< Object >();
      relation.add( param[ 0 ] );
      tuple = new ArrayList< Object >();
      for (int i=1; i<param.length; ++i) {
        tuple.add( param[ i ] );
      }
    }
  }

  public < R, T > RelationQuery( Object relation, Collection< ? > tuple,
                                 Expression< Boolean > whereClause, EObject model ) {
    this( null );
    if ( relation instanceof Collection ) {
      this.relation = (Collection< Object >)relation;
    } else {
      this.relation = new ArrayList<Object>();
      this.relation.add( relation );
    }
    this.tuple = new ArrayList<Object>();
    this.tuple.addAll( tuple );
    this.whereClause = whereClause;
  }

  /**
   * See
   * https://jplwiki.jpl.nasa.gov:8443/display/OpsRev/Syntax+for+model+queries
   * for what this should be.
   * 
   * The {@code relations()} function performs a query on a model based on
   * relationships among components in a system model (e.g., SysML). In discrete
   * mathematics, a relation can be thought of as a set of tuples of elements
   * having the relation. For example, for integers {1,2,3}, the &lt; (lessThan)
   * relation is {(1,2),(1,3),(2,3)} because 1 &lt; 2, 1 &lt; 3, and 2 &lt; 3. A
   * relation may also be treated as a function returning {@code true} or
   * {@code false} for an input tuple. &lt;(1,2)={@code true} and &lt;(3,2)=
   * {@code false}. <!--For a given domain D={1,2,3}, the relation could simply
   * be a function returning the set: &lt;(D) = {(1,2),(1,3),(2,3)}. -->Another
   * function could be defined to take part of the tuple as input and return the
   * set of items that form matching relations for the rest of the tuple:
   * &lt;(1) = {2,3}, &lt;(3) = {}. {@code relations()} is a general function
   * for specifying queries of these different types.
   * <p>
   * The {@code relation} argument and an item in the {@code tuple} list (of
   * respective types {@code R} and {@code T}) may be a {@link String},
   * MagicDraw {@link Element}, EMF {@link EObject}, array, {@link List}, or
   * {@code null}. Arrays and {@link List}s are interpreted as choices for the
   * relation or tuple item. So, <quote> relations(new Object[]{ "partProperty",
   * "characterization"}, 1, new Object[]{powerSystem, battery})}</quote> would
   * return {@code (true, true)} if {@code battery} is a {@code partProperty}
   * and a {@code characterization} of {@code powerSystem}. For a null relation
   * or a null entry in the tuple list the result contains a collection
   * (packaged in a {@code Map}) of items each of which, if substituted for the
   * {@code null}, would be an existing relation.
   * {@code relations(powerSystem, null, battery)} would return a map with
   * {@code null} values and keys, <quote>{partProperty,
   * characterization}</quote>.
   * <p>
   * If multiple {@code null} entries are given, then the matching relations are
   * returned as a nested map. For example, consider the tertiary relation,
   * {@code a + b = c}. <quote>relations(plusEquals, 1, new Integer[]{null, 1,
   * null})</quote> returns the {@code Map}, <quote>{1={2}, 2={3},
   * 3={}}</quote>.
   * <p>
   * {@code chainLength} specifies how many times the relationship is chained
   * with itself, and is 1 by default. A binary relation can be treated as a
   * function from the specified item in the tuple to the unspecified item,
   * e.g., &lt;(1) = {2,3}. Chaining {@code lessThan} twice means taking the
   * output of the first as inputs to a second call to the function. So,
   * <quote>relations(lessThan, 2, new Object[]{1,null}) = {3}</quote> since
   * &lt;(1,{2,3}), &lt;(2,{3}) and &lt;(3,{}). In this case, the result happens
   * to correspond to the second item of the tuple. If for the first item,
   * <quote>relations(lessThan, 2, new Object[]{null,3}) = {1}</quote> since
   * &lt;({1,2},3), &lt;({},1) and &lt;({1},2). For relations among triples and
   * longer tuples where there may be multiple query fields (passing
   * {@code null}), mapping the result back to the inputs is not
   * straightforward. <quote>relations(plusEquals, 2, new Integer[]{null, 1,
   * null})</quote> returns the {@code Map}, <quote>{1={2}, 2={3}, 3={}}</quote>
   * Should the values be substituted for the second item in the tuple? TBD!
   * 
   * @param relation
   *          an identifier for the relation among model elements
   * @param chainLength
   *          a range on the number of times that the relation is chained
   * @param tuple
   *          the related items in order
   * @param whereClause
   *          a Boolean {@code Expression} filtering out related tuples based on
   *          the items
   * @param model
   *          a reference to the model on which the queries are made
   * @return a nested {@code Map} for the matching tuple items, mapping each
   *         item queried to the next or null if the last queried.
   */
  public static < R, T > Map< Object, Object >
      relations( R relation, IntegerDomain chainLength, List< T > tuple,
                 Expression< Boolean > whereClause, EObject model ) {
    HashMap< Object, Object > result = new HashMap< Object, Object >();
    List<Object> objects = new ArrayList< Object >();
    objects.add(relation);
    objects.addAll(tuple);
    //RelationQuery rq = new RelationQuery( objects.toArray() );
    RelationQuery rq = new RelationQuery( relation, tuple, whereClause, model );
    return result;
  }

  public static < R, T > Map< Object, Object > relations( R relation,
                                                          Object... tuple ) {
    return relations( relation, new IntegerDomain(1,1), tuple, true, null );
  }

  private Object subComponent;

  void f() {
    int mass = 0, localMass = 0;
    mass = localMass + sum( relations( this.subComponent, mass ) );

  }

  private int sum( Object relations ) {
    // TODO Auto-generated method stub
    return 0;
  }

  private Object relations( Object subComponent2, int mass ) {
    // TODO Auto-generated method stub
    return null;
  }

}
