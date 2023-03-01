package shotny.ToDoListapi.bucket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;

@Getter
@NoArgsConstructor
public class BucketRequestDto {
    private String bucketName;

    public Bucket toEntity() {
        return Bucket.builder()
                .title(bucketName)
                .build();
    }
}
