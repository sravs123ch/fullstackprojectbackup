package com.kodnest.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.project.entity.Playlist;
import com.kodnest.project.entity.Song;
import com.kodnest.project.service.PlaylistService;
import com.kodnest.project.service.SongService;

@Controller
public class PlaylistController {
	@Autowired
	PlaylistService playlistService;
	@Autowired
	SongService songService;
	
	@GetMapping("/createplaylists")
	public String createPlaylist(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);	
		return "createplaylists";
		}
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		System.out.println(playlist);

	    	playlistService.addPlaylist(playlist);
	    	
	    	//update the song_playlists table
	    	
	    	List<Song>songs=playlist.getSongs();
	    	for(Song song:songs) {
	    		song.getPlaylists().add(playlist);
	    		songService.updateSong(song);
	    	}
 
	    	return "adminhome";
	   }
	 @GetMapping("/viewplaylist")
	 public String viewPlaylist(Model model) {
			List<Playlist> allplaylist= playlistService.fetchAllPlaylists();
			
			 model.addAttribute("songs",allplaylist);
			 System.out.println(allplaylist);
			 return "viewplaylist"; 
			  }
}
