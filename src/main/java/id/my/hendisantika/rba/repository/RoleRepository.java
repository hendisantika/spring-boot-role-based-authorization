package id.my.hendisantika.rba.repository;

import id.my.hendisantika.rba.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-role-based-authorization
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/04/25
 * Time: 10.09
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
