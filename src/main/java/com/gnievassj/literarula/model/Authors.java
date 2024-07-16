package com.gnievassj.literarula.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalInt;

@Entity
@Table(name="authors")
public class Authors {
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
        this.birthYear = OptionalInt.of(dataAuthors.birthYear()).orElse(0);
        this.deathYear = OptionalInt.of(dataAuthors.deathYear()).orElse(0);
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
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", books=" + books +
                '}';
    }
    public String formato(){
        return """
                ----- Autor -----
                Nombre: %s
                Año nacimiento: %d
                Año muerte: %d
                -----------------
                """.formatted(name,birthYear,deathYear);
    }
}
