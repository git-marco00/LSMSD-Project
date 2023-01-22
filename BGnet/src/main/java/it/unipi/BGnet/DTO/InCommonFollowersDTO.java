package it.unipi.BGnet.DTO;

public class InCommonFollowersDTO {
    private String name;
    private String imgUrl;

    public InCommonFollowersDTO(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
