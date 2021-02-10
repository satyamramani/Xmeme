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

/*
* This class implements MemeServicePost
* @Autowired MongoTemplate
* @Autowired Provider<ModelMapper>

* It has two methods postMemes and checkDuplicates
* postMemes takes argument as UserMeme and returns the id of new created meme.
* checkDuplicates takes arguments as List<MemeEntity> and UserMeme to check it is duplicate or not and
* return true or false

 */

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

        //map userEntity into memeEntity
        modelMapper.map(meme,memeEntity);

        //Find all memes from database
        List<MemeEntity> memeEntities = mongoTemplate.findAll(MemeEntity.class);

        String id;

        //if duplicate exist then return empty as id otherwise return the new id.
        if(!checkDuplicates(memeEntities,meme)) {
            mongoTemplate.save(memeEntity);
            id = memeEntity.getId();
        }
        else {
            id = "empty";
        }

        return new PostMemeResponse(id);
    }

    //for checking the duplicate entry
    private boolean checkDuplicates(List<MemeEntity> memeEntities, UserMeme meme) {

        //iterate over each meme to check name,url and caption are same or not.
        for(MemeEntity memeEntity: memeEntities) {
            if(meme.getName().equals(memeEntity.getName()) && meme.getUrl().equals(memeEntity.getUrl()) &&
            meme.getCaption().equals(memeEntity.getCaption())) {
                return true;
            }
        }

        return false;
    }
}
