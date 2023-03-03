package shotny.ToDoListapi.bucket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;
import shotny.ToDoListapi.todos.Todo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BucketCreateResponseDto {

    private Long id;
    private String bucketName;

    public BucketCreateResponseDto(Bucket entity) {
        this.id = entity.getId();
        this.bucketName = entity.getBucketName();
    }
}
