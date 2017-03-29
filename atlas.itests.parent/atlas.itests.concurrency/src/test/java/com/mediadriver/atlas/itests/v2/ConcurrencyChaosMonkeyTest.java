package com.mediadriver.atlas.itests.v2;

import com.mediadriver.atlas.api.v2.AtlasContext;
import com.mediadriver.atlas.api.v2.AtlasContextFactory;
import com.mediadriver.atlas.api.v2.AtlasSession;
import com.mediadriver.atlas.core.v2.DefaultAtlasContextFactory;
import com.mediadriver.atlas.java.v2.JavaField;
import com.mediadriver.atlas.v2.AtlasMapping;
import com.mediadriver.atlas.v2.AtlasModelFactory;
import com.mediadriver.atlas.v2.FieldType;
import com.mediadriver.atlas.v2.MapAction;
import com.mediadriver.atlas.v2.MapFieldMapping;
import com.mediadriver.atlas.v2.MappedField;
import com.mediadriver.atlas.v2.SeparateFieldMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 */
@Ignore(value = "Integration Test")
public class ConcurrencyChaosMonkeyTest {

    private AtlasContextFactory atlasContextFactory = null;
    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyChaosMonkeyTest.class);

    @Before
    public void setUp() {
        atlasContextFactory = DefaultAtlasContextFactory.getInstance();
    }

    @After
    public void tearDown() {
        atlasContextFactory = null;
    }

    //    one thread, many contexts
    @Test
    public void chaosMonkeyTest_ManyContexts() throws Exception {
        long startTime = System.nanoTime();

        AtlasMapping mapping1 = generateMapping();
        Status twitterStatus = generateTwitterStatus();
        for (int i = 0; i < 256; i++) {

            Thread chaosMonkeyThread = new Thread("ChaosMonkeyThread-" + i) {
                public void run() {
                    logger.info(this.getName() + " starting.");

                    for (int j = 0; j < 100000; j++) {

                        try {
                            AtlasContext context = atlasContextFactory.createContext(mapping1);
                            AtlasSession session = context.createSession();
                            session.setInput(twitterStatus);
                            context.process(session);

                            Random rand = new Random(System.currentTimeMillis() % 13);
                            int randSleep;
                            randSleep = rand.nextInt(20000);
                            Thread.sleep(randSleep);

                            context.process(session);

                            Thread.sleep(randSleep);
                        } catch (Throwable e) {
                            logger.error("ERROR", e);
                        }
                    }

                    logger.info(this.getName() + " thread completed.");
                }
            };

            chaosMonkeyThread.start();
        }

        Thread.sleep(600000L);
        long difference = System.nanoTime() - startTime;

        logger.info(String.format("Total time: %d minutes to process 100000 mappings with one context per execution", TimeUnit.NANOSECONDS.toMinutes(difference)));

    }


    //    many threads, one context
    @Test
    public void chaosMonkeyTest_ManyThreads() throws Exception {
        long startTime = System.nanoTime();

        AtlasMapping mapping1 = generateMapping();
        Status twitterStatus = generateTwitterStatus();
        AtlasContext context = atlasContextFactory.createContext(mapping1);
        for (int i = 0; i < 256; i++) {
            Thread chaosMonkeyThread = new Thread("ChaosMonkeyThread-" + i) {
                public void run() {
                    logger.info(this.getName() + " starting.");
                    for (int j = 0; j < 100000; j++) {

                        try {

                            AtlasSession session = context.createSession();
                            session.setInput(twitterStatus);
                            context.process(session);

                            Random rand = new Random(System.currentTimeMillis() % 13);
                            int randSleep;
                            randSleep = rand.nextInt(20000);
                            Thread.sleep(randSleep);

                            context.process(session);

                            Thread.sleep(randSleep);
                        } catch (Throwable e) {
                            logger.error("ERROR", e);
                        }
                    }

                    logger.info(this.getName() + " thread completed.");
                }
            };

            chaosMonkeyThread.start();
        }

        Thread.sleep(600000L);
        long difference = System.nanoTime() - startTime;

        logger.info(String.format("Total time: %d minutes to process 100000 mappings with one context shared with 256 threads", TimeUnit.NANOSECONDS.toMinutes(difference)));

    }


    protected AtlasMapping generateMapping() throws Exception {
        AtlasMapping mapping = AtlasModelFactory.createAtlasMapping();

        mapping.setName("mockMapping");
        mapping.setSourceUri("atlas:java?className=twitter4j.Status");
        mapping.setTargetUri("atlas:java?className=org.apache.camel.salesforce.dto.Contact");

        SeparateFieldMapping sepMapping = AtlasModelFactory.createFieldMapping(SeparateFieldMapping.class);
        MappedField nameField = AtlasModelFactory.createMappedField();
        JavaField jNameField = new JavaField();
        jNameField.setName("Name");
        jNameField.setPath("User.name");
        jNameField.setGetMethod("getName");
        jNameField.setType(FieldType.STRING);
        nameField.setField(jNameField);

        MappedField fnField = AtlasModelFactory.createMappedField();
        JavaField jFirstNameField = new JavaField();
        jFirstNameField.setName("FirstName");
        jFirstNameField.setPath("FirstName");
        jFirstNameField.setSetMethod("setFirstName");
        jFirstNameField.setType(FieldType.STRING);
        fnField.setField(jFirstNameField);

        MapAction fnAction = new MapAction();
        fnAction.setIndex(0);
        fnField.getFieldActions().getFieldAction().add(fnAction);

        MappedField lnField = AtlasModelFactory.createMappedField();
        JavaField jLastNameField = new JavaField();
        jLastNameField.setName("LastName");
        jLastNameField.setPath("LastName");
        jLastNameField.setSetMethod("setLastName");
        jLastNameField.setType(FieldType.STRING);
        lnField.setField(jLastNameField);
        MapAction lnAction = new MapAction();
        lnAction.setIndex(1);
        lnField.getFieldActions().getFieldAction().add(lnAction);

        sepMapping.setInputField(nameField);
        sepMapping.getOutputFields().getMappedField().add(fnField);
        sepMapping.getOutputFields().getMappedField().add(lnField);
        mapping.getFieldMappings().getFieldMapping().add(sepMapping);

        MapFieldMapping textDescMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        MappedField textField = AtlasModelFactory.createMappedField();
        JavaField jTextField = new JavaField();
        jTextField.setName("Text");
        jTextField.setPath("Text");
        jTextField.setGetMethod("getText");
        jTextField.setType(FieldType.STRING);
        textField.setField(jTextField);

        MappedField descField = AtlasModelFactory.createMappedField();
        JavaField jDescField = new JavaField();
        jDescField.setName("Description");
        jDescField.setPath("description");
        jDescField.setSetMethod("setDescription");
        jDescField.setType(FieldType.STRING);
        descField.setField(jDescField);

        textDescMapping.setInputField(textField);
        textDescMapping.setOutputField(descField);
        mapping.getFieldMappings().getFieldMapping().add(textDescMapping);

        MapFieldMapping screenTitleMapping = AtlasModelFactory.createFieldMapping(MapFieldMapping.class);
        MappedField screenField = AtlasModelFactory.createMappedField();
        JavaField jScreenField = new JavaField();
        jScreenField.setName("ScreenName");
        jScreenField.setPath("User.screenName");
        jScreenField.setGetMethod("getScreenName");
        jScreenField.setType(FieldType.STRING);
        screenField.setField(jScreenField);

        MappedField titleField = AtlasModelFactory.createMappedField();
        JavaField jTitleField = new JavaField();
        jTitleField.setName("Title");
        jTitleField.setPath("Title");
        jTitleField.setSetMethod("setTitle");
        jTitleField.setType(FieldType.STRING);
        titleField.setField(jTitleField);

        screenTitleMapping.setInputField(screenField);
        screenTitleMapping.setOutputField(titleField);
        mapping.getFieldMappings().getFieldMapping().add(screenTitleMapping);

        return mapping;
    }

    protected Status generateTwitterStatus() {
        MockStatus status = new MockStatus();
        MockUser user = new MockUser();
        user.setName("Bob Vila");
        user.setScreenName("bobvila1982");
        status.setUser(user);
        status.setText("Let's build a house!");
        return status;
    }
}
