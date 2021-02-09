package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.UserMeme;

public interface MemeService {

    UserMeme findMemeById(String id);

    GetMemes findAllMemes();
}
