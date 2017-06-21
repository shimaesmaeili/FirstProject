import extensions.DurationInDaysException;
import extensions.depositBalanceException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Deposit> listOfObjects = new ArrayList<Deposit>();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("sample.xml");

        parseDepositFile(listOfObjects, document);
        Collections.sort(listOfObjects);
        printPayedInterest(listOfObjects);

    }

    private static void parseDepositFile(List<Deposit> listOfObjects, Document document) {
        NodeList nodeList = document.getElementsByTagName("deposit");

        for (int i = 0; i < nodeList.getLength(); i++) {
            try {
                Node node = nodeList.item(i);
                Element element = (Element) node;

                String depositType = element.getElementsByTagName("depositType").item(0).getTextContent();
                int customerNumber = Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                int durationInDays = Integer.parseInt(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                if (durationInDays <= 0)
                    throw new DurationInDaysException("Duration is not correct!");
                BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                if (depositBalance.compareTo(new BigDecimal(0)) < 0)
                    throw new depositBalanceException("Deposit Balance is Negative!");

                Object object = Class.forName(depositType).newInstance();
                Deposit dep = new Deposit((DepositType) object, customerNumber, durationInDays, depositBalance);

                BigDecimal interest = depositBalance.multiply(new BigDecimal(dep.getInterestRate())).multiply(new BigDecimal(durationInDays)).divide(new BigDecimal(36500), RoundingMode.HALF_UP);
                dep.setInterest(interest);

                listOfObjects.add(dep);
            } catch (DurationInDaysException e) {
                System.err.println(e.getMessage());
            } catch (depositBalanceException e) {
                System.err.println(e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Deposit Type did not recognized!");
            }
        }
    }

    private static void printPayedInterest(List<Deposit> listOfObjects) {
        for (Deposit d : listOfObjects)
            System.out.println(d.getCustomerNumber() + "#" + d.getInterest());
    }
}
