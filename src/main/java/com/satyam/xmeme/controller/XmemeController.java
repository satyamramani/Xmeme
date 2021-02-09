package com.satyam.xmeme.controller;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.PostMemeResponse;
import com.satyam.xmeme.dto.UserMeme;
import com.satyam.xmeme.services.MemeService;
import com.satyam.xmeme.services.MemeServicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class XmemeController {

    public static final String END_POINT = "/memes";
    public static final String MEME_API = "/{moduleId}";

    @Autowired
    private MemeService memeService;

    @Autowired
    private MemeServicePost memeServicePost;

    @GetMapping(END_POINT + MEME_API)
    public ResponseEntity<UserMeme> findMeme(@PathVariable String moduleId) {

        UserMeme getMemes = memeService.findMemeById(moduleId);

        return ResponseEntity.ok().body(getMemes);
    }

    @GetMapping(END_POINT)
    public ResponseEntity<GetMemes> findALLMemes() {

        GetMemes getMemes = memeService.findAllMemes();

        return ResponseEntity.ok().body(getMemes);
    }

    @PostMapping(END_POINT)
    public ResponseEntity<PostMemeResponse> postMemes(@RequestBody UserMeme meme) {

        PostMemeResponse postMemeResponse = memeServicePost.postMemes(meme);

        return ResponseEntity.ok().body(postMemeResponse);

    }

}
