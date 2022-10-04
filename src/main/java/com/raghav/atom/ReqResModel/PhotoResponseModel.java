package com.raghav.atom.ReqResModel;

import com.raghav.atom.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoResponseModel {
    private List<Photo> photos;
    private int currentPage;
    private int totalPages;
    private int pageSize;

    public PhotoResponseModel(Page<Photo> page){
        this.photos = page.getContent();
        this.currentPage = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
    }
}
