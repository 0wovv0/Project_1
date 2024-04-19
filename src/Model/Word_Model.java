package Model;

public class Word_Model {
    String word, mean, genre;
    String ipa;
    String status;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Word_Model(String word, String mean, String ipa, String genre) {
        this.word = word;
        this.mean = mean;
        this.genre = genre;
        this.ipa = ipa;
    }

}
