package com.example.TMS.Service;

import com.example.TMS.Entity.User;
import com.example.TMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User FindOrCreateUser(OAuth2User oAuth2User){
        String name= oAuth2User.getAttribute("name");
        String email =oAuth2User.getAttribute("email");
        String picture =oAuth2User.getAttribute("picture");
        String googleId = oAuth2User.getAttribute("sub");

        Optional<User> existingUser=userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            User user=existingUser.get();
            user.setName(name);
            user.setPicture(picture);
            if(user.getGoogleId()==null){
                user.setGoogleId(googleId);
            }
            return userRepository.save(user);
        }else{
            User newUser=new User(email,name,picture,googleId);
            return userRepository.save(newUser);
        }
    }
public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
}
public User save(User user){
        return userRepository.save(user);
}
}
