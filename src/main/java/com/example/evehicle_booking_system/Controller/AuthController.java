package com.example.evehicle_booking_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.evehicle_booking_system.Repository.UserRepository;
import com.example.evehicle_booking_system.UserModel.User;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🏠 Home Page (Public)
    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home"; // home.html (contains login/register buttons)
    }

//     @GetMapping("/User/home")
// public String userHome() {
//     return "user_home"; // user_home.html
// }


    // 🌐 Login Page (GET)
    @GetMapping("/login")
    public String loginPage() {
        
        return "login"; // login.html in templates
    }

    

    // 🌐 Registration Page (GET)
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html in templates
    }

    // 🧾 Save new user (POST)
@PostMapping("/saveUser")
public String saveUser(@ModelAttribute("user") User user) {

    // Encrypt password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Set default role
    user.setRole("ROLE_USER");

    // Save user
    userRepository.save(user);

    // Redirect to login after successful registration
    return "redirect:/login?registerSuccess=true";
}


    // // 🧍‍♂️ User Dashboard after successful login
    // @GetMapping("/User/home")
    // public String userHome() {
    //     return "user_home"; // user_home.html (user dashboard)
    // }

    // // 👑 Admin Dashboard (if needed)
    // @GetMapping("/Admin/dashboard")
    // public String adminDashboard() {
    //     return "admin_dashboard"; // admin_dashboard.html
    // }
}
