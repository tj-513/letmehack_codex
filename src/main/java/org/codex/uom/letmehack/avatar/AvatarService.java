package org.codex.uom.letmehack.avatar;

import org.codex.uom.letmehack.common.Constants;
import org.codex.uom.letmehack.user.User;
import org.codex.uom.letmehack.user.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
}
