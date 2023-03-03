package shotny.ToDoListapi.bucket;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.BaseTimeEntity;
import shotny.ToDoListapi.todos.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Bucket extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bucketName;

    @Column
    private int listCount;

    @Column
    private int completedCount;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();


    @Builder
    public Bucket(String title) {
        this.bucketName = title;
    }

    public void updateName(String bucketName) {
        this.bucketName = bucketName;
    }


    public void countUp() {
        ++ this.listCount;
    }

    public void countDown() {
        if (this.listCount > 0) {
            -- this.listCount;
        }
    }

    public void completedUp() {
        ++this.completedCount;
    }

    public void completedDown() {
        if (this.completedCount > 0) {
            --this.completedCount;
        }
    }

//    public void updateCompleted(boolean completed) {
//        if (completed == true) {
//            this.completedCount++;
//        } else {
//            this.completedCount--;
//        }
//    }
}
