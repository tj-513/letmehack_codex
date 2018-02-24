package org.codex.uom.letmehack.avatar;

import org.codex.uom.letmehack.common.Constants;
import org.codex.uom.letmehack.user.User;
import org.codex.uom.letmehack.user.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by TJR on 2/24/2018.
 */
@Component
public class AvatarService {
    @Autowired
    UserRespository userRespository;

    public void addAvatar(MultipartFile multipartFile, Long userId) throws IOException {
        User user = userRespository.findOne(userId);
        if (multipartFile.isEmpty()) {
            throw new RuntimeException();
        }
        byte[] bytes = multipartFile.getBytes();
        String[] tokens = multipartFile.getOriginalFilename().split("\\.");
        String fileExtension = tokens[tokens.length - 1];
        String directory = Constants.AVATAR_DIRECTORY + "/user_" + userId + "." + fileExtension;
        Path path = Paths.get(directory);
        Files.write(path, bytes);
        user.setAvatarUrl(directory);
        userRespository.save(user);
    }

    public byte [] getImageById(Long userId){
        User user = userRespository.findOne(userId);
        return getImage(user.getAvatarUrl());
    }

    private byte [] getImage(String url){
        try {
            File file = new File("");
            // Retrieve image from the classpath.
            InputStream is = new FileInputStream(url);
            if(is == null )
                System.err.println("NULL image");
            // Prepare buffered image.
            BufferedImage img = ImageIO.read(is);

            // Create a byte array output stream.
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
