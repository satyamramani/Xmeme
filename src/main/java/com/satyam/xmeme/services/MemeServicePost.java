package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.PostMemeResponse;
import com.satyam.xmeme.dto.UserMeme;

public interface MemeServicePost {

    PostMemeResponse postMemes(UserMeme meme);
}
