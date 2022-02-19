package account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @Id
//    @JsonIgnore
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    @Column
    @NotNull
    @NotBlank
    String name;

    @Column
    @NotNull
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Column
    @NotNull
    @NotBlank
    String lastname;

    @Column
    @NotNull
    @NotBlank
    @Pattern(regexp = ".+@acme.com")
    String email;
}
