//package com.java.ph3.halinamarket;
//
//import com.java.ph3.halinamarket.models.User;
//import com.java.ph3.halinamarket.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class UserRepositoryTests {
//
//    @Autowired
//    private UserRepository repo;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        user.setEmail("name@gmail.com");
//        user.setPassword("name123");
//        user.setFirstName("First Name");
//        user.setLastName("Last Name");
//        user.isAcceptEmail(true);
////      missing userByUserAddressId -- create user_address
//
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getEmail());
//
//        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
//    }
//}
