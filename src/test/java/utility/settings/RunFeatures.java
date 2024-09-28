package utility.settings;
 import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
 import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json", retryCount = 0, detailedReport = true, detailedAggregatedReport = true, overviewReport = true, toPDF = true, outputFolder = "target")

@CucumberOptions(
		
		features = {"src/main/resources/webapp"},
		glue= {"classpath:Webapp/"},
		
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html","json:target/cucumber-reports/Cucumber.json"})
		//tags={})
	public class RunFeatures {
			
	
	}

