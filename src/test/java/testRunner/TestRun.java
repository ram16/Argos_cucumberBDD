package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features={".//features/"},
                //features="@target/rerun.txt",
                glue="stepDefinitions",
                dryRun=false,
                tags="@sanity" ,//scenarios taged with @sanity
                //tags="@sanity and @regression"  //Scenarios tagged with both @sanity and @regression
                //tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression
                //tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
                monochrome = true,
                plugin= {"pretty",
                        "html:reports/myreport.html",
                        "rerun:target/rerun.txt",    //Mandatory to capture failures
                       },

                       publish=true
        )

public class TestRun {
}
