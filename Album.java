package p2;
public class Album {
    String name, country, singer, group, company, aid, review, format;
    int year, ISBN;
    Song song;

    public Album() {
    }

     public Album(String name, int year, String group, String singer, String review, String aid){
        this.aid = aid;
	    this.name = name;
        this.year = year;
        this.group = group;
        this.singer = singer;
        this.review = review;
    }

    public Album(String name, String country, String company, String review){

    }

    public Album(String name, String country, String singer, String group, String company, String aid, String review, String format, int year, int ISBN, Song song) {
        this.name = name;
        this.country = country;
        this.singer = singer;
        this.group = group;
        this.company = company;
        this.aid = aid;
        this.review = review;
        this.format = format;
        this.year = year;
        this.ISBN = ISBN;
        this.song = song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReview(){
        return review;
    }

    public void setReview(String review){
        this.review = review;   
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}

