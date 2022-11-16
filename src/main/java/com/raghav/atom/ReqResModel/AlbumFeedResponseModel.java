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
    private long totalRecords;

    public AlbumFeedResponseModel(Page<AlbumFeed> page) {
        this.albums = page.getContent();
        this.currentPage = page.getNumber() + 1;
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.totalRecords = page.getTotalElements();
    }
}
