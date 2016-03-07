package FileCrawler;

import static org.junit.Assert.*;
import java.io.File;
import java.util.Collection;
import static org.junit.Assert.*;

public class Test {
    
    public Test() {
    }

    public void testAddTree() {
        System.out.println("addTree");
        File file = null;
        Collection<File> all = null;
        FileCrawler.addTree(file, all);        
    }
    
}
