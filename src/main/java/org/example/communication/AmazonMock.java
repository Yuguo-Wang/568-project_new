package org.example.communication;

import java.io.IOException;
import org.example.protocol.*;
//public class AmazonMock {
//    public static void main(String[] args) throws IOException {
//        //UpsClient upsClient = new UpsClient("vcm-32430.vm.duke.edu", 7474);
//        UpsClient upsClient = new UpsClient("65.75.220.176", 55556);
//        System.out.println("socket with ups");
//        UpsAmazon.UAConnect uaConnect = upsClient.receiveUAConnect();
//        System.out.println(uaConnect);
//        // 1 connect
//        UpsAmazon.AUConnected.Builder builder = UpsAmazon.AUConnected.newBuilder();
//        builder.setWorldid(uaConnect.getWorldid()).setResult("success!");
//        upsClient.sendMessage(builder.build());
//        // 2 call truck
//        UpsAmazon.AUProduct.Builder auproductBuilder1 = UpsAmazon.AUProduct.newBuilder();
//        UpsAmazon.AUProduct.Builder auproductBuilder2 = UpsAmazon.AUProduct.newBuilder();
//        auproductBuilder1.setDescription("cool apple").setId(1).setCount(2).setDestX(11).setDestY(12).setUserid(1);
//        auproductBuilder2.setDescription("bad apple").setId(2).setCount(3).setDestX(11).setDestY(12).setUserid(1);
//        UpsAmazon.AUCallTruck.Builder auCallTruckBuilder = UpsAmazon.AUCallTruck.newBuilder();
//        auCallTruckBuilder.setSeqnum(1).setWhid(1).setWhX(1).setWhY(1).addThings(auproductBuilder1).addThings(auproductBuilder2);
//        UpsAmazon.AUCommands.Builder auCommand0 = UpsAmazon.AUCommands.newBuilder();
//        auCommand0.addCallTruck(auCallTruckBuilder.build());
//        upsClient.sendMessage(auCommand0.build());
//        UpsAmazon.UACommands uaCommands = upsClient.receiveUACommands();
//        System.out.println("receive call truck back uaCommand ack: " + uaCommands);
//        // 3 truck arrived
//        UpsAmazon.UACommands uaCommands1 = upsClient.receiveUACommands();
//        System.out.println("receive truck arrived: " + uaCommands1);
//        UpsAmazon.AUCommands.Builder auCommandsBuilder1 = UpsAmazon.AUCommands.newBuilder();
//        auCommandsBuilder1.addAcks(uaCommands1.get
//}
