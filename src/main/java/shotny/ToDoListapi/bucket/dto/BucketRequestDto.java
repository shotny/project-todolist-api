package shotny.ToDoListapi.bucket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BucketRequestDto {
    private String name;

    public Bucket toEntity() {
        return Bucket.builder()
                .name(name)
                .build();
    }
}
