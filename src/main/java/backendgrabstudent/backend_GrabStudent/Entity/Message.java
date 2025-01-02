package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Student sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Student recipient;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    private String content;
    private String status;
    private LocalDateTime sendAt;
    private LocalDateTime updatedAt;
}