package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.*;

@Suite
@SelectClasses({
    NoneLoginTest.class,
    UserTest.class,
    AdminTest.class,
    PositiveMovieCRUDTest.class
})

public class PositiveTestSuite {
}
