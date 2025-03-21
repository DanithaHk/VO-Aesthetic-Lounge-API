package lk.ijse.voaestheticlounge.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lk.ijse.voaestheticlounge.dto.UserDTO;
import lk.ijse.voaestheticlounge.entity.User;
import lk.ijse.voaestheticlounge.repo.UserRepository;
import lk.ijse.voaestheticlounge.service.UserService;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername "+email);
        User user = userRepository.findByEmail(email);
        System.out.println("loadUserByUsername "+email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)){
            User user = userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);

        }else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(String.valueOf(id))){
          throw new RuntimeException("user not Found");

        }
        userRepository.deleteById(String.valueOf(id));
    }

    @Override
    public void updateUserRole(String email, String newRole) {
        User user = userRepository.findByEmail(String.valueOf(email));

        user.setRole(newRole); // Update only the role
        userRepository.save(user);

    }

    @Override
    public List<UserDTO> getAll() {
        return modelMapper.map(userRepository.findAll(),new TypeToken<List<UserDTO>>() {}.getType());

    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return modelMapper.map(user,UserDTO.class);
    }


    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            userDTO.setRole("USER");
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }



}
