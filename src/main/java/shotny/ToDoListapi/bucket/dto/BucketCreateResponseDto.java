package shotny.ToDoListapi.bucket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;

@Getter
@NoArgsConstructor
public class BucketCreateResponseDto {

    private Long id;
    private String name;

    public BucketCreateResponseDto(Bucket entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
