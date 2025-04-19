package id.my.hendisantika.rba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-role-based-authorization
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/04/25
 * Time: 10.14
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@EnableMethodSecurity
public class TestController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    public Authentication getAuthenticatedUser() {
        log.info("test {}", SecurityContextHolder.getContext().getAuthentication());
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/noRoleNeeded")
    public String publicResource() {
        log.info("This resource only requires authentication");
        return "This resource only requires authentication";
    }

    @GetMapping("/roleNeeded")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String privateResource() {
        log.info("This resource requires the ADMIN role");
        return "This resource requires the ADMIN role";
    }
}
