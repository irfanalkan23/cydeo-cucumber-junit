package com.cydeo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                //"pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",

        },
        //providing the location of our features package
        features = "src/test/resources/features",   // “features” --> Copy Path/Reference --> Path From Content Root

        //giving the address where we store our step_definition
        glue = "com/cydeo/step_definitions",        // "step_definitions" --> Copy Path/Reference --> Path From Source Root
        dryRun = false,         // true --> I want to get snippets
        tags = "",
        publish = false

)
public class CukesRunner {
}
