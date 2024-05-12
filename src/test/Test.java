package test;


import com.sian.javaprojecttemplates.restvue.common.JDBC;
import com.sian.javaprojecttemplates.restvue.model.entity.Person;
import com.sian.javaprojecttemplates.restvue.model.service.PersonService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.Configurer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    private final static Logger logger =  LogManager.getLogger(Test.class);
    public static void main(String[] args) throws Exception {

        Person person = new Person()
                .setName("ViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolationViolation")
                .setFamily("dd");

        Validator validator = new Validator();

        List<ConstraintViolation> violationList = validator.validate(person);

        StringJoiner joiner = new StringJoiner("\n");
        for (ConstraintViolation constraintViolation : violationList) {
            String s = extractModifiedErrorMessage("constraintViolationgetMessage()");
            System.out.println(s);
        }



    }

    private static String extractModifiedErrorMessage(String errorMessage) {
        // Find the index of the last dot
        int lastDotIndex = errorMessage.lastIndexOf('.');

        // Find the index of the second-to-last dot
        int secondLastDotIndex = errorMessage.lastIndexOf('.', lastDotIndex - 1);

        // Extract the substring after the second-to-last dot
        String modifiedMessage = errorMessage.substring(secondLastDotIndex + 1);

        // Replace the first dot with a space
        modifiedMessage = modifiedMessage.replaceFirst("\\.", " ");

        return modifiedMessage;
    }
}
