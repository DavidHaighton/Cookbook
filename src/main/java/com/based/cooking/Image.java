package com.based.cooking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Generated
    @Id
    private long id;
    private String description;
    @Lob
    private byte[] photo;


}
