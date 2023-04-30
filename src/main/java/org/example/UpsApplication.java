package org.example;

import org.example.communication.UpsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.ibatis.session.SqlSession;
import org.example.Database.*;
import org.example.Utils.ConstUtils;
import org.example.Utils.MyBatisUtil;
import org.example.Database.PackagesMapper;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class UpsApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(UpsApplication.class, args);
        final String toWorldHost = "vcm-30598.vm.duke.edu";
        final int toWorldPortNum = 12345;

        final int myPortNum = 7474;

        UpsServer upsServer = new UpsServer(toWorldHost, toWorldPortNum, myPortNum);
        upsServer.run(toWorldHost, toWorldPortNum, myPortNum);
        }

//        final String toWorldHost = "vcm-30970.vm.duke.edu";
//        final int toWorldPortNum = 12345;
//
//        final int myPortNum = 57145;
//
//
//
//        try {
//            UpsServer upsServer = new UpsServer(toWorldHost, toWorldPortNum, myPortNum);
//            upsServer.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
