package yte.intern.project.EventManagementSystem.usecases.managesecurity;


import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Authority;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.repository.AuthorityRepository;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.repository.UserRepository;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.util.RandomString;
import yte.intern.project.EventManagementSystem.usecases.sendqrcode.EmailService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final EmailService emailService;

    @Override
    public void createUser(final UserDetails user) {
        Users users = (Users) user;
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
    }

    @Override
    public void updateUser(final UserDetails user) {
        Users oldUser = (Users) loadUserByUsername(user.getUsername());
        Users newUser = (Users) user;
        newUser.setId(oldUser.getId());
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(final String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        Users user = (Users) loadUserByUsername(username);
        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Wrong old password is given!");
        }
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Users addUser(String username, String email) throws MessagingException, IOException, WriterException {
        Optional<Users> usersOptional = userRepository.findByUsername(username);
        if(usersOptional.isPresent()){
            throw new CustomException("Bu isimde etkinlik görevlisi zaten mevcut");
        }
        else{
            Optional<Authority> authorityOptional = authorityRepository.findByAuthority("EVENT_MANAGER");
            Authority authority;
            if(authorityOptional.isPresent()){
                authority = authorityOptional.get();
            }
            else{
                authority = authorityRepository.save(new Authority(null, "EVENT_MANAGER"));
            }
            RandomString randomString = new RandomString();
            String password = randomString.getAlphaNumericString(8);
            Users addedUser = userRepository.save(new Users(null, username, passwordEncoder.encode(password), Set.of(authority)));
            emailService.sendMailWithPassword(email, addedUser.getUsername(), password);
            return addedUser;
        }
    }

    public List<String> getAllEventManagers(){
        List<String> eventManagers = new ArrayList<String>();
        List<Users> users = userRepository.findAll();
        for(Users user: users){
            if(!user.getUsername().equals("admin"))
                eventManagers.add(user.getUsername());
        }
        return eventManagers;
    }

}
