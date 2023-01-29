package com.okamor.mmbeauty.model;

import com.okamor.mmbeauty.model.enums.CourseSatatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Course")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String uniqCode;
    private String name;

    private String description;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date startDate = new Date();

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date endDate;

    private int dayAccess;
    private String urlLink;

    private String previewLink;

    private String avatarUrl;
    private double price;

    private double discount = 1;

    @Enumerated(EnumType.STRING)
    private CourseSatatus status = CourseSatatus.COURSE_ENABLE;

}
