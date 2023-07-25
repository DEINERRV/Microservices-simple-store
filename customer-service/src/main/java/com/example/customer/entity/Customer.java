package com.example.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "tbl_customers")
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "El numero de documento no puede ser vacio")
    @Size(min = 8 , max = 8, message = "El tamanno del numero de documento es 8")
    @Column(name = "number_id" , unique = true ,length = 8, nullable = false)
    String numberId;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Size(min = 3, max = 15, message = "El tamanno del nombre tiene que ser de 3 a 15 caracteres")
    @Column(name = "first_name" , unique = true ,length = 8, nullable = false)
    String firstname;

    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name="last_name", nullable=false)
    private String lastName;
    
    @NotEmpty(message = "El correo electronico no puede ser vacio")
    @Email(message = "El correo electronico no es valido")
    @Column(unique=true, nullable=false)
    private String email;

    @Column(name="photo_url")
    private String photoUrl;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @NotNull(message = "La region no debe ser vacia")
    private Region region;
}
