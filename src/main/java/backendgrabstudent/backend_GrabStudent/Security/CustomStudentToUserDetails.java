package backendgrabstudent.backend_GrabStudent.Security;

import backendgrabstudent.backend_GrabStudent.Entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomStudentToUserDetails implements UserDetails {
    private final Student student;

    public CustomStudentToUserDetails(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Gán vai trò mặc định ROLE_USER
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Có thể sử dụng trường `isBanned` để xác định trạng thái khóa
        return !student.getBanned();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Dựa vào `verifyStudent` để quyết định tài khoản có được kích hoạt không
        return student.getVerifyStudent();
    }

    public String getName() {
        return student.getName();
    }
}
