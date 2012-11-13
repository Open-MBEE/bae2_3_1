package gov.nasa.jpl.ae.event;

public interface AspenTimelineWritable {

  String toAspenMdl( String tlName );

  String toAspenIni( String tlName );

}
