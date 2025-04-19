package dev.usdev.spring.boot.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HomeDto {
    private List<LinkDto> links;

//    public HomeDto() {
//    }
//
//    public HomeDto(List<LinkDto> links) {
//        this.links = links;
//    }
//
//    public List<LinkDto> getLinks() {
//        return links;
//    }
//
//    public void setLinks(List<LinkDto> links) {
//        this.links = links;
//    }
}