import Service.MultiLevelCache;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Multilevel Cache Implementation");

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);

        MultiLevelCache multiLevelCache = new MultiLevelCache(3, list);

        //Add on level 0
        multiLevelCache.write(100,101);
        //Add on level 0
        multiLevelCache.write(200,201);
        //300 is added on level 0 and 100 is added on level 1
        multiLevelCache.write(300,301);
        //400 is added on level 0 and 200 is added on level 1
        multiLevelCache.write(400,401);

        multiLevelCache.read(200);

        //500 is added on level 0 and 300 is added on level 1
        multiLevelCache.write(500,501);
        //600 is added on level 0 and 400 is added on level 1 and 100 is added to level 2
        multiLevelCache.write(600,601);

        multiLevelCache.delete(100);
        multiLevelCache.delete(400);


    }
}
