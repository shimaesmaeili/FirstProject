import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Implementation {
    public static void main(String[] args){
        List<Deposit> listOfObjects = new ArrayList<Deposit>();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("sample.xml");

            NodeList nodeList = document.getElementsByTagName("deposit");

            for (int i=0; i<nodeList.getLength(); i++) {
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

            Collections.sort(listOfObjects);

            for (Deposit d : listOfObjects)
                System.out.println(d.getCustomerNumber()+"#"+d.getInterest());
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
