package org.example.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.example.Database.PackagesMapper;
import org.example.Entity.PackageInfo;
import org.example.Entity.PackageInfoList;
import org.example.Utils.MyBatisUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class UserController {


//    @GetMapping("query/1/{packageId}")
//    public ResponseEntity<PackageInfoList> getPackageById(@PathVariable String packageId) {
//        System.out.println(packageId);
//        // 在实际应用中，您需要查询数据库或其他数据源获取订单信息
////        String orderInfo = String.format("hello");
//        Package myPackage = queryPackageById(Long.parseLong(packageId));
//        PackageInfo packageInfo = new PackageInfo(
//                myPackage.getPackageId(),
//                myPackage.getDescription(),
//                myPackage.getItemNum(),
//                myPackage.getDestX(),
//                myPackage.getDestY(),
//                myPackage.getUserId()
//        );
//        PackageInfoList pkgInfoList = new PackageInfoList();
//        pkgInfoList.add(packageInfo);
//
//        return ResponseEntity.ok(pkgInfoList);
//    }
//
//    @GetMapping("query/2/{userId}")
//    public ResponseEntity<PackageInfoList> getPackageByUserId(@PathVariable String userId) {
//        System.out.println(userId);
//        // 在实际应用中，您需要查询数据库或其他数据源获取订单信息
////        String orderInfo = String.format("hello");
//        service.UserService userService = new UserService();
//        List<Package> myPackages = userService.queryPackageByUserId(Integer.parseInt(userId));
//        PackageInfoList pkgInfoList = new PackageInfoList();
//        for (Package pkg : myPackages) {
//            PackageInfo packageInfo = new PackageInfo(
//                    pkg.getPackageId(),
//                    pkg.getDescription(),
//                    pkg.getItemNum(),
//                    pkg.getDestX(),
//                    pkg.getDestY(),
//                    pkg.getUserId()
//            );
//            pkgInfoList.add(packageInfo);
//        }
//        return ResponseEntity.ok(pkgInfoList);
//    }
//
//    //use
//    Package queryPackageById(long packageId){
//        SqlSession sqlSession = MyBatisUtil.getSqlSession();
//        PackagesMapper packagesMapper = sqlSession.getMapper(PackagesMapper.class);
//
//    }
}