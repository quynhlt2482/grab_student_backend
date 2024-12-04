package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

public class StudentPasswordUpdateDTO {
    private String password;

    public StudentPasswordUpdateDTO() {}

    public StudentPasswordUpdateDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
