package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ConversationRepos extends JpaRepository<Conversation, Integer> {
    Boolean existsByStudent1IdAndStudent2Id(Integer student1Id, Integer student2Id);

    Conversation findByStudent1IdAndStudent2Id(Integer studentId, Integer student2Id);

    @Query("select c from Conversation  c where c.student1.id = :studentId or c.student2.id = :studentId")
    List<Conversation> findByStudentId(@Param("studentId") Integer studentId);
}