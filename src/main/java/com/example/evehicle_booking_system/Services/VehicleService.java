package com.example.evehicle_booking_system.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evehicle_booking_system.Repository.VehicleRepository;
import com.example.evehicle_booking_system.UserModel.Vehicle;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vr;

    public List<Vehicle> getAllVehicle(){
        return vr.findAll();
    }

    // public Vehicle getByModelName(String modelName){

    //     Optional<Vehicle> opt = vr.findByModelName(modelName);
    //     Vehicle veh = opt.get();
    //     // return vr.findByModelName(modelName);
    //     return veh;
    // }

    public Vehicle getByVehicleId(Long vehicleId){

        Optional<Vehicle> upd = vr.findById(vehicleId);
        Vehicle updatedone = upd.get();

        return updatedone;
    }
    
    public void  saveinvehicle(Vehicle bike){
        vr.save(bike);
    }

    // @Transactional 
    // public void deletevianame(String modelName){
    //     vr.deleteByModelName(modelName);
    // }

    public void deleteviaid(Long vehicleId){
        vr.deleteById(vehicleId);
    }
}
