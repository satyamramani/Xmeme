package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.UserMeme;

import java.util.List;

public interface MemeService {

    UserMeme findMemeById(String id);

    List<UserMeme> findAllMemes();
}
