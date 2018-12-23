package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This controller method is used while submiting a comment from UI , with a POST request method.
    //Accepts image id , to get the image object for which we are creating comments.
    //Maps comments to image and user appropriately by setting the selected image and logged in user into the Comment Model
    //After all redirects to the same page showing all the comments.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String comment, Comment newComment, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Image selectedImage = imageService.getImageById(imageId);

        newComment.setText(comment);
        newComment.setCreatedDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        newComment.setImage(selectedImage);
        newComment.setUser(user);
        commentService.createComment(newComment);
        return "redirect:/images/" + selectedImage.getId() + "/" + selectedImage.getTitle();
    }
}
