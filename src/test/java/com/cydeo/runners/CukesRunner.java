package com.cydeo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                //"pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",               //keeping track of failed scenarios
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",

        },
        //providing the location of our FEATURES package
        features = "src/test/resources/features",   // “features” --> Copy Path/Reference --> Path From Content Root

        //giving the address where we store our STEP_DEFINITION
        glue = "com/cydeo/step_definitions",        // "step_definitions" --> Copy Path/Reference --> Path From Source Root

        //true --> "I don't want to run my java selenium code. What I want is; if I have extra snippets, I just want to get them"
        //true --> I want to get snippets
        dryRun = false,         // false --> I want to run my java selenium code.

        tags = "",
        publish = true

)
public class CukesRunner {
}
