package com.gnievassj.literarula.model;

import com.gnievassj.literarula.service.IFormatoDatos;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Entity
@Table(name="authors")
public class Authors implements IFormatoDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Books> books;
    public Authors(){}
    public Authors(DataAuthors dataAuthors){
        this.name = dataAuthors.name();
        //this.birthYear = OptionalInt.of(dataAuthors.birthYear()).orElse(0);
        //this.deathYear = OptionalInt.of(dataAuthors.deathYear()).orElse(0);
        this.birthYear = dataAuthors.birthYear();
        this.deathYear = dataAuthors.deathYear();
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getBirthYear() {return birthYear;}

    public void setBirthYear(Integer birthYear) {this.birthYear = birthYear;}

    public Integer getDeathYear() {return deathYear;}

    public void setDeathYear(Integer deathYear) {this.deathYear = deathYear;}

    public List<Books> getBooks() {return books;}

    public void setBooks(List<Books> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + Optional.ofNullable(birthYear).map(String::valueOf).orElse("No data")+
                ", deathYear=" + Optional.ofNullable(deathYear).map(String::valueOf).orElse("No data")+
                ", books=" + books +
                '}';
    }
    @Override
    public String formato(){
        //String authorBirthYear = (birthYear != null) ? birthYear.toString() : "No data";
        //String authorDeathYear = (deathYear != null) ? deathYear.toString() : "No data";
        return  """
                ----- Autor -----
                Nombre: %s
                Año nacimiento: %s
                Año muerte: %s
                -----------------
                """
                .formatted(
                        name,
                        Optional.ofNullable(birthYear).map(String::valueOf).orElse("No data"),
                        Optional.ofNullable(deathYear).map(String::valueOf).orElse("No data")
                );
    }
}
