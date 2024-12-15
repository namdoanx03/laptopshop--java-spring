package vn.namdoan.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.namdoan.laptopshop.domain.Role;
import vn.namdoan.laptopshop.domain.User;
import vn.namdoan.laptopshop.service.UploadService;
import vn.namdoan.laptopshop.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String createUserPage(@ModelAttribute("newUser") @Valid User user,
            BindingResult result,
            @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println("Error: " + error.getField() + " - " + error.getDefaultMessage());
            }
            return "admin/user/create";
        }

        // Handle file upload
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        user.setAvatar(avatar);

        // Set hashed password
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Set role
        Role role = this.userService.getRoleByName("USER");
        if (role == null) {
            result.rejectValue("role", "error.role", "Role không hợp lệ");
            return "admin/user/create";
        }
        user.setRole(role);

        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/user";
        }
        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(@ModelAttribute("newUser") User updatedUser,
            @RequestParam("file") MultipartFile file) {
        User currentUser = this.userService.getUserById(updatedUser.getId());
        if (currentUser == null) {
            return "redirect:/admin/user";
        }

        // Update fields
        currentUser.setFullName(updatedUser.getFullName());
        currentUser.setAddress(updatedUser.getAddress());
        currentUser.setPhone(updatedUser.getPhone());

        // Optional: Update avatar if file is uploaded
        if (!file.isEmpty()) {
            String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
            currentUser.setAvatar(avatar);
        }

        // Optional: Update password if not empty
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String hashedPassword = this.passwordEncoder.encode(updatedUser.getPassword());
            currentUser.setPassword(hashedPassword);
        }

        this.userService.handleSaveUser(currentUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/delete")
    public String postDeleteUser(@RequestParam("id") long id) {
        this.userService.deleteAUser(id);
        return "redirect:/admin/user";
    }
}
