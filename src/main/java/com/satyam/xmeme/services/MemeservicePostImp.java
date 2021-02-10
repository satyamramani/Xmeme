package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.PostMemeResponse;
import com.satyam.xmeme.dto.UserMeme;
import com.satyam.xmeme.model.MemeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.List;

@Service
public class MemeservicePostImp implements MemeServicePost{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;

    @Override
    public PostMemeResponse postMemes(UserMeme meme) {

        ModelMapper modelMapper = modelMapperProvider.get();

        MemeEntity memeEntity = new MemeEntity();

        modelMapper.map(meme,memeEntity);

//        List<MemeEntity> memeEntities = mongoTemplate.findAll(MemeEntity.class);
//
//        int count = memeEntities.size()+1;
//
//        String id = Integer.toString(count);

//        memeEntity.setId(id);
        
        mongoTemplate.save(memeEntity);

        String id = memeEntity.getId();

        return new PostMemeResponse(id);
    }
}
