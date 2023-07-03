package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    // TODO implement the tests
    @TestSubject
    Discussion discussion;
    @TestSubject
    Course courseMock = createMock(Course.class);
    @TestSubject
    Comment commentMock = createMock(Comment.class);
    @Test
    public void testComment() {
        EasyMock.expect(commentMock.save()).andReturn(true);
        EasyMock.replay(commentMock);
        discussion = new Discussion();
        assertEquals(true, discussion.addComment(commentMock));
        assertEquals(1, discussion.getNumberOfComments());
    }

    @Test
    public void testCommentIfSavingFails() {
        EasyMock.expect(commentMock.save()).andReturn(false);
        EasyMock.replay(commentMock);
        discussion = new Discussion();
        discussion.addComment(commentMock);
        assertEquals(0, discussion.getNumberOfComments());
    }

    @Test
    public void testStartCourseDiscussion() {
        Person lecturer = new Lecturer("Harald", "Räcke", LocalDate.now());
        EasyMock.expect(courseMock.isDiscussionAllowed(lecturer)).andReturn(true);
        EasyMock.replay(courseMock);
        discussion = new Discussion();
        assertEquals(true, discussion.startCourseDiscussion(courseMock, lecturer, "Endterm"));
        assertEquals(courseMock, discussion.getCourse());
        assertEquals("Endterm", discussion.getTopic());
    }

}
