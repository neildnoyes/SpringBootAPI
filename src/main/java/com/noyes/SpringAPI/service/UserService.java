package com.noyes.SpringAPI.service;

import com.noyes.SpringAPI.api.model.User;
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


    /**
     * UserService constructor
     */
    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Neil", "Noyes", 25, "neilnoyessss@gmail.com");
        User user2 = new User(2, "Eric", "Noyes", 35, "ericnoyes@gmail.com");
        User user3 = new User(3, "Avery", "Noyes", 32, "averynoyes@gmail.com");
        User user4 = new User(4, "Kim", "Noyes", 67, "knoyes@gmail.com");
        User user5 = new User(5, "Geralyn", "Noyes", 59, "gnoyes@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    /**
     *
     * @param id - integer representing a user id
     * @return returns an optional filled with a user or empty optional
     */
    public Optional<User> getUser(Integer id){

        Optional optional = Optional.empty();

        for (User user : userList){
            if (id == user.get_id()){
                optional = Optional.of(user);
                return optional;
            }
        }

        return optional;

    }

    /**
     *
     * @param user - user to add
     * @return ResponseEntity representing good or bad request
     */
    public ResponseEntity<String> addUser(User user){

        //make sure user does not exist
        if (!userExists(user.get_id())){

            //add user
            userList.add(user);

            return ResponseEntity.ok("User added successfully");

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to add specified user - id exists");
        }

    }

    /**
     *
     * @param user - user to update
     * @return ResponseEntity representing good or bad request
     */
    public ResponseEntity<String> updateUser(User user){

        //make sure user does not exist
        if (userExists(user.get_id())){

            updateUserFields(user);

            return ResponseEntity.ok("User updated successfully");

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update specified user - id dne");
        }

    }

    public List<User> getUserList(){
        return userList;
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
            if (user.get_id() == id){
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
            if (currentUser.get_id() == src_user.get_id()){
                currentUser.set_age(src_user.get_age());
                currentUser.set_email(src_user.get_email());
                currentUser.set_last_name(src_user.get_last_name());
                currentUser.set_first_name(src_user.get_first_name());
            }
        }

    }

}
