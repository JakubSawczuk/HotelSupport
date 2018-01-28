package database;

import exceptions.DuplicatePrimaryKeyException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kuba on 2018-01-14.
 */


public class SupportDatabase implements Runnable {


    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    public static void persistObject(Object obj, String pesel) throws DuplicatePrimaryKeyException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new DuplicatePrimaryKeyException(pesel);
        }
    }

    public static void persistSimpleObject(Object obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void logInToDatabase() {
        List databaseConfiguration = parseXML();
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.user", databaseConfiguration.get(0).toString());
        persistenceMap.put("javax.persistence.jdbc.password", databaseConfiguration.get(1).toString());

        // ToDo: Do odkomentowania
        /*Logger.info(databaseConfiguration.get(0).toString());
        Logger.info(databaseConfiguration.get(1).toString());*/

        entityManagerFactory = Persistence.createEntityManagerFactory("hotel", persistenceMap);
        entityManager = entityManagerFactory.createEntityManager();
    }


    private List<String> parseXML() {
        List loginAndPassword = new ArrayList();
        try {
            File file = new File("src\\main\\resources\\database.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeLst = document.getElementsByTagName("database");
            Node firstNode = nodeLst.item(0);

            if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstElement = (Element) firstNode;

                NodeList loginElementsList = firstElement.getElementsByTagName("user");
                Element loginElementItemFromList = (Element) loginElementsList.item(0);
                NodeList loginElement = loginElementItemFromList.getChildNodes();

                NodeList passwordElementsList = firstElement.getElementsByTagName("pass");
                Element passwordElemtsItemFromList = (Element) passwordElementsList.item(0);
                NodeList passwordElement = passwordElemtsItemFromList.getChildNodes();

                loginAndPassword.add((loginElement.item(0)).getNodeValue());
                loginAndPassword.add((passwordElement.item(0)).getNodeValue());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginAndPassword;
    }

    @Override
    public void run() {
        logInToDatabase();
        System.out.println("Loguje sie do bazy");
    }
}
