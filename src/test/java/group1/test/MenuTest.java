package group1.test;

import group1.storage.Database;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Created by low on 4/11/16 12:55 PM.
 */
public class MenuTest {
    @Ignore
    @Test
    public void upload() {
        Database.flush();
    }
}
