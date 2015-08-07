package com.ibm.testing;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by patrick on 8/6/2015.
 */

@RunWith(Cucumber.class)
@Cucumber.Options(
        features = "MockApp",
        format = {"json:target/integration_cucumber.json"},
        tags = {"@all"}//what tags to include(@)/exclude(@~)
)

public class LoginTest {
}
