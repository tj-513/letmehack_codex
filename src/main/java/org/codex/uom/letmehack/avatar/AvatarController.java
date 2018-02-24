package org.codex.uom.letmehack.avatar;

import org.codex.uom.letmehack.common.ErrorMessage;
import org.codex.uom.letmehack.common.SuccessMessage;
import org.codex.uom.letmehack.user.User;
import org.codex.uom.letmehack.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

/**
 * Created by TJR on 2/24/2018.
 */
@Controller
@RequestMapping("/api")
public class AvatarController {
    @Autowired
    private AvatarService avatarService;
    @PostMapping("/avatars")
    public ResponseEntity<?> addAvatar(@RequestParam("file") MultipartFile file, @RequestParam("user_id") Long userId) {
        try {
            if(userId == null){
                // todo: update avatar of authenticated user
            }else{
              avatarService.addAvatar(file,userId);
            }
            SuccessMessage message = new SuccessMessage();
            message.setMessage("successfully added");
            return new ResponseEntity<SuccessMessage>(message, HttpStatus.OK);
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setStatus("500");
            errorMessage.setErrorMessage("An error occurred while saving file");
            errorMessage.setDeveloperMessage(e.toString());
            return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.valueOf(500));
        }
    }

    @GetMapping("avatars")
    public ResponseEntity<byte[]> getAvatarById(@RequestParam("user_id") Long userId){
        byte[] image =  avatarService.getImageById(userId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
