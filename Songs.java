/*
 * Celine Demorre & Sandy Ghbrial 
 */
	
package song;

public class Songs {

	private String song_name, artist, album, year;
	
	public Songs(String song_name, String artist, String album, String year) {
		this.song_name = song_name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName() {
		return this.song_name;
	}
	
	public void setName(String song_name) {
		this.song_name = song_name;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return song_name + ", " + artist;
	}
	
	public String toFullString() {
		return song_name + ", " + artist + ", " + album + ", " + year;
	}
	
}
