package com.raghav.atom.ReqResModel;

import com.raghav.atom.model.AlbumFeed;
import com.raghav.atom.model.Art;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtResponseModel {
    private List<Art> albums;
    private int currentPage;
    private int totalPages;
    private int pageSize;

    public ArtResponseModel(Page<Art> page) {
        System.out.println(page.getContent().toString());
        this.albums = page.getContent();
        this.currentPage = page.getNumber() + 1;
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();

    }
}
