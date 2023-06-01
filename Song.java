package p2;

import java.net.URL;

public class Song{
    String title, genre, composer, lang, sid;
    int duration;
    URL MuML;
    Album album;

    public Song(){}

    public Song(String title, String lang, String genre,String composer, String sid, Int duration, URL MuML, Album album){
        this.title = title;
        this.genre = genre;
        this.composer = composer;
        this.lang = lang;
        this.sid = sid;
        this.duration = duration;
        this.MuML =  MuML;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Int getDuration() {
        return duration;
    }

    public void setDuration(Int duration) {
        this.duration = duration;
    }

    public URL getMuML() {
        return MuML;
    }

    public void setMuML(URL muML) {
        this.MuML = muML;
    }

    public URL getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}

