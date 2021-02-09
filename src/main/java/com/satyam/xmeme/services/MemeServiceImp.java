package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.GetMemes;
import com.satyam.xmeme.dto.UserMeme;
import com.satyam.xmeme.model.MemeEntity;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

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

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        List<MemeEntity> memeEntities = mongoTemplate.find(query,MemeEntity.class);

        modelMapper.map(memeEntities.get(0),userMeme);

        return userMeme;
    }

    //to get all memes
    @Override
    public GetMemes findAllMemes() {

        List<UserMeme> userMemes = new ArrayList<>();

        ModelMapper modelMapper = modelMapperProvider.get();

        List<MemeEntity> memeEntities = mongoTemplate.findAll(MemeEntity.class);

        for(MemeEntity memeEntity : memeEntities) {

            UserMeme userMeme = new UserMeme();

            modelMapper.map(memeEntity,userMeme);

            userMemes.add(userMeme);

        }

        GetMemes getMemes = new GetMemes(userMemes);

        return getMemes;
    }
}
