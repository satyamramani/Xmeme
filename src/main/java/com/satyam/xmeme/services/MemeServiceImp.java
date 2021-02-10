package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.UserMeme;
import com.satyam.xmeme.model.MemeEntity;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
*This class implements MemeService with two methods findMemeById and findAllMemes
* @Autowired MongoTemplate
* @Autowired Provider<ModelMapper>
* findMemeById takes String id as argument and returns the userMeme containing the same id
* findAllMemes takes no argument and returns all the memes present in the database.

 */

@Service
public class MemeServiceImp implements MemeService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;


    //to get meme by Id.
    @Override
    public UserMeme findMemeById(String id) {

        UserMeme userMeme = new UserMeme();

        ModelMapper modelMapper = modelMapperProvider.get();

        //created a query for get meme by id.
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        //get the meme which have the same id.
        List<MemeEntity> memeEntities = mongoTemplate.find(query,MemeEntity.class);

        //map the response memeEntity to userMeme
        modelMapper.map(memeEntities.get(0),userMeme);

        return userMeme;
    }

    //to get all memes
    @Override
    public List<UserMeme> findAllMemes() {

        List<UserMeme> userMemes = new ArrayList<>();

        ModelMapper modelMapper = modelMapperProvider.get();

        //get list of all memes from database mongodb.
        List<MemeEntity> memeEntities = mongoTemplate.findAll(MemeEntity.class);

        //reverse the memes to get the latest first.
        Collections.reverse(memeEntities);

        //iterate over all memes to map in the userMemes
        for(MemeEntity memeEntity : memeEntities) {

            UserMeme userMeme = new UserMeme();

            modelMapper.map(memeEntity,userMeme);

            userMemes.add(userMeme);

        }

        return userMemes;
    }
}
