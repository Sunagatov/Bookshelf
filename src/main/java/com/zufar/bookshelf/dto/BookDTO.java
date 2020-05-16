package com.zufar.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private List<Long> authors;
    private DateDTO publicationDate;
    private String imageLink;
    private String epubLink;
    private String fb2Link;
    private String pdfLink;
    private Long countryId;
    private int pageCount;

    public BookDTO(String title, List<Long> authorIds, DateDTO publicationDate, Long country, int page_count) {
        this.title = title;
        this.authors = authorIds;
        this.publicationDate = publicationDate;
        this.countryId = country;
        this.pageCount = page_count;
    }
}
