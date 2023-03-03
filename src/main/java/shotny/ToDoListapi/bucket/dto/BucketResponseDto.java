package shotny.ToDoListapi.bucket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BucketResponseDto {

    private Long id;
    private String name;
    private int count;
    private int completedCount;
    private LocalDateTime createdAt;

    @Builder
    public BucketResponseDto(Bucket entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.count = entity.getCount();
        this.completedCount = entity.getCompletedCount();
        this.createdAt = entity.getModifiedAt();
    }
}
