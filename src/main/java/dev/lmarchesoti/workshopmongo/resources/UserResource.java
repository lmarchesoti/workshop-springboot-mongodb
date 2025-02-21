package dev.lmarchesoti.workshopmongo.resources;

import dev.lmarchesoti.workshopmongo.domain.User;
import dev.lmarchesoti.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
//        User maria = new User("1", "Maria Brown", "maria@gmail.com");
//        User alex = new User("2", "Alex Green", "alex@gmail.com");
//
//        List<User> userList = new ArrayList<>(Arrays.asList(maria, alex));

        List<User> userList = userService.findAll();

        return ResponseEntity.ok().body(userList);
    }

}
