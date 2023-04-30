package org.example.Handler;

import org.example.protocol.UpsAmazon;
import org.example.protocol.WorldUps;
import org.example.protocol.UpsAmazon;

import java.io.IOException;

import static org.example.communication.UpsServer.*;

public class SenderHandler implements Runnable{
    @Override
    public  void run(){
        //一直发送
  
        while (true){
            WorldUps.UCommands.Builder uCommandsBuilder = WorldUps.UCommands.newBuilder();
            UpsAmazon.UACommands.Builder uACommandsBuilder = UpsAmazon.UACommands.newBuilder();
            try{
            //uw
                if (!uwACKSet.isEmpty() || !uGoDeliverMap.isEmpty() || !uGoPickupMap.isEmpty() || !uQueryMap.isEmpty()){
                    uCommandsBuilder.addAllAcks(uwACKSet);
                    uwACKSet.clear();
                    uCommandsBuilder.addAllDeliveries(uGoDeliverMap.values());
                    uCommandsBuilder.addAllPickups(uGoPickupMap.values());
                    uCommandsBuilder.addAllQueries(uQueryMap.values());
                    worldClient.writeToWorld(uCommandsBuilder.build());
                    System.out.println("sending to world");
                    System.out.println(uCommandsBuilder.build().toString());
                 
                }
                if (!uaACKSet.isEmpty() || !uaUpdatePackageStatusMap.isEmpty() || !uaTruckArrivedMap.isEmpty()
                || !uaTruckDeliverMadeMap.isEmpty()){
                 //ua
                    uACommandsBuilder.addAllAcks(uaACKSet);
                    uaACKSet.clear();
                    uACommandsBuilder.addAllUpdatePackageStatus(uaUpdatePackageStatusMap.values());
                    uACommandsBuilder.addAllTruckArrived(uaTruckArrivedMap.values());
                    uACommandsBuilder.addAllDelivered(uaTruckDeliverMadeMap.values());
                    amazonClient.writeToAmz(uACommandsBuilder.build());
                    System.out.println("sending to amz");
                    System.out.println(uACommandsBuilder.build().toString());
                    
                }
                Thread.sleep(5000);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("send message error :" + e);
            }
        }
    }
}
