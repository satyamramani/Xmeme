package com.satyam.xmeme.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/*


{

"name": "ashok kumar",

"url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",

"caption": "This is a meme"

}

 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMeme {

    @Id
    private String id;

    private String name;

    private String url;

    private String caption;

}
