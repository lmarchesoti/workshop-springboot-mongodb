package dev.lmarchesoti.workshopmongo.services;

import dev.lmarchesoti.workshopmongo.domain.User;
import dev.lmarchesoti.workshopmongo.dto.UserDTO;
import dev.lmarchesoti.workshopmongo.repository.UserRepository;
import dev.lmarchesoti.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found with id " + id));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public void update(String id, UserDTO dto) {
        User user = findById(id);
        updateData(user, dto);
        userRepository.save(user);
    }

    private void updateData(User user, UserDTO dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }

    public User fromDto(UserDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        return user;
    }

}
