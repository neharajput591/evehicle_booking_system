package com.example.evehicle_booking_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.evehicle_booking_system.Repository.VehicleRepository;
import com.example.evehicle_booking_system.Services.AdminService;
import com.example.evehicle_booking_system.Services.FeedbackService;
import com.example.evehicle_booking_system.Services.UserService;
import com.example.evehicle_booking_system.Services.VehicleService;
import com.example.evehicle_booking_system.UserModel.Vehicle;

// import ch.qos.logback.core.model.Model;

@Controller
public class AdminController {

    @Autowired
    VehicleRepository vr;

    @Autowired
    UserService us;

    @Autowired
    AdminService as;

    @Autowired
    VehicleService vs;

    @Autowired
    FeedbackService fs;

    @GetMapping("/Admin/dashboard")
    public String admindash(Model model){

        // model.addAttribute("newone", new Vehicle());

        // model.addAttribute("newone" , new Vehicle());
        String r = "ROLE_USER";

        model.addAttribute("vehicles" , vr.findAll());
         
        

        model.addAttribute("users", as.finduser(r));
        model.addAttribute("newone",new Vehicle());
        model.addAttribute("feedbacks", fs.findfeedbacks());
        model.addAttribute("totalVehicles" , as.getTotalVehicles());
        model.addAttribute("totalUsers" , as.getTotalUsers());
        model.addAttribute("noOfFeedback" , as.getTotalFeedback());
        model.addAttribute("totalOrders" , as.getTotalOrders());

        return "AdminDashboard";
    }

    @PostMapping("/Admin/addnewvehicle")
    public String added(@ModelAttribute("newone") Vehicle newone , Model model, @RequestParam(value = "imageUrl", required = false) String imageUrl){

        if (imageUrl != null && !imageUrl.isEmpty()) {
            newone.setImageUrl(imageUrl);
        }
        vr.save(newone);

        return "AdminDashboard";
    }

    // @GetMapping("/Admin/UpdateVehicleData/{modelName}")
    // public String update(@PathVariable("modelName") String modelName ,Model model){

    //     Vehicle bike = vs.getByModelName(modelName);
    //     model.addAttribute("bike",bike);

    //     return "UpdateVehicledetails";
    // }

    @GetMapping("/Admin/Updatebyid/{vehicleId}")
    public String updateviaid(@PathVariable("vehicleId") Long vehicleId , Model model){

        Vehicle veh = vs.getByVehicleId(vehicleId);
        model.addAttribute("veh" , veh);
        return "UpdateVehicle";
    }

    // @PostMapping("/Admin/updateddata")
    // public String updatevehicle(@ModelAttribute("bike") Vehicle bike, Model model){

    //     vs.saveinvehicle(bike);

    //        String r = "ROLE_USER";

    //     model.addAttribute("vehicles" , vr.findAll());
    //     model.addAttribute("users", as.finduser(r));
    //     model.addAttribute("newone",new Vehicle());
    //     // return "AdminDashboard";
    //     return "redirect:/Admin/dashboard";

    // }

    @PostMapping("/Admin/updateddata1")
     public String updatevehicle1(@ModelAttribute("veh") Vehicle bike, Model model){

        vs.saveinvehicle(bike);

           String r = "ROLE_USER";

        model.addAttribute("vehicles" , vr.findAll());
        model.addAttribute("users", as.finduser(r));
        model.addAttribute("newone",new Vehicle());
        // return "AdminDashboard";
        return "redirect:/Admin/dashboard";

    }

    // @GetMapping("/Admin/DeleteVehicle/{modelName}")
    // public String deletevehicle(@PathVariable("modelName") String modelName , Model model){
    //     vs.deletevianame(modelName);
    //        String r = "ROLE_USER";

    //     model.addAttribute("vehicles" , vr.findAll());
    //     model.addAttribute("users", as.finduser(r));
    //     model.addAttribute("newone",new Vehicle());

    //     return "AdminDashboard";
    // }

    @GetMapping("/Admin/DeleteVehiclebyid/{vehicleId}")
    public String deleteviaid(@PathVariable("vehicleId") Long vehicleId , Model model){
        vs.deleteviaid(vehicleId);

            String r = "ROLE_USER";

        model.addAttribute("vehicles" , vr.findAll());
        model.addAttribute("users", as.finduser(r));
        model.addAttribute("newone",new Vehicle());

        return "AdminDashboard";

    }
    
}
