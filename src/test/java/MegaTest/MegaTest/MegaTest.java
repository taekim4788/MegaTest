package MegaTest.MegaTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features/megaTest.feature", 
		glue= {"StepDefinitions"}, 
		monochrome=true,
		plugin={"pretty",
				"json:target/cucumber-report-html/mega.json",
				"html:target/cucumber-report-html/mega.html"}
)

public class MegaTest {

}
