package com.trust.process;

import org.apache.jena.atlas.lib.Sink ;
import org.apache.jena.graph.Node ;
import org.apache.jena.graph.Triple ;
import org.apache.jena.rdf.model.Property ;
import org.apache.jena.riot.RDFDataMgr ;
import org.apache.jena.riot.out.SinkTripleOutput ;
import org.apache.jena.riot.system.StreamRDF ;
import org.apache.jena.riot.system.StreamRDFBase ;
import org.apache.jena.riot.system.SyntaxLabels ;
import org.apache.jena.sparql.vocabulary.FOAF ;

import java.io.Serializable;

public class RiotForTrust implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void GetRDFValue(String filename){
	        // This is the heart of N-triples printing ... output is heavily buffered
	        // so the FilterSinkRDF called flush at the end of parsing.
	        Sink<Triple> output = new SinkTripleOutput(System.out, null, SyntaxLabels.createNodeToLabel()) ;
	        StreamRDF filtered = new FilterSinkRDF(output, FOAF.name, FOAF.knows) ;
	        
	        // Call the parsing process. 
	        RDFDataMgr.parse(filtered, filename) ;
	    }
	    
	    static class FilterSinkRDF extends StreamRDFBase
	    {
	        private final Node[] properties ;
	        // Where to send the filtered triples.
	        private final Sink<Triple> dest ;

	        FilterSinkRDF(Sink<Triple> dest, Property... properties)
	        {
	            this.dest = dest ;
	            this.properties = new Node[properties.length] ;
	            for ( int i = 0 ; i < properties.length ; i++ ) 
	                this.properties[i] = properties[i].asNode() ;
	        }

	        @Override
	        public void triple(Triple triple)
	        {
	            for ( Node p : properties )
	            {
	                if ( triple.getPredicate().equals(p) )
	                    dest.send(triple) ;
	            }
	        }
	        
	        @Override
	        public void finish()
	        {
	            // Output may be buffered.
	            dest.flush() ;
	        }
	}

}
