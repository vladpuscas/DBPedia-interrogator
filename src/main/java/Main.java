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
        //System.out.println(option);

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
        //ontModel.read("dbpedia_2014.owl");
        while(true) {
            model.read(in, null);

            StmtIterator iterator = model.listStatements();
            List<String> properties = new ArrayList<String>();
            List<Resource> resources = new ArrayList<Resource>();

            while (iterator.hasNext()) {
                Statement statement = iterator.nextStatement();
                Property p = statement.getPredicate();
                Resource r = statement.getSubject();
                if (p.toString().contains(ns) && !properties.contains(p.toString().replaceAll(ns, ""))) {
                    properties.add(p.toString().replaceAll(ns, ""));
                    resources.add(r);
                }
            }
            for (int i = 0; i < properties.size(); i++)
                System.out.println(i + ":" + properties.get(i));
            option = scanner.nextInt();

            Property property = model.getProperty(ns + properties.get(option));
            NodeIterator nodeIterator = model.listObjectsOfProperty(property);
            int j=0;
            List<RDFNode> newResource = new ArrayList<RDFNode>();
            while (nodeIterator.hasNext()) {
                RDFNode ans = nodeIterator.nextNode();
                if(!newResource.contains(ans)) {
                    newResource.add(ans);
                    System.out.println(j + ":" + ans.toString().replaceAll(resNS,""));
                    j++;
                }
            }
            option = scanner.nextInt();
            if(option == -1){
                break;
            }
            else {
                if(newResource.get(option).toString().contains(resNS)) {
                    in = FileManager.get().open(newResource.get(option).toString().replace(resNS, dataNS) + ".rdf");
                    System.out.println(newResource.get(option).toString().replaceAll(resNS, dataNS) + ".rdf");
                    properties.clear();
                    resources.clear();
                    newResource.clear();
                    model = ModelFactory.createDefaultModel();
                }
                else {
                    System.out.println(newResource.get(option).toString());
                    break;
                }
            }


        }





    }
}
