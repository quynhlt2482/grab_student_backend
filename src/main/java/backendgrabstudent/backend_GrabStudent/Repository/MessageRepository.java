package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByConversationIdOrderBySendAtAsc(Integer conversationId);

    @Transactional
    @Modifying
    @Query("UPDATE Message m SET m.status = :status WHERE m.id IN :messageIds")
    void updateStatusByMessageIds(@Param("status") String status, @Param("messageIds") List<Integer> messageIds);

    Integer countByRecipientIdAndStatus(Integer recipientId, String status);
}

