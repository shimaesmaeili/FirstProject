import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Implementation {
    public static void main(String[] args){
        ArrayList<Deposit> listOfObjects = new ArrayList<Deposit>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("sample.xml");

            NodeList nodeList = document.getElementsByTagName("deposit");

            for (int i = 0; i < nodeList.getLength(); i++) {
                try {
                    Node node = nodeList.item(i);
                    Element element = (Element) node;

                    String depositType = element.getElementsByTagName("depositType").item(0).getTextContent();
                    int customerNumber = Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                    int durationInDays = Integer.parseInt(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                    if (durationInDays <= 0)
                        throw new ArithmeticException("Duration is not correct!");
                    BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                    if (depositBalance.compareTo(new BigDecimal(0)) < 0)
                        throw new ArithmeticException("Deposit Balance is Negative!");

                    Class<?> wantedClass = Class.forName(depositType);
                    Constructor<?> constructor = wantedClass.getConstructor(int.class, int.class, BigDecimal.class);
                    Object object = constructor.newInstance(customerNumber, durationInDays, depositBalance);

                    Method getInterestRate = object.getClass().getMethod("getInterestRate");

                    BigDecimal interest = depositBalance.multiply(new BigDecimal((double) getInterestRate.invoke(object))).multiply(new BigDecimal(durationInDays)).divide(new BigDecimal(36500), RoundingMode.HALF_UP);

                    Method setInterest = object.getClass().getMethod("setInterest", BigDecimal.class);
                    setInterest.invoke(object, interest);

                    listOfObjects.add((Deposit) object);
                }
                catch (ClassNotFoundException e){
                    System.err.println("Deposit Type did not recognized!");
                }
                catch (ArithmeticException e){
                    System.err.println(e.getMessage());
                }
            }

            for (int j=0; j<listOfObjects.size(); j++) {
                BigDecimal max = listOfObjects.get(j).getInterest();
                int maxIndex = j;
                for (int i=j; i<listOfObjects.size(); i++) {
                    if (listOfObjects.get(i).getInterest().compareTo(max) > 0){
                        max = listOfObjects.get(i).getInterest();
                        maxIndex = i;
                    }
                }
                Deposit picked = listOfObjects.get(maxIndex);
                listOfObjects.remove(maxIndex);
                listOfObjects.add(j, picked);
            }

            for (int i=0; i<listOfObjects.size(); i++){
                System.out.println(listOfObjects.get(i).getCustomerNumber()+"#"+listOfObjects.get(i).getInterest());
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
