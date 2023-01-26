package com.based.cooking;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

/**
 * Bean for recipes
 * All recipes must have
 * - a name
 * - an author
 * - a creation date
 * - todo: a photo
 * - tags
 * - ingredients
 * - instructions
 */
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Getter
public class Recipe {

    @Id
    @GeneratedValue
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Generated
    private long id;

    @Setter
    @NonNull
    private String name;

    @Setter
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Setter
    @NonNull
    private String author;

    @Setter
    @ElementCollection
    @NonNull
    private Set<String> tags;

    @Setter
    @NonNull
    private String ingredients;

    @Setter
    @NonNull
    private String instructions;

    @Setter
    @NonNull
    private String notes;

}
