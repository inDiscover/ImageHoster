package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //This method calls the createComment method in the Repository and passes the Comment to insert/persist.
    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }
}
