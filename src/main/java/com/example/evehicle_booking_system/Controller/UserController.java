package com.example.evehicle_booking_system.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.evehicle_booking_system.Repository.CartItemRepository;
import com.example.evehicle_booking_system.Repository.FeedbackRepository;
import com.example.evehicle_booking_system.Repository.OrderRepository;
import com.example.evehicle_booking_system.Repository.PaymentRepository;
import com.example.evehicle_booking_system.Repository.UserRepository;
import com.example.evehicle_booking_system.Services.PaymentService;
import com.example.evehicle_booking_system.Services.VehicleService;
import com.example.evehicle_booking_system.UserModel.CartItem;
import com.example.evehicle_booking_system.UserModel.Feedback;
import com.example.evehicle_booking_system.UserModel.Payment;
import com.example.evehicle_booking_system.UserModel.User;

@Controller
public class UserController {

    @Autowired
    VehicleService vs;

    @Autowired
    FeedbackRepository feedrepo;

    @Autowired
    PaymentService paymentservice;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentrepo;

    // @Autowired
    // CartService cs;

    @GetMapping("/User/vehicle")
    public String showvehicle(Model model) {

        model.addAttribute("cartdetail", new CartItem());
        model.addAttribute("vehicles", vs.getAllVehicle());

        // @GetMapping("/vehicles")
        // public String showVehicles(Model model) {
        // List<Vehicle> list = vr.findAll();
        // if (list == null) {
        // list = new ArrayList<>();
        // }
        // model.addAttribute("vehicles", list);
        return "Vehicle";
    }

    @GetMapping("/User/homelogin")
    public String homepage(Model model) {

        return "user_home";
    }

    // @GetMapping("/User/cart")
    // public String mycart(Model model){

    // model.addAttribute("cartitems" , cs.getAllCartItem());

    // return "mycart";
    // }

    // ✅ View cart items for logged-in user
    @GetMapping("/User/cart")
    public String getMyCart(Principal principal, Model model) {
        String email = principal.getName();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "unauthorised";
            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();
        List<CartItem> items = cartItemRepository.findByUser(user);

        model.addAttribute("items", items);

        // return ResponseEntity.ok(items);
        return "mycart";
    }

    @GetMapping("/User/removecartitem/{id}")
    public String removeCartItem(@PathVariable long id, Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        CartItem item = cartItemRepository.findById(id).orElse(null);

        if (item != null && item.getUser().getUserId() == user.getUserId()) {
            cartItemRepository.delete(item);
        }

        model.addAttribute("items", item);
        return "user_home";
    }

    // @GetMapping("/User/removecartitem")
    // public String remove(Model model){
    // return "mycart";
    // }

    @GetMapping("/User/orders")
    public String vieworders(Model model, Principal principal) {
        // String Orders
        String email = principal.getName();

        // Integer userid = principal.get

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User u = optionalUser.get();

        Long userid = u.getUserId();

        List<Payment> orders = paymentservice.getOrderByUserId(userid);
        // Integer userid = optionalUser.getUserId();

        // Payment orders = paymentrepo.findById(userid)
        // .orElse(null);

        model.addAttribute("orders", orders);

        model.addAttribute("feed", new Feedback());

        // Optional<Payment> orders = paymentrepo.findById(u.getUserId());

        return "Order1";
    }

    @PostMapping("/User/feedback")
    public String feedbackveh(@ModelAttribute("feed") Feedback feed, Model model, Principal principal) {

        String email = principal.getName();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();

        feed.setUseridfeed(user.getUserId());

        // setUser_idfeed(user_idfeed);
        feedrepo.save(feed);

        // System.out.println("Feedback is submitted successfully");

        return "user_home";

    }

    // @GetMapping("/User/payfororder/{modelname}")
    // public String payorder(@PathVariable(value = "modelname") String modelname,
    // Model model, Principal principal) {

    // String email = principal.getName();

    // Integer userid = principal.get

    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // User u = optionalUser.get();
    // Integer userid = optionalUser.getUserId();

    // model.addAttribute("optionalUser", optionalUser);
    // System.out.println(u.getUserId());

    // Optional<Payment> paying = paymentrepo.findByModelname(modelname);

    // model.addAttribute("paying", paying);

    // Optional<Payment> paying = paymentrepo.findByModelnameAndUser_id(modelname,
    // u.getUserId());

    // Optional<Payment> paying = paymentrepo.findById(id);

    // return "payment";
    // }

    // @GetMapping("/User/orders")
    // public String or

    // @RestController
    // @PostMapping("/User/payment")
    // public String paymentinprocess(@RequestBody Payment paymentitem, Principal
    // principal, Model model) {

    // String modelname = paymentitem.getModelname();
    // if (principal == null) {
    // return ("Unauthorised access");

    // }

    // String email = principal.getName();
    // Optional<User> optionalUser = userRepository.findByEmail(email);
    // if (optionalUser.isEmpty()) {
    // return ("User not found");
    // }

    // User user = optionalUser.get();
    // paymentitem.setUserpayment(user);
    // paymentrepo.save(paymentitem);
    // return ResponseEntity.ok("Item added to cart for user: " + user.getName());

    // // model.addAttribute("paymentdetails",
    // paymentrepo.findByModelname("modelname"));

    // // return "payment";
    // }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
