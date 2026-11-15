package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.*;

@Suite
@SelectClasses({
        NegativeRegistrationTest.class,
        NegativeLoginTest.class,
        NegativeMovieCRUDTest.class
})

public class NegativeTestSuite {
}
