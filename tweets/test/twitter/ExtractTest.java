/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.awt.List;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
	    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
	    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
	    private static final Instant d3 = Instant.parse("2016-02-17T11:10:00Z");
	    private static final Instant d4 = Instant.parse("2016-02-17T11:15:00Z");
	    private static final Instant d5 = Instant.parse("2016-02-17T11:30:00Z");
	    private static final Instant d6 = Instant.parse("2016-02-17T10:30:00Z");
	    
	    private static final Tweet tweet1 = new Tweet(1, "alyssa", "@hamad is it reasonable to talk about rivest so much?", d1);
	    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
	    private static final Tweet tweet3 = new Tweet(3, "Ahsan Khan", "@hamad whats going on dude??", d3);
	    private static final Tweet tweet4 = new Tweet(4, "Ahsan's Tweet", "@Ahsan is doing tweet 4",d4);
	    private static final Tweet tweet5 = new Tweet(5, "usman and shazaib tweet", "@usman @shazaib are doing tweet 5",d5);
	    private static final Tweet tweet6 = new Tweet(6, "hamad tweet", "@hamad @hamad doing tweet 6",d6);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    /**this test case test the method if we pass two tweets in the list
   
      */
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    /**this test case test the method if we pass three tweets in the list
    
     */
    @Test
    public void testGetTimespanThreeTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d3, timespan.getEnd());
    }
    
    /**this test case test the method if we pass one tweets in the list
    
     */
    
    @Test
    public void testGetTimespanOneTweet() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        assertEquals("start",d1, timespan.getStart());
        assertEquals("end",d1, timespan.getEnd());        
    }
    /**this test case test the method if we pass no tweets in the list
    
     */
   @Test
    public void testGetTimespanNoTweet() {
    	ArrayList list = new ArrayList<Tweet>();
    	
        Timespan timespan = Extract.getTimespan(list);
        assertEquals(timespan.getStart(),timespan.getEnd());         
    }
 /**this test case test the method if there is no one mentioned in tweet
    
     */
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
 /**this test case test the method if there is one mentioned in only one tweet
  * like for example  "@Ahsan is this another one of these stupid tests?" 
  * here only one username is mentioned "Ahsan"
    
     */
    @Test
    public void testGetMentionedUsersOneMentionOneTweet() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.contains("ahsan"));
    }
    /**this test case test the method if there are two mentioned in only one tweet
     * like for example  "@usman @shazaib can't believe I'm doing another"
     * here two usernames are mentioned "usman","shazaib"
       
        */
    @Test
    public void testGetMentionedUsersTwoeMentionOneTweet() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.containsAll(Arrays.asList("usman", "shazaib")));
    }
    /**this test case test the method if there are two mentioned in only one tweet but they are repeated
     * like for example  "@hamad @hamad can't believe I'm doing another"
     * here two usernames are mentioned "hamad","hamad" but two times
       
        */
    @Test
    public void testGetMentionedUsersTwoeMentionOneTweetrepeateduser() {         
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.contains("hamad"));
    }
    @Test
    public void testGetMentionedUsersMoreeMentionMoreThanOneTweetrepeateduser() {    
    	int count=0;
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6,tweet1,tweet3));
        Set<String> mentionedUsersLowerCase = new HashSet<>();
        for (int i=0;i<mentionedUsers.size();i++)
        {
        	count++;
        }
        assertEquals(1,count);
        for (String mentionedUser : mentionedUsers) {
            mentionedUsersLowerCase.add(mentionedUser.toLowerCase());
        }
        assertTrue(mentionedUsersLowerCase.contains("hamad"));
    }
   
   
   

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
