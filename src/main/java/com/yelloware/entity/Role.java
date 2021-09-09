package com.yelloware.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Role  implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

	@Basic
	@Column(name = "name")
    private String name;

	@Basic
	@Column(name = "description")
    private String description;

   /* @OneToMany
    @JoinColumn(name = "role_id")
    private List<String> role = new ArrayList<>();
    
    private RoleType RoleType;
    
    private User user;
    
    public enum RoleType {
        YONETICI,
        IK_YONETICISI,
        PROJE_SORUMLUSU,
        IK_PERSONELI,
        ADAY
    }*/

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
