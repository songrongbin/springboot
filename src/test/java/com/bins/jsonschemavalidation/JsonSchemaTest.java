package com.bins.jsonschemavalidation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by songrongbin on 2016/4/15.
 * valide json schema
 */
public class JsonSchemaTest {
    @Test
    public void validate_driverSet_schema() {
        JsonNode schema = readJSONfile("src/test/resources/json/Schema.json");
        JsonNode data = readJSONfile("src/test/resources/json/DataGoodExample.json");
        ProcessingReport report =
                JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);
        System.out.println("result:" + report.isSuccess());
        Assert.assertTrue(report.isSuccess());
    }

    @Test
    // wrong data
    public void validate_driverSet_schema2() {
        JsonNode data = readJSONfile("src/test/resources/json/DataBadExample.json");
        JsonNode schema = readJSONfile("src/test/resources/json/Schema.json");
        ProcessingReport report =
                JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);
        //Assert.assertFalse(report.isSuccess());

        // assert error message
        Iterator<ProcessingMessage> it = report.iterator();
        System.out.println("result:" + report.isSuccess());
        for (ProcessingMessage processingMessage : report) {
            System.out.println("message:" + processingMessage.getMessage());
        }

        Assert.assertEquals(
                "instance type (string) does not match any allowed primitive type (allowed: [\"integer\"])",
                it.next().getMessage());

    }

    private JsonNode readJSONfile(String filePath) {
        JsonNode instance = null;
        try {
            instance = new JsonNodeReader().fromReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
