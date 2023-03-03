package shotny.ToDoListapi.bucket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BucketRequestDto {
    private String bucketName;

    public Bucket toEntity() {
        return Bucket.builder()
                .title(bucketName)
                .build();
    }
}
