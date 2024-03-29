package user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/*
   操作相关注解 @PostMapping, @GetMapping, @PutMapping, Deletemapping
   参数相关注解 @RequestBody, @PathVariable
*/
@RestController
public class UserController {
    private final Map<String,User> map = new HashMap<>();

    UserController(){
        map.put("aaa",new User("aaa",11));
        map.put("bbb",new User("bbb",22));
        map.put("ccc",new User("ccc",33));
    }

    /*
    @GetMapping("/users")
    public List<User> getUsers() {
        Set<String> keys = map.keySet();
        List<User> res = new ArrayList<>();
        for (String name:keys) {
            res.add(map.get(name));
        }
        return res;
    }

    @GetMapping("/users/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            return null;
        }
    }

    @PostMapping("/users")
    public User AddUser(@RequestBody User user) {
        String name = user.getName();
        map.put(name,user);
        return user;
    }

    @PutMapping("/users/{name}")
    public User UpdateUser(@PathVariable("name") String name ,@RequestBody User user) {
        map.get(name).setAge(user.getAge());
        return user;
    }
    @DeleteMapping("/users/{name}")
    public User DelUserByName(@PathVariable("name") String name){
        if(map.containsKey(name)){
            return map.remove(name);
        }else{
            return null;
        }
    }
    */

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers(){
        Set<String> keys = map.keySet();
        List<User> res = new ArrayList<>();
        for (String name:keys) {
            res.add(map.get(name));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name) {
        if(map.containsKey(name)){
            return new ResponseEntity<>(map.get(name),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User>  AddUser(@RequestBody User user) {
        String name = user.getName();
        if(map.containsKey(name)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            map.put(name,user);
            return new ResponseEntity<>(map.get(name),HttpStatus.CREATED);
        }
    }



    @PutMapping("/users/{name}")
    public ResponseEntity<User> UpdateUser(@PathVariable("name") String name ,@RequestBody User user) {
        if(map.containsKey(name)){
            User updateUser = map.get(name);
            updateUser.setAge(user.getAge());
            return new ResponseEntity<>(map.get(name),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity<User> DelUserByName(@PathVariable("name") String name){
        if(map.containsKey(name)){
            return new ResponseEntity<>(map.remove(name),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
