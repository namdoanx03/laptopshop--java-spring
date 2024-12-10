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

<<<<<<< HEAD
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
=======
    public UserService(UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
>>>>>>> temp-branch

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
<<<<<<< HEAD
        return this.userRepository.findByEmail(email);
    }

    // public List<User> getAllUsersById(long id){
    // return this.userRepository.findById(id);
    // }
    public User getUserById(long id) {
        return this.userRepository.findById(id);
=======
        return this.userRepository.findOneByEmail(email);
>>>>>>> temp-branch
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

<<<<<<< HEAD
=======
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

>>>>>>> temp-branch
    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

<<<<<<< HEAD
    // dau vao la RegisterDTO registerDTO, dau ra la User
=======
>>>>>>> temp-branch
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
