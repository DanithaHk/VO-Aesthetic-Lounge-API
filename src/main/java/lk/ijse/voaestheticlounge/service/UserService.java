package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.UserDTO;

import java.util.List;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
    void deleteUser(Long id);
    void updateUserRole(String email, String newRole);
    List<UserDTO> getAll();
    UserDTO findByEmail(String email);

}
