package com.satyam.xmeme.controller;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.PostMemeResponse;
import com.satyam.xmeme.dto.UpdateMeme;
import com.satyam.xmeme.dto.UserMeme;
import com.satyam.xmeme.services.MemeService;
import com.satyam.xmeme.services.MemeServicePost;
import com.satyam.xmeme.services.MemeServiceUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class XmemeController {

    public static final String END_POINT = "/memes";
    public static final String MEME_API_ID= "/{moduleId}";

    @Autowired
    private MemeService memeService;

    @Autowired
    private MemeServicePost memeServicePost;

    @Autowired
    private MemeServiceUpdate memeServiceUpdate;

    @GetMapping(END_POINT + MEME_API_ID)
    public ResponseEntity<UserMeme> findMeme(@PathVariable String moduleId) {

        UserMeme getMemes = memeService.findMemeById(moduleId);

        return ResponseEntity.ok().body(getMemes);
    }

    @GetMapping(END_POINT)
    public ResponseEntity<List<UserMeme>> findAllMemes() {

        List<UserMeme> getMemes = memeService.findAllMemes();

        return ResponseEntity.ok().body(getMemes);
    }

    @PostMapping(END_POINT)
    public ResponseEntity<?> postMemes(@RequestBody UserMeme meme) {

        PostMemeResponse postMemeResponse = memeServicePost.postMemes(meme);

        if(postMemeResponse.getId().equals("empty")) {
            return new ResponseEntity<>("Duplicate",HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().body(postMemeResponse);

    }

    @PatchMapping(END_POINT + MEME_API_ID)
    public ResponseEntity<?> updateMeme(@RequestBody UpdateMeme meme, @PathVariable String moduleId) {

        memeServiceUpdate.updateMeme(moduleId,meme);

        return ResponseEntity.ok("Resource updated");
    }

}
