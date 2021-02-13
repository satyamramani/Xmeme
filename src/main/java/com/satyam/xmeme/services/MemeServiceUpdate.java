package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.UpdateMeme;

public interface MemeServiceUpdate {

    void updateMeme(String id, UpdateMeme updateNewMeme);
}
