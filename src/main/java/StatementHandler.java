import org.apache.jena.rdf.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Vlad on 28-Dec-16.
 */
public class StatementHandler {
    private ModelHandler modelHandler;
    private Scanner scanner;
    private static final String ns = "http://dbpedia.org/ontology/";
    private static final String resNS = "http://dbpedia.org/resource/";

    public StatementHandler() {
        modelHandler = new ModelHandler();
        scanner = new Scanner(System.in);
    }

    public Property getProperty(String model) {
        modelHandler.loadModel(model);
        StmtIterator iterator = modelHandler.getStatements();
        List<String> properties = new ArrayList<String>();
        List<RDFNode> resources = new ArrayList<RDFNode>();

        while (iterator.hasNext()) {
            Statement statement = iterator.nextStatement();
            Property p = statement.getPredicate();
            RDFNode r = statement.getObject();
            if (p.toString().contains(ns) && !properties.contains(p.toString().replaceAll(ns, "")) && (r.isLiteral() || r.isResource())) {
                properties.add(p.toString().replaceAll(ns, ""));
                resources.add(r);
            }
        }
        for (int i = 0; i < properties.size(); i++) {
            if(resources.get(i).isLiteral())
                System.out.println(i + ": " + properties.get(i) + " of " + modelHandler.getResource());
            else
                System.out.println(i + ": Same " + properties.get(i) + " as " + modelHandler.getResource());
        }

        Integer option = scanner.nextInt();
        return modelHandler.getProperty(ns + properties.get(option));
    }

    public String getResourceWithProperty(Property property) {
        NodeIterator nodeIterator = modelHandler.getObjectsWithProperty(property);
        RDFNode resource = nodeIterator.next();

        if (resource.isResource())
            return resource.toString().replaceAll(resNS,"");
        else if(resource.isLiteral()) {
            if(resource.asLiteral().getLanguage().equals("en"))
                System.out.println(resource.asLiteral());
            else if(nodeIterator.hasNext()) {
                while (!resource.asLiteral().getLanguage().equals("en"))
                    resource = nodeIterator.next();
                System.out.println(resource.asLiteral());
            }
            else
                System.out.println(resource.asLiteral());
            return null;
        }
        else
            return null;

    }
    public void listResources(String resource, Property property) {
        modelHandler.loadModel(resource);
        ResIterator resIterator = modelHandler.getResourcesWithProperty(property);
        while(resIterator.hasNext()) {
            Resource ans = resIterator.next();
            System.out.println(ans.toString());
        }
    }
}
