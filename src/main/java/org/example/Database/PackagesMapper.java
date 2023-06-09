package org.example.Database;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PackagesMapper {

    void createPackagesTable();

    void dropPackagesTable();

//    @Select("SELECT * FROM package WHERE packageId = #{packageId}")
    Packages getPackageById(Integer packageId);

//    @Select("SELECT * FROM package")
    List<Packages> getAllPackages();

//    @Insert("INSERT INTO package (truckId, userId, itemNum) VALUES (#{truckId}, #{userId}, #{itemNum})")
    @Options(useGeneratedKeys = true, keyProperty = "packageId")
    void insertPackage(Packages pkg);

//    @Update("UPDATE package SET truckId = #{truckId}, userId = #{userId}, itemNum = #{itemNum} WHERE packageId = #{packageId}")
    void updatePackage(Packages pkg);

    void updatePackageTruckId (Integer packageId, Integer truckId);

//    @Delete("DELETE FROM package WHERE packageId = #{packageId}")
    void deletePackage(Integer packageId);

    List<Packages> getPackagesByTruckId(int truckId);

    void updatePackageStatusByTruckId(@Param("status") String status, @Param("truckId") Integer truckId);

    
}
