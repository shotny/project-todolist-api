package shotny.ToDoListapi.todos;

import lombok.*;
import shotny.ToDoListapi.BaseTimeEntity;
import shotny.ToDoListapi.bucket.Bucket;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column
    private String content;

    @Column(nullable = false)
    private boolean completed;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

//    private enum todoStatus{
//        COMPLETED, UNCOMPLETED;
//    }

    public void updateTodo(String toDo) {
        this.content = toDo;
    }

    public void updateCompleted() {
        if (this.completed == false) {
            this.completed = true;
        } else {
            this.completed = false;
        }
    }

    @Builder
    public Todo(String content) {
        this.content = content;
        this.completed = false;
    }
}
