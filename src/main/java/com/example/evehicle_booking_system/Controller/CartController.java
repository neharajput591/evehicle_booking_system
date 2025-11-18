package com.example.evehicle_booking_system.Controller;

// import org.apache.catalina.User;
// import com.example.demo.model.CartItem;
// import com.example.demo.model.User;
// import com.example.demo.repository.CartItemRepository;
// import com.example.demo.repository.UserRepository;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.evehicle_booking_system.Repository.CartItemRepository;
import com.example.evehicle_booking_system.Repository.PaymentRepository;
import com.example.evehicle_booking_system.Repository.UserRepository;
import com.example.evehicle_booking_system.Repository.VehicleRepository;
import com.example.evehicle_booking_system.UserModel.CartItem;
import com.example.evehicle_booking_system.UserModel.Payment;
import com.example.evehicle_booking_system.UserModel.User;

// @RestController
// @RequestMapping("/api/cart")
// @CrossOrigin(origins = "http://localhost:5500")  // adjust to your frontend port
@Controller
public class CartController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    PaymentRepository paymentrepo;

    // ✅ Add to cart for logged-in user

    @ResponseBody
    @PostMapping("/User/addtocart")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem, Principal principal) {

        // 1️⃣ Check if user is logged in
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please LOGIN to add an item to your cart");
        }

        // 2️⃣ Fetch logged-in user by email
        String email = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not found. Please login again.");
        }

        User user = optionalUser.get();

        // Debug
        System.out.println("Logged-in User: " + user.getEmail() + " | ID = " + user.getUserId());

        // 3️⃣ Validate vehicle ID coming from frontend
        if (cartItem.getVehicle() == null || cartItem.getVehicle().getVehicleId() == null) {
            return ResponseEntity.badRequest().body("Vehicle ID missing in request!");
        }

        Long vehicleId = cartItem.getVehicle().getVehicleId();

        // 4️⃣ Fetch vehicle from DB
        var vehicleOpt = vehicleRepository.findById(vehicleId);

        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid vehicle ID!");
        }

        // Link user and vehicle properly
        cartItem.setUser(user);
        cartItem.setVehicle(vehicleOpt.get());

        // 5️⃣ Save cart item
        cartItemRepository.save(cartItem);

        return ResponseEntity.ok("Item added to cart for user: " + user.getName());
    }

    // @ResponseBody
    // @PostMapping("/User/addtocart")
    // public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem,
    // Principal principal) {
    // if (principal == null) {
    // System.out.println("Principal is NULL. User is not authenticated!");
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    // .body("Please LOGIN TO ADD A ITEM TO YOUR CART");
    // } else {
    // System.out.println("Principal is present: " + principal.getName());
    // }

    // String email = principal.getName();
    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // if (optionalUser.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    // }

    // User user = optionalUser.get();
    // cartItem.setUser(user);
    // cartItemRepository.save(cartItem);
    // return ResponseEntity.ok("Item added to cart for user: " + user.getName());
    // }

    // @RestController
    @PostMapping("/User/payment")
    public ResponseEntity<String> paymentinprocess(@RequestBody Payment paymentitem, Principal principal, Model model) {

        String modelname = paymentitem.getModelname();
        if (principal == null) {
            // return ("Unauthorised access");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please LOGIN TO ADD A ITEM TO YOUR CART");

        }

        String email = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // return ("User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = optionalUser.get();
        paymentitem.setUserpayment(user);
        paymentrepo.save(paymentitem);
        return ResponseEntity.ok("Item added to cart for user: " + user.getName());

        // model.addAttribute("paymentdetails",
        // paymentrepo.findByModelname("modelname"));

        // return "payment";
    }

    // @PostMapping("/User/addtocart")
    // public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem,
    // Principal principal) {
    // String email = principal.getName();

    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // if (optionalUser.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login
    // first");
    // }

    // User user = optionalUser.get();

    // // ✅ Extract the vehicle ID from request JSON (manually)
    // Long vehicleId = null;
    // if (cartItem.getVehicle() != null) {
    // vehicleId = cartItem.getVehicle().getVehicleId();
    // }

    // if (vehicleId == null) {
    // return ResponseEntity.badRequest().body("Vehicle ID is missing in request");
    // }

    // // ✅ Fetch the actual Vehicle entity
    // Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
    // if (vehicleOpt.isEmpty()) {
    // return ResponseEntity.badRequest().body("Invalid vehicle ID");
    // }

    // ✅ Link both entities
    // cartItem.setUser(user);
    // cartItem.setVehicle(vehicleOpt.get());

    // cartItemRepository.save(cartItem);
    // return ResponseEntity.ok("Item added to cart for " + user.getName());
    // }

    // @RestController
    // @RequestMapping("/User")
    // public class CartController {

    // @Autowired
    // private CartItemRepository cartItemRepository;
    // @Autowired
    // private UserRepository userRepository;

    // @PostMapping("/addtocart")
    // public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem,
    // Principal principal) {
    // if (principal == null) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    // .body("Please LOGIN TO ADD A ITEM TO YOUR CART");
    // }

    // String email = principal.getName();
    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // if (optionalUser.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    // }

    // User user = optionalUser.get();
    // cartItem.setUser(user);
    // cartItemRepository.save(cartItem);
    // return ResponseEntity.ok("Item added to cart for user: " + user.getName());
    // }
    // }

    // // ✅ View cart items for logged-in user
    // @GetMapping("/User/cart")
    // public String getMyCart(Principal principal) {
    // String email = principal.getName();

    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // if (optionalUser.isEmpty()) {
    // return "unauthorised";
    // // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    // }

    // User user = optionalUser.get();
    // List<CartItem> items = cartItemRepository.findByUser(user);

    // // return ResponseEntity.ok(items);
    // return "mycart";
    // }
}
