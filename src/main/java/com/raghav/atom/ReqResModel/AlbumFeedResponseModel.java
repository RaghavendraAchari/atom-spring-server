package com.raghav.atom.ReqResModel;

import com.raghav.atom.model.AlbumFeed;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class AlbumFeedResponseModel {
    private List<AlbumFeed> albums;
    private int currentPage;
    private int totalPages;
    private int pageSize;

    public AlbumFeedResponseModel(Page<AlbumFeed> page) {
        System.out.println(page.getContent().toString());
        this.albums = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();

    }
}
