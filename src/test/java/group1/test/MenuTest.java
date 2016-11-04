package group1.test;

import group1.commons.Database;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class DatabaseTest {
    @Ignore
    @Test
    public void upload() {
        Database.save();
    }
}
