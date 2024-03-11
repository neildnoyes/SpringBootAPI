package com.noyes.SpringAPI.service;

import com.noyes.SpringAPI.api.model.User;
import com.noyes.SpringAPI.api.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /**
     * List of Users (in lieu of DB connection)
     */
    private List<User> userList;

    @Autowired
    private UserRepository userRepository;


    /**
     * UserService constructor
     */
    public UserService() {}

    /**
     *
     * @param id - integer representing a user id
     * @return returns an optional filled with a user or empty optional
     */
    public Optional<User> getUser(Integer id){

        return userRepository.findById(id);

    }

    /**
     *
     * @param user - user to add
     * @return ResponseEntity representing good or bad request
     */
    public ResponseEntity<String> addUser(User user){

        //make sure user does not exist
        if (userRepository.existsById(user.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to add specified user - id exists");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User added successfully");

    }

    /**
     *
     * @param user - user to update
     * @return ResponseEntity representing good or bad request
     */
    public ResponseEntity<String> updateUser(User user){

        //make sure user does not exist
        if (!userRepository.existsById(user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update specified user - id does not exist");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User updated successfully");

    }

    /**
     *
     * @param id - user id to delete
     * @return ResponseEntity representing good or bad request
     */
    public ResponseEntity<String> deleteUser(Integer id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {

            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        }
    }

    /**
     *
     * @return List of all Users in tbl_user
     */
    public List<User> getUserList(){
        return userRepository.findAll();
    }

    /**
     * Helper Section
     */

    /**
     *
     * @param id - integer representing a user id
     * @return existsFlag
     */
    private boolean userExists(int id){

        boolean existsFlag = false;

        for (User user : userList){
            if (user.getId() == id){
                existsFlag = true;
            }
        }

        return existsFlag;
    }

    /**
     *
     * @param src_user - user object with updated fields
     */
    private void updateUserFields(User src_user){

        //loop users
        for (int i = 0; i < userList.size(); i++){

            //curr user
            User currentUser = userList.get(i);

            //set fields if id matches
            if (currentUser.getId() == src_user.getId()){
                currentUser.setAge(src_user.getAge());
                currentUser.setEmail(src_user.getEmail());
                currentUser.setLastName(src_user.getLastName());
                currentUser.setFirstName(src_user.getFirstName());
            }
        }

    }

}
