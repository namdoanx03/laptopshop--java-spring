package vn.namdoan.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.namdoan.laptopshop.domain.Role;
import vn.namdoan.laptopshop.domain.User;
import vn.namdoan.laptopshop.domain.dto.RegisterDTO;
import vn.namdoan.laptopshop.repository.RoleRepository;
import vn.namdoan.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Lấy người dùng theo email
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    // Lấy người dùng theo ID
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    // Kiểm tra email đã tồn tại hay chưa
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    // Xóa người dùng theo ID
    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    // Lưu một user
    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    // Chuyển RegisterDTO thành User
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    // Lấy Role theo tên
    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
