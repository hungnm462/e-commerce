package vn.gs.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "example")
public class ExampleEntity implements Serializable {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;
}
