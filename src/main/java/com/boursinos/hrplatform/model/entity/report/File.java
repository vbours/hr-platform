package com.boursinos.hrplatform.model.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Table(name = "File")
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "file_id")
    private String fileId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "filename")
    private String filename;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "type")
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "bucket")
    private String bucket;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public File(String filename, String type, String bucket) {
        this.filename = filename;
        this.type = type;
        this.bucket = bucket;
    }
}
