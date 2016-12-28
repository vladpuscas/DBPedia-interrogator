import org.apache.jena.rdf.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Integer option = scanner.nextInt();
        return modelHandler.getProperty(ns + properties.get(option));
    }

    public String getResourceWithProperty(Property property) {
        NodeIterator nodeIterator = modelHandler.getObjectsWithProperty(property);
        int j = 0;
        List<RDFNode> newResource = new ArrayList<RDFNode>();
        while (nodeIterator.hasNext()) {
            RDFNode ans = nodeIterator.nextNode();
            if (!newResource.contains(ans)) {
                newResource.add(ans);
                System.out.println(j + ":" + ans.toString().replaceAll(resNS, ""));
                j++;
            }
        }
        Integer option = scanner.nextInt();
        if (newResource.get(option).toString().contains(resNS))
            return newResource.get(option).toString().replaceAll(resNS,"");
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
