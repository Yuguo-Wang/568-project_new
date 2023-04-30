package org.example.communication;


import org.apache.ibatis.session.SqlSession;
import org.example.Database.PackagesMapper;
import org.example.Database.TruckMapper;
import org.example.Database.UserMapper;
import org.example.Handler.ACommandHandler;
import org.example.Handler.SenderHandler;
import org.example.Handler.WCommandHandler;
import org.example.Utils.MyBatisUtil;
import org.example.protocol.UpsAmazon;
import org.example.protocol.WorldUps;
import org.example.controller.TruckController;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.example.Database.*;
import org.example.Utils.ConstUtils;
import org.example.Utils.MyBatisUtil;
import org.example.Utils.SeqUtils;
import org.example.controller.TruckController;
import org.example.protocol.*;
import org.example.protocol.WorldUps;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;


import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class UpsServer {
    public static long WORD_ID;

    public static UserClient userClient;
    public static AmazonClient amazonClient;
    public static WorldClient worldClient;

    public static TruckMapper truckMapper;
    public static UserMapper userMapper;
    public static PackagesMapper packagesMapper;
    public static SqlSession sqlSession;

    //uw
    public static Map<Long, WorldUps.UGoPickup> uGoPickupMap =  new ConcurrentHashMap<>();
    public static Map<Long, WorldUps.UGoDeliver> uGoDeliverMap = new ConcurrentHashMap<>();
    public static Map<Long, WorldUps.UQuery> uQueryMap = new ConcurrentHashMap<>();
    public static Set<Long> uwACKSet = new CopyOnWriteArraySet<>();
    //ua
    public static Map<Long, UpsAmazon.UATruckArrived> uaTruckArrivedMap = new ConcurrentHashMap<>();
    public static Set<Long> uaACKSet = new CopyOnWriteArraySet<>();
    public static Map<Long, UpsAmazon.UAUpdatePackageStatus> uaUpdatePackageStatusMap =  new ConcurrentHashMap<>();
    public static Map<Long, UpsAmazon.UATruckDeliverMade> uaTruckDeliverMadeMap = new ConcurrentHashMap<>();

    public static Map<Long, Set<Long>> packageStatusMap = new ConcurrentHashMap<>();

    public UpsServer(String toWorldHost, int toWorldPortNum, int myPortNum) throws IOException {
        //database init
        initUpsDatabase();
        System.out.println("database initialed");
        //communication init
        //userClient = new UserClient();
        //amazonClient = new AmazonClient(myPortNum);ç

        //get mapper
        sqlSession = MyBatisUtil.getSqlSession();
        packagesMapper = sqlSession.getMapper(PackagesMapper.class);
        truckMapper = sqlSession.getMapper(TruckMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);


        TruckController truckController = new TruckController();
        List<WorldUps.UInitTruck> initTrucks = truckController.initTrucks();
        worldClient = new WorldClient(toWorldHost, toWorldPortNum);
        WorldUps.UConnect.Builder builder = WorldUps.UConnect.newBuilder();
        builder.setIsAmazon(false);
        builder.addAllTrucks(initTrucks);
        for (WorldUps.UInitTruck truck : initTrucks){
            truckMapper.insertTruck(new Truck(truck.getId(), ConstUtils.TRUCK_IDLE_STATUS, 0));
        }
        sqlSession.commit();
       //builder.addAllTrucks(initTrucks);
        try {
            System.out.println("world " + worldClient.getSocket());
            worldClient.writeToWorld(builder.build());
            System.out.println("give world connect .....");
            System.out.println(builder.build());
            //connected
            WorldUps.UConnected uConnected = worldClient.readFromWorldConnected();
            String result = uConnected.getResult();
            if (! result.equals("connected!"))
                throw new IllegalArgumentException(result);
            System.out.println(result);
            WORD_ID = uConnected.getWorldid();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Uconnect error : " + e);
        }

        amazonClient = new AmazonClient(myPortNum);

        //get mapper
        // sqlSession = MyBatisUtil.getSqlSession();
        // packagesMapper = sqlSession.getMapper(PackagesMapper.class);
        // truckMapper = sqlSession.getMapper(TruckMapper.class);
        // userMapper = sqlSession.getMapper(UserMapper.class);
    }

    private static void initUpsDatabase(){
        try {
            MyBatisUtil.dropAllTables();
            MyBatisUtil.createAllTables();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(String toWorldHost, int toWorldPortNum, int myPortNum) throws IOException {
        // worldClient = new WorldClient(toWorldHost, toWorldPortNum);
        // amazonClient = new AmazonClient(myPortNum);
        WCommandHandler wCommandHandler = new WCommandHandler();
        Thread thread0 = new Thread(wCommandHandler);
        thread0.start();
        ACommandHandler aCommandHandler = new ACommandHandler();
        Thread thread1 = new Thread(aCommandHandler);
        thread1.start();
       //todo: user
        SenderHandler senderHandler = new SenderHandler();
        Thread thread3 = new Thread(senderHandler);
        thread3.start();
    }




}
