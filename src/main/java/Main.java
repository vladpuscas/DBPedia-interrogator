import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ext.com.google.common.base.Predicate;
import org.apache.jena.ext.com.google.common.util.concurrent.Service;
import org.apache.jena.graph.Triple;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntTools;
import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.expr.E_Regex;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * Created by slayer on 11/28/16.
 */
public class Main {
    public static String ns = "http://dbpedia.org/ontology/";
    public static String dataNS = "http://dbpedia.org/data/";
    public static String resNS = "http://dbpedia.org/resource/";
    public static String propertyNS = "http://dbpedia.org/property/";

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();

    }
}
