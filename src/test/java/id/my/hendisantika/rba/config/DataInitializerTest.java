package id.my.hendisantika.rba.config;

import id.my.hendisantika.rba.domain.Role;
import id.my.hendisantika.rba.domain.User;
import id.my.hendisantika.rba.repository.RoleRepository;
import id.my.hendisantika.rba.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DataInitializerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testDataInitialization() {
        // Verify roles are created
        Role adminRole = roleRepository.findByName("ADMIN");
        Role userRole = roleRepository.findByName("USER");

        assertNotNull(adminRole, "Admin role should be created");
        assertNotNull(userRole, "User role should be created");
        assertEquals("ADMIN", adminRole.getName());
        assertEquals("USER", userRole.getName());

        // Verify admin user is created with ADMIN role
        User adminUser = userRepository.findByUsername("admin");
        assertNotNull(adminUser, "Admin user should be created");
        assertTrue(adminUser.isEnabled(), "Admin user should be enabled");

        boolean hasAdminRole = adminUser.getRoles().stream()
                .anyMatch(role -> "ADMIN".equals(role.getName()));
        assertTrue(hasAdminRole, "Admin user should have ADMIN role");

        // Verify regular user is created with USER role
        User regularUser = userRepository.findByUsername("user");
        assertNotNull(regularUser, "Regular user should be created");
        assertTrue(regularUser.isEnabled(), "Regular user should be enabled");

        boolean hasUserRole = regularUser.getRoles().stream()
                .anyMatch(role -> "USER".equals(role.getName()));
        assertTrue(hasUserRole, "Regular user should have USER role");

        System.out.println("[DEBUG_LOG] Admin user: " + adminUser.getUsername() +
                ", Roles: " + adminUser.getRoles().stream().map(Role::getName).toList());
        System.out.println("[DEBUG_LOG] Regular user: " + regularUser.getUsername() +
                ", Roles: " + regularUser.getRoles().stream().map(Role::getName).toList());

        // Debug role comparison
        System.out.println("[DEBUG_LOG] Admin role ID: " + adminRole.getId() + ", Name: " + adminRole.getName());
        for (Role role : adminUser.getRoles()) {
            System.out.println("[DEBUG_LOG] Admin user role ID: " + role.getId() + ", Name: " + role.getName());
        }
    }
}
