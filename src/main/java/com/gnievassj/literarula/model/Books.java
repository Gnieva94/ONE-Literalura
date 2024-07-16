package com.gnievassj.literarula.model;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Authors author;
    private String language;
    private Double downloadCount;
    public Books(){}
    public Books(DataBooks dataBooks){
        this.title = dataBooks.title();
        this.author = new Authors(dataBooks.authors().get(0));
        this.language = dataBooks.languages().get(0);
        this.downloadCount = dataBooks.downloadCount();
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public Authors getAuthor() {return author;}

    public void setAuthor(Authors author) {this.author = author;}

    public String getLanguage() {return language;}

    public void setLanguage(String language) {this.language = language;}

    public Double getDownloadCount() {return downloadCount;}

    public void setDownloadCount(Double downloadCount) {this.downloadCount = downloadCount;}

    @Override
    public String toString() {
//        return "Books{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", language='" + language + '\'' +
//                ", downloadCount=" + downloadCount +
//                '}';
        return """
                ------- LIBRO -------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Num de descargas: %f
                ---------------------
                """.formatted(title,author.getName(),language,downloadCount);
    }
    public String formato(){
        String authorName = (author != null)?author.getName():"Autor no disponible";
        return """
                ------- LIBRO -------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Num de descargas: %f
                ---------------------
                """.formatted(title,authorName,language,downloadCount);
    }
}
