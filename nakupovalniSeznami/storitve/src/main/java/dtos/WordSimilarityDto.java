package dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WordSimilarityDto {
    @JsonProperty("ftext")
    private String ftext;
    @JsonProperty("stext")
    private String stext;
    @JsonProperty("percentage")
    private Float percentage;

    public String getFtext() {
        return ftext;
    }

    public void setFtext(String ftext) {
        this.ftext = ftext;
    }

    public String getStext() {
        return stext;
    }

    public void setStext(String stext) {
        this.stext = stext;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}