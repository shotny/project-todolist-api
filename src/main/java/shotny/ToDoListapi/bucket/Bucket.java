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
    private String name;

    @Column
    private int count;

    @Column
    private int completedCount;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();


    @Builder
    public Bucket(String name) {
        this.name = name;
    }

    public void updateName(String bucketName) {
        this.name = bucketName;
    }

    public void countUp() {
        ++this.count;
    }

    public void countDown() {
        if (this.count > 0) {
            -- this.count;
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
}
