import com.github.andrewoma.dexx.collection.ArrayList;
import com.github.andrewoma.dexx.collection.List;
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
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by slayer on 11/28/16.
 */
public class Main {
    public static String ns = "http://dbpedia.org/ontology/";
    public static String dataNS = "http://dbpedia.org/data/";
    public static String resNS = "http://dbpedia.org/resource/";
    public static String propertyNS = "http://dbpedia.org/property/";
    public static String[] domains = {"The Witcher 3: Wild Hunt", "Age of Empires", "Battlefield: Bad Company 2", "The Witcher 2: Assassins of kings", "Assassin's Creed IV"};
    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();

        OntModel ontModel = ModelFactory.createOntologyModel("http://www.w3.org/2002/07/owl#");
        InputStream in = null;
        Resource resource;
        System.out.println("Available domains:");
        for(int i=0; i<domains.length; i++)
            System.out.println(i + ":" +  domains[i]);
        Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        Integer option;
        option = scanner.nextInt();
        System.out.println(option);

        switch (option) {
            case 0:
                in = FileManager.get().open(dataNS + "The_Witcher_3:_Wild_Hunt.rdf");
                break;
            case 1:
                in = FileManager.get().open(dataNS +"Age_of_Empires.rdf");
                break;
            case 2:
                in = FileManager.get().open(dataNS +"Battlefield:_Bad_Company_2.rdf");
                break;
            case 3:
                in = FileManager.get().open(dataNS +"The_Witcher_2:_Assassins_of_Kings.rdf");
                break;
            case 4:
                in = FileManager.get().open(dataNS +"Assassin's_Creed_IV:_Black_Flag.rdf");
                break;
            default:
                System.out.println("The option is not valid");
        }
        ontModel.read("dbpedia_2014.owl");
        model.read(in,null);

        StmtIterator iterator = model.listStatements();
        //Property prop = model.getProperty(propertyNS + "developer");
        //Resource r = model.getResource(resNS + "Battlefield:_Bad_Company_2");
        //Resource res = r.getProperty(prop).getResource();
        //System.out.println(res);
        ArrayList<String> properties = new ArrayList<String>();
        while(iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Property p = statement.getPredicate();
            

        }
        //Resource resource = (Resource)r.getProperty(prop).getResource();
        //System.out.println(resource);
        /*while(iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();
            System.out.println(object.toString().replaceAll(resNS,"") + " is " + predicate.toString().replaceAll(ns,"") + " of " + subject.toString().replaceAll(resNS,""));
        }*/
        OntClass ontClass = ontModel.getOntClass(ns + "VideoGame");
        Model m = ontClass.getModel();
        StmtIterator i = m.listStatements();
       /*while(i.hasNext()) {
            Statement statement = i.nextStatement();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();
            System.out.println("Subject: " + subject.toString());
            //System.out.println("Predicate: " + predicate.toString());
            //if(object instanceof Resource)
                if(subject.toString().contains("/resource/"))
                    System.out.println("Subject: " + subject.toString());

        }*/

        //System.out.println(ontClass);
        //model.write(System.out,"Turtle");


    }
}
