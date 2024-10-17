package kg.alatoo.library.services;


import kg.alatoo.library.dto.SignUpDto;
import kg.alatoo.library.entities.UserEntity;
import kg.alatoo.library.exceptions.ApiException;
import kg.alatoo.library.model.UserModel;
import kg.alatoo.library.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return new UserModel(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getCreatedAt()
        );
    }

    public void saveUser(SignUpDto signUpDto) {
        log.info("Sign up user: {}", signUpDto.getUsername());
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUpDto.getName());
        userEntity.setUsername(signUpDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User " + signUpDto.getUsername() + " is already exists", HttpStatusCode.valueOf(409));
        } catch (Exception e) {
            log.error("Error", e);
            throw new ApiException("Error while user creating", HttpStatusCode.valueOf(400));
        }
    }
}
