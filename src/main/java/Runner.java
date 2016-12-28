import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

import java.util.Scanner;

/**
 * Created by Vlad on 28-Dec-16.
 */
public class Runner {
    private StatementHandler statementHandler;
    public static String[] domains = {"The_Witcher_3:_Wild_Hunt", "Age_of_Empires", "Battlefield:_Bad_Company_2", "The_Witcher_2:_Assassins_of_kings", "Assassin's_Creed_IV"};
    public Runner () {
        statementHandler = new StatementHandler();
    }
    public void run() {
        Property property = statementHandler.getProperty(chooseDomain());
        String resource = statementHandler.getResourceWithProperty(property);
        statementHandler.listResources(resource,property);
    }
    private String chooseDomain() {
        for(int i=0; i<domains.length; i++)
            System.out.println(i + ":" +  domains[i]);
        Scanner scanner = new Scanner(System.in);
        Integer option;
        option = scanner.nextInt();

        return domains[option];
    }
}
