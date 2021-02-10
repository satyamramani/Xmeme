package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.UpdateMeme;
import com.satyam.xmeme.model.MemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeServiceUpdateImp implements MemeServiceUpdate{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateMeme(String id, UpdateMeme meme) {

        String newURL = meme.getUrl();
        String newCaption = meme.getCaption();

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        List<MemeEntity> memeEntities = mongoTemplate.find(query,MemeEntity.class);

        if(newURL != null) {
            memeEntities.get(0).setUrl(newURL);
        }
        if(newCaption != null) {
            memeEntities.get(0).setCaption(newCaption);
        }

        mongoTemplate.save(memeEntities.get(0));

        return;

    }
}
