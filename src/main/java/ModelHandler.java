import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

/**
 * Created by Vlad on 28-Dec-16.
 */
public class ModelHandler {
    private Model model;
    private InputStream inputStream;
    private static String dataNS = "http://dbpedia.org/data/";
    public ModelHandler() {
        model = ModelFactory.createDefaultModel();
        inputStream = null;
    }
    public void loadModel(String resource) {
        model = ModelFactory.createDefaultModel();
        inputStream = FileManager.get().open(dataNS + resource + ".rdf");
        model.read(inputStream,null);
    }
    public StmtIterator getStatements() {
        return model.listStatements();
    }
    public NodeIterator getObjectsWithProperty(Property property) {
        return model.listObjectsOfProperty(property);
    }
    public ResIterator getResourcesWithProperty(Property property) {
        return model.listResourcesWithProperty(property);
    }
    public Property getProperty(String nameOfProperty) {
        return model.getProperty(nameOfProperty);
    }
}
