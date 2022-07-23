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

        multiLevelCache.write(100,101);
        multiLevelCache.write(200,201);
        multiLevelCache.write(300,301);
        multiLevelCache.write(400,401);

        multiLevelCache.read(200);

        multiLevelCache.write(500,501);
        multiLevelCache.write(600,601);




    }
}
