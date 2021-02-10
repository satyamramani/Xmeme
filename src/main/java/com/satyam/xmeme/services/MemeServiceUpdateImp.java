package com.satyam.xmeme.services;

import com.satyam.xmeme.dto.UpdateMeme;
import com.satyam.xmeme.model.MemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*This class implements MemeServiceUpdate which has one method updateMeme
* @Autowired MongoTemplate
* updateMeme takes String id and UpdateMeme as argument and updates the changes in database.

 */

@Service
public class MemeServiceUpdateImp implements MemeServiceUpdate{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateMeme(String id, UpdateMeme meme) {

        String newURL = meme.getUrl();
        String newCaption = meme.getCaption();

        //query to get the meme by id.
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        //get meme contains the same id
        List<MemeEntity> memeEntities = mongoTemplate.find(query,MemeEntity.class);

        //if only url is need to be updated then leaving caption as it is.
        if(newURL != null) {
            memeEntities.get(0).setUrl(newURL);
        }//if only caption is need to be updated then leaving url as it is.
        if(newCaption != null) {
            memeEntities.get(0).setCaption(newCaption);
        }

        //saving the change to database.
        mongoTemplate.save(memeEntities.get(0));

    }
}
